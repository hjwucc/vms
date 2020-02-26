package com.wu.vms.service.impl;

import com.wu.vms.controller.viewobject.MenuVO;
import com.wu.vms.dataobject.MenuDO;
import com.wu.vms.dataobject.RoleDO;
import com.wu.vms.dataobject.UserDO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.mapper.MenuDOMapper;
import com.wu.vms.mapper.RoleDOMapper;
import com.wu.vms.mapper.UserDOMapper;
import com.wu.vms.service.UserService;
import com.wu.vms.service.model.MenuModel;
import com.wu.vms.service.model.UserModel;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:38
 * @description：用户登录处理实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private RoleDOMapper roleDOMapper;

    @Autowired(required = false)
    private UserDOMapper userDOMapper;

    @Autowired(required = false)
    private MenuDOMapper menuDOMapper;


    @Override
    public UserModel getUserByUserId(Integer userId) {
        UserDO userDO = userDOMapper.selectByUserId(userId);
        RoleDO roleDO = null;
        if(userDO != null){
            roleDO = roleDOMapper.selectByPrimaryKey(userDO.getRoleid());
        }
        return convertFromDataObject(userDO,roleDO);
    }

    @Override
    public int updatePassword(Integer userId, String password) {
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        int i = userDOMapper.updatePassword(userId, encodePassword);
        return i;
    }

    @Override
    public List<MenuModel> getMenuByUserId(Integer userId) {
        List<MenuDO> menuDOList = menuDOMapper.getMenuByUserId(userId);
        List<MenuModel> modelList = new ArrayList<>();
        for (MenuDO menuDO:menuDOList
             ) {
            modelList.add(convertFromDataObject_v(menuDO));
        }
        return modelList;
    }

    /**
    * @Description 模型转换成UserDO
    * @param userModel
    * @Return com.wu.vms.dataobject.UserDO
    */
    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    /**
    * @Description 模型转换成UserModel
    * @param userDO
    * @param roleDO
    * @Return com.wu.vms.service.model.UserModel
    */
    private UserModel convertFromDataObject(UserDO userDO, RoleDO roleDO){
        if(userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if(roleDO != null){
            userModel.setRoleName(roleDO.getName());
            userModel.setRoleNameZh(roleDO.getNamezh());
        }
        return userModel;
    }

    /**
    * @Description 转换为MenuModel
    * @param menuDO
    * @Return com.wu.vms.service.model.MenuModel
    */
    private MenuModel convertFromDataObject_v(MenuDO menuDO){
        if(menuDO == null) return null;
        MenuModel menuModel = new MenuModel();
        BeanUtils.copyProperties(menuDO,menuModel);
        return menuModel;
    }

    @Test
    public void dkjk(){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
