package com.wu.vms.service;

import com.wu.vms.error.CommonException;
import com.wu.vms.service.model.TeacherModel;

import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/21 21:40
 * @description：老师业务层接口
 */

public interface TeacherService {
    List<TeacherModel> selectAll();
    TeacherModel selectById(Integer id);
    int updateTeacherInfo(TeacherModel teacherModel);
    void addSingleTeacher(TeacherModel teacherModel) throws CommonException;
}
