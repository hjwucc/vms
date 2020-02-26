package com.wu.vms.service;

import com.wu.vms.error.CommonException;
import com.wu.vms.service.model.StudentModel;
import com.wu.vms.service.model.SuccessModel;
import com.wu.vms.service.model.TeacherModel;

import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/22 14:16
 * @description：学生Service层接口
 */

public interface StudentService {

    StudentModel selectById(Integer id);
    TeacherModel selectMyTeacher(Integer teacherId);
    int updateMyTeacher(Integer id,Integer teacherId);
    int updateMyInfo(StudentModel studentModel);
    List<StudentModel> selectStudentsByTeacherId(Integer teacherId) throws CommonException;
    int updateStatus(Integer id,Integer status);
    List<StudentModel> selectMyStudents(Integer teacherId);
    void updateMyStudents(Integer teacherId,List<Integer> ids) throws CommonException;
    List<StudentModel> selectNonMatchedStudents();
    void addSingleStudent(StudentModel studentModel) throws CommonException;
    List<SuccessModel> selectSuccessStudents() throws CommonException;
}
