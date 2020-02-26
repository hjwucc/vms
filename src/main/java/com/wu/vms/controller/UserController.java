package com.wu.vms.controller;

import com.github.pagehelper.PageHelper;
import com.wu.vms.controller.viewobject.MenuVO;
import com.wu.vms.controller.viewobject.UserVO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.response.CommonReturnType;
import com.wu.vms.service.TeacherService;
import com.wu.vms.service.UserService;
import com.wu.vms.service.model.MenuModel;
import com.wu.vms.service.model.TeacherModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:28
 * @description：处理用户登录
 */
@RestController
@Api(tags = "用户操作相关接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("menu")
    @ApiOperation("获取菜单接口")
    public CommonReturnType getMenu(Authentication authentication) throws CommonException {
        //判空
        int userId = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            userId = Integer.parseInt(authentication.getName());
        }
        //查找
        List<MenuModel> modelList = userService.getMenuByUserId(userId);
        if(modelList == null || modelList.size() == 0){
            throw new CommonException(EmCommonError.GET_MENU_ERROR);
        }
        //转换为MenuVO
        List<MenuVO> voList = new ArrayList<>();
        for (MenuModel menuModel:modelList
             ) {
            voList.add(convertFromModel_v(menuModel));
        }
        //返回
        return CommonReturnType.create(voList);
    }

    /**
    * @Description 转换为MenuVO
    * @param menuModel
    * @Return com.wu.vms.controller.viewobject.MenuVO
    */
    public MenuVO convertFromModel_v(MenuModel menuModel){
        if(menuModel == null) return null;
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menuModel,menuVO);
        return menuVO;
    }
}
