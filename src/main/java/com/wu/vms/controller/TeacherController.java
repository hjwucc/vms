package com.wu.vms.controller;

import com.github.pagehelper.PageHelper;
import com.wu.vms.controller.viewobject.StudentVO;
import com.wu.vms.controller.viewobject.TeacherVO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.response.CommonReturnType;
import com.wu.vms.service.StudentService;
import com.wu.vms.service.TeacherService;
import com.wu.vms.service.UserService;
import com.wu.vms.service.model.StudentModel;
import com.wu.vms.service.model.TeacherModel;
import com.wu.vms.thread.EmailRunnable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/21 22:29
 * @description：处理老师业务
 */
@RestController
@Api(tags = "老师操作相关接口")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    @Autowired
    private UserService userService;

    @GetMapping("selectTeacherInfo")
    @ApiOperation("查询老师个人信息接口")
    public CommonReturnType selectMyInfo(Authentication authentication){
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //查找
        TeacherModel teacherModel = teacherService.selectById(id);
        //转换
        TeacherVO teacherVO = convertFromModel(teacherModel);
        //返回
        return CommonReturnType.create(teacherVO);
    }

    @PostMapping("updateTeacherInfo")
    @ApiOperation("更改老师个人信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "gender",value = "性别"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "phone",value = "手机号码"),
            @ApiImplicitParam(name = "direction",value = "研究方向"),
            @ApiImplicitParam(name = "describe",value = "个人简介")
    })
    public CommonReturnType updateTeacherInfo(Authentication authentication,TeacherVO teacherVO) throws CommonException {
        //判空
        if(teacherVO == null){
            throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
        }
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //更新个人信息
        teacherVO.setId(id);
        int i = teacherService.updateTeacherInfo(convertFromViewObject(teacherVO));
        if(i == 0){
            throw new CommonException(EmCommonError.UPDATE_TEACHER_ERROR);
        }
        //返回成功
        return CommonReturnType.create(null);
    }

    @GetMapping("selectStudentInfo")
    @ApiOperation("查询学生个人信息接口")
    @ApiImplicitParam(name = "studentId",value = "学号ID",required = true)
    public CommonReturnType selectStudentInfo(@RequestParam Integer studentId) throws CommonException {
        //查找
        StudentModel studentModel = studentService.selectById(studentId);
        if(studentModel == null){
            throw new CommonException(EmCommonError.SELECT_STUDENT_INFO_ERROR);
        }
        //转换为StudentVO
        StudentVO studentVO = studentController.convertFromModel(studentModel);
        //返回
        return CommonReturnType.create(studentVO);
    }

    @GetMapping("selectStudents")
    @ApiOperation("查找选择我为导师的所有学生列表接口")
    public CommonReturnType selectStudents(Authentication authentication) throws CommonException {
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //查找
        List<StudentModel> modelList = studentService.selectStudentsByTeacherId(id);
        if(modelList == null || modelList.size() == 0){
            throw new CommonException(EmCommonError.HAVE_NO_STUDENT_SELECT);
        }
        //转换为StudentVO
        List<StudentVO> voList = new ArrayList<>();
        for (StudentModel studentModel:modelList
             ) {
            voList.add(studentController.convertFromModel(studentModel));
        }
        //返回
        return CommonReturnType.create(voList);
    }

    @PostMapping("updateMyStudents")
    @ApiOperation("确认选择学生接口")
    @ApiImplicitParam(name = "ids",value = "学生ID集合")
    public CommonReturnType updateMyStudents(Authentication authentication,@RequestBody List<Integer> ids) throws CommonException {
        //判空
        if(ids == null || ids.size() == 0){
            throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
        }
        int teacherId = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            teacherId = Integer.parseInt(authentication.getName());
        }
        //更改学生信息并发送邮件
        studentService.updateMyStudents(teacherId, ids);
        //返回成功
        return CommonReturnType.create(null);
    }

    @GetMapping("selectMyStudents")
    @ApiOperation("查看我的学生列表接口")
    public CommonReturnType selectMyStudents(Authentication authentication) throws CommonException {
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //查找
        List<StudentModel> modelList = studentService.selectMyStudents(id);
        if(modelList == null || modelList.size() == 0){
            throw new CommonException(EmCommonError.HAVE_NO_STUDENT_CONFIRM_SELECT);
        }
        //转换为StudentVO
        List<StudentVO> voList = new ArrayList<>();
        for (StudentModel studentModel:modelList
        ) {
            voList.add(studentController.convertFromModel(studentModel));
        }
        //返回
        return CommonReturnType.create(voList);
    }

    @PostMapping("updatePassword")
    @ApiOperation("更改老师密码接口")
    @ApiImplicitParam(name = "password",value = "此处填修改后的密码",required = true)
    public CommonReturnType updatePassword(Authentication authentication,@RequestParam String password) throws CommonException {
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //修改密码
        int i = userService.updatePassword(id, password);
        if(i == 0){
            throw new CommonException(EmCommonError.UPDATE_PASSWORD_ERROR);
        }
        //返回成功
        return CommonReturnType.create(null);
    }

    /**
    * @Description 转换为视图层模型
    * @param teacherModel
    * @Return com.wu.vms.controller.viewobject.TeacherVO
    */
    public TeacherVO convertFromModel(TeacherModel teacherModel){
        if(teacherModel == null) return null;
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacherModel,teacherVO);
        return teacherVO;
    }

    /**
    * @Description TeacherVO转换为TeacherModel
    * @param teacherVO
    * @Return com.wu.vms.service.model.TeacherModel
    */
    public TeacherModel convertFromViewObject(TeacherVO teacherVO){
        if(teacherVO == null) return null;
        TeacherModel teacherModel = new TeacherModel();
        BeanUtils.copyProperties(teacherVO,teacherModel);
        return teacherModel;
    }
}
