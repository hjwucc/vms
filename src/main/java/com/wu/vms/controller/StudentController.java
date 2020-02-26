package com.wu.vms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/21 22:31
 * @description：处理学生业务
 */
@RestController
@Api(tags = "学生操作相关接口")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherController teacherController;
    @Autowired
    private UserService userService;

    @GetMapping("selectTeachers")
    @ApiOperation("查询所有老师列表接口")
    @ApiImplicitParam(name = "pageNum",value = "页数",defaultValue = "1",required = true)
    public CommonReturnType selectTeachers(@RequestParam Integer pageNum){
        if(pageNum == null || pageNum == 0) pageNum=1;//默认为第一页
        //查询并分页
        PageHelper.startPage(pageNum,10);
        List<TeacherModel> teacherModelList = teacherService.selectAll();
        PageInfo<TeacherModel> voPageInfo = new PageInfo<>(teacherModelList);
        //转换为TeacherVO
        List<TeacherVO> teacherVOList = new ArrayList<>();
        for (TeacherModel teacherModel:voPageInfo.getList()
             ) {
            teacherVOList.add(teacherController.convertFromModel(teacherModel));
        }
        //返回数据
        return CommonReturnType.create(teacherVOList);
    }

    @GetMapping("updateMyTeacher")
    @ApiOperation("确认选择该导师接口")
    @ApiImplicitParam(name = "teacherId",value = "导师ID",required = true)
    public CommonReturnType updateMyTeacher(Authentication authentication, @RequestParam Integer teacherId) throws CommonException {
       //判空
        int studentId = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            studentId = Integer.parseInt(authentication.getName());
        }
        //更改导师信息，设置状态为已选择
        try {
            studentService.updateMyTeacher(studentId, teacherId);
            studentService.updateStatus(studentId, 2);
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(EmCommonError.UPDATE_MY_TEACHER_ERROR);
        }
        //返回成功
        return CommonReturnType.create(null);
    }

    @GetMapping("selectStudentInfo")
    @ApiOperation("查询学生个人信息接口")
    public CommonReturnType selectMyInfo(Authentication authentication){
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //查找
        StudentModel studentModel = studentService.selectById(id);
        //转换
        StudentVO studentVO = convertFromModel(studentModel);
        //返回
        return CommonReturnType.create(studentVO);
    }

    @PostMapping("updateStudentInfo")
    @ApiOperation("修改学生个人信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "gender",value = "性别"),
            @ApiImplicitParam(name = "major",value = "专业"),
            @ApiImplicitParam(name = "politic",value = "政治面貌"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "phone",value = "手机号码"),
            @ApiImplicitParam(name = "describe",value = "个人简介")
    })
    public CommonReturnType updateMyInfo(Authentication authentication, StudentVO studentVO) throws CommonException {
        //判空
        if(studentVO == null){
            throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
        }
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //更改信息
        studentVO.setId(id);
        int i = studentService.updateMyInfo(convertFromViewObject(studentVO));
        if(i == 0){
            throw new CommonException(EmCommonError.UPDATE_STUDENT_ERROR);
        }
        //修改成功
        return CommonReturnType.create(null);
    }

    @GetMapping("selectTeacherInfo")
    @ApiOperation("查询该老师个人信息接口")
    @ApiImplicitParam(name = "teacherId",value = "老师ID",required = true)
    public CommonReturnType selectTeacherInfo(@RequestParam Integer teacherId) throws CommonException {
        //查找
        TeacherModel teacherModel = teacherService.selectById(teacherId);
        if(teacherModel == null){
            throw new CommonException(EmCommonError.SELECT_TEACHER_INFO_ERROR);
        }
        //转换
        TeacherVO teacherVO = teacherController.convertFromModel(teacherModel);
        //返回
        return CommonReturnType.create(teacherVO);
    }

    @GetMapping("selectMyTeacher")
    @ApiOperation("查看我的导师接口")
    public CommonReturnType selectMyTeacher(Authentication authentication) throws CommonException {
        TeacherVO teacherVO = null;
        //判空
        int id = 0;
        if(!StringUtils.isEmpty(authentication.getName())){
            id = Integer.parseInt(authentication.getName());
        }
        //查找学生
        StudentModel studentModel = studentService.selectById(id);
        //判断选择状态
        Integer status = studentModel.getStatus();
        if(status == 1){
            throw new CommonException(EmCommonError.STUDENT_NOT_SELECT);
        }else if(status == 2){
            throw new CommonException(EmCommonError.STUDENT_NOT_CONFIRM_SELECT);
        }else{
            //有导师选择，则返回导师信息
            TeacherModel teacherModel = studentService.selectMyTeacher(studentModel.getTeacherid());
            //转换为TeacherVO
            teacherVO = teacherController.convertFromModel(teacherModel);
        }
        //返回
        return CommonReturnType.create(teacherVO);
    }

    @PostMapping("updatePassword")
    @ApiOperation("更改学生密码接口")
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
    * @param studentModel
    * @Return com.wu.vms.controller.viewobject.StudentVO
    */
    public StudentVO convertFromModel(StudentModel studentModel){
      if(studentModel == null) return null;
      StudentVO studentVO = new StudentVO();
      BeanUtils.copyProperties(studentModel,studentVO);
      return studentVO;
    }

    /**
    * @Description 转换为业务层模型
    * @param studentVO
    * @Return com.wu.vms.service.model.StudentModel
    */
    public StudentModel convertFromViewObject(StudentVO studentVO){
        if(studentVO == null) return null;
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(studentVO,studentModel);
        return studentModel;
    }
}
