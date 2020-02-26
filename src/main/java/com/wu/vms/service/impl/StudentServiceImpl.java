package com.wu.vms.service.impl;

import com.wu.vms.dataobject.StudentDO;
import com.wu.vms.dataobject.UserDO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.mapper.StudentDOMapper;
import com.wu.vms.mapper.TeacherDOMapper;
import com.wu.vms.mapper.UserDOMapper;
import com.wu.vms.service.StudentService;
import com.wu.vms.service.TeacherService;
import com.wu.vms.service.model.StudentModel;
import com.wu.vms.service.model.SuccessModel;
import com.wu.vms.service.model.TeacherModel;
import com.wu.vms.thread.EmailRunnable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/22 14:21
 * @description：学生Service接口实现类
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentDOMapper studentDOMapper;
    @Autowired(required = false)
    private TeacherService teacherService;
    @Autowired(required = false)
    private UserDOMapper userDOMapper;
    @Autowired
    ExecutorService executorService;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public StudentModel selectById(Integer id) {
        StudentDO studentDO = studentDOMapper.selectByPrimaryKey(id);
        return convertFromDataObject(studentDO);
    }

    @Override
    public TeacherModel selectMyTeacher(Integer teacherId) {
        TeacherModel teacherModel = teacherService.selectById(teacherId);
        return teacherModel;
    }

    @Override
    public int updateMyTeacher(Integer id, Integer teacherId) {
        return studentDOMapper.updateMyTeacher(id, teacherId);
    }

    @Override
    public int updateMyInfo(StudentModel studentModel) {
        StudentDO studentDO = convertFromModel(studentModel);
        int i = studentDOMapper.updateByPrimaryKeySelective(studentDO);
        return i;
    }

    @Override
    public List<StudentModel> selectStudentsByTeacherId(Integer teacherId) {
        List<StudentDO> studentDOList = studentDOMapper.selectStudentsByTeacherId(teacherId);
        List<StudentModel> modelList = new ArrayList<>();
        for (StudentDO studentDO:studentDOList
             ) {
            modelList.add(convertFromDataObject(studentDO));
        }
        return modelList;
    }

    @Override
    public int updateStatus(Integer id,Integer status) {
        int i = studentDOMapper.updateStatus(id, status);
        return i;
    }

    @Override
    public List<StudentModel> selectMyStudents(Integer teacherId) {
        List<StudentDO> studentDOList = studentDOMapper.selectMyStudents(teacherId);
        List<StudentModel> modelList = new ArrayList<>();
        for (StudentDO studentDO:studentDOList
        ) {
            modelList.add(convertFromDataObject(studentDO));
        }
        return modelList;
    }

    @Override
    public void updateMyStudents(Integer teacherId, List<Integer> ids) throws CommonException {
        int size = ids.size();
        try {
                for(int i = 0; i < size; i++){
                Integer studentId = ids.get(i);
                studentDOMapper.updateMyTeacher(studentId,teacherId);
                studentDOMapper.updateStatus(studentId,3);
                //发送邮件通知
                StudentDO studentDO = studentDOMapper.selectByPrimaryKey(studentId);
                TeacherModel teacherModel = teacherService.selectById(teacherId);
                executorService.execute(new EmailRunnable(studentDO,teacherModel,javaMailSender,templateEngine));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(EmCommonError.SELECT_MY_STUDENT_ERROR);
        }
    }

    @Override
    public List<StudentModel> selectNonMatchedStudents() {
        List<StudentDO> doList = studentDOMapper.selectNonMatchedStudents();
        List<StudentModel> modelList = new ArrayList<>();
        for (StudentDO studentDO:doList
             ) {
            modelList.add(convertFromDataObject(studentDO));
        }
        return modelList;
    }

    @Override
    public void addSingleStudent(StudentModel studentModel) throws CommonException {
        try {
            //添加学生表数据
            studentDOMapper.insertSelective(convertFromModel(studentModel));
            //添加用户表数据
            UserDO userDO = new UserDO();
            userDO.setUserid(studentModel.getId());
            userDO.setPassword(new BCryptPasswordEncoder().encode("123456"));
            userDO.setRoleid(2);
            userDOMapper.insertSelective(userDO);
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(EmCommonError.ADD_SINGLE_STUDENT_ERROR);
        }
    }

    @Override
    public List<SuccessModel> selectSuccessStudents() throws CommonException {
        List<SuccessModel> successModels = new ArrayList<>();
        List<StudentDO> students = studentDOMapper.selectSuccessStudents();
        if(students == null || students.size() == 0){
            throw new CommonException(EmCommonError.HAVE_NO_STUDENT_CONFIRM_SELECT);
        }
        try {
            for (StudentDO studentDO:students
                 ) {
                SuccessModel successModel = new SuccessModel();
                TeacherModel teacherModel = teacherService.selectById(studentDO.getTeacherid());
                successModel.setStudentid(studentDO.getId());
                successModel.setStudentname(studentDO.getName());
                successModel.setMajor(studentDO.getMajor());
                successModel.setStudentphone(studentDO.getPhone());
                successModel.setStudentemail(studentDO.getEmail());
                successModel.setTeachername(teacherModel.getName());
                successModel.setTeacherphone(teacherModel.getPhone());
                successModel.setTeacheremail(teacherModel.getEmail());
                successModels.add(successModel);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(EmCommonError.SELECT_SUCCESS_ERROR);
        }
        return successModels;
    }

    /**
    * @Description 转换为业务层模型
    * @param studentDO
    * @Return com.wu.vms.service.model.StudentModel
    */
    public StudentModel convertFromDataObject(StudentDO studentDO){
        if(studentDO == null) return null;
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(studentDO,studentModel);
        return studentModel;
    }

    /**
    * @Description 转换为数据层模型
    * @param studentModel
    * @Return com.wu.vms.dataobject.StudentDO
    */
    public StudentDO convertFromModel(StudentModel studentModel){
        if(studentModel == null) return null;
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(studentModel,studentDO);
        return studentDO;
    }
}
