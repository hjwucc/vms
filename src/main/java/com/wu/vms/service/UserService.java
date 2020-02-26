package com.wu.vms.service;

import com.wu.vms.dataobject.MenuDO;
import com.wu.vms.service.model.MenuModel;
import com.wu.vms.service.model.UserModel;

import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:38
 * @description：用户登录接口
 */

public interface UserService {
    UserModel getUserByUserId(Integer id);

    int updatePassword(Integer userId,String password);

    List<MenuModel> getMenuByUserId(Integer userId);
}
