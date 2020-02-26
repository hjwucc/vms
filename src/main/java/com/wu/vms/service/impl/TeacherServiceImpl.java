package com.wu.vms.service.impl;

import com.wu.vms.dataobject.TeacherDO;
import com.wu.vms.dataobject.UserDO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.mapper.TeacherDOMapper;
import com.wu.vms.mapper.UserDOMapper;
import com.wu.vms.service.TeacherService;
import com.wu.vms.service.model.TeacherModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/21 21:41
 * @description：老师业务层实现类
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired(required = false)
    private TeacherDOMapper teacherDOMapper;
    @Autowired(required = false)
    private UserDOMapper userDOMapper;

    @Override
    public List<TeacherModel> selectAll() {
        List<TeacherDO> teacherDOList = teacherDOMapper.selectAll();
        List<TeacherModel> teacherModelList = null;
        if(teacherDOList != null){
            teacherModelList = new ArrayList<>();
            for (TeacherDO teacherDO:teacherDOList
                 ) {
                teacherModelList.add(convertFromDataObject(teacherDO));
            }
        }
        return teacherModelList;
    }

    @Override
    public TeacherModel selectById(Integer id) {
        TeacherDO teacherDO = teacherDOMapper.selectByPrimaryKey(id);
        return convertFromDataObject(teacherDO);
    }

    @Override
    public int updateTeacherInfo(TeacherModel teacherModel) {
        int i = teacherDOMapper.updateByPrimaryKeySelective(convertFromModel(teacherModel));
        return i;
    }

    @Override
    public void addSingleTeacher(TeacherModel teacherModel) throws CommonException {
        try {
            //添加老师表数据
            teacherDOMapper.insertSelective(convertFromModel(teacherModel));
            //添加用户表数据
            UserDO userDO = new UserDO();
            userDO.setUserid(teacherModel.getId());
            userDO.setRoleid(3);
            userDO.setPassword(new BCryptPasswordEncoder().encode("123456"));
            userDOMapper.insertSelective(userDO);
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(EmCommonError.ADD_SINGLE_TEACHER_ERROR);
        }
    }

    /**
    * @Description 转换为业务层模型
    * @param teacherDO
    * @Return com.wu.vms.service.model.TeacherModel
    */
    public TeacherModel convertFromDataObject(TeacherDO teacherDO){
        if(teacherDO == null) return null;
        TeacherModel teacherModel = new TeacherModel();
        BeanUtils.copyProperties(teacherDO,teacherModel);
        return teacherModel;
    }

    /**
    * @Description 转换为TeacherDO
    * @param teacherModel
    * @Return com.wu.vms.dataobject.TeacherDO
    */
    public TeacherDO convertFromModel(TeacherModel teacherModel){
        if(teacherModel == null) return null;
        TeacherDO teacherDO = new TeacherDO();
        BeanUtils.copyProperties(teacherModel,teacherDO);
        return teacherDO;
    }
}
