package com.wu.vms.controller;

import com.wu.vms.controller.viewobject.StudentVO;
import com.wu.vms.controller.viewobject.SuccessVO;
import com.wu.vms.controller.viewobject.TeacherVO;
import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.response.CommonReturnType;
import com.wu.vms.service.StudentService;
import com.wu.vms.service.TeacherService;
import com.wu.vms.service.model.StudentModel;
import com.wu.vms.service.model.SuccessModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/23 18:12
 * @description：管理员相关操作
 */
@RestController
@Api(tags = "管理员操作相关接口")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherController teacherController;

    @GetMapping("selectNonMatchedStudents")
    @ApiOperation("查看没有匹配的学生接口")
    public CommonReturnType selectNonMatchedStudents() throws CommonException {
        //查找
        List<StudentModel> modelList = studentService.selectNonMatchedStudents();
        if(modelList == null || modelList.size() == 0){
            throw new CommonException(EmCommonError.ALL_STUDENT_MATCHED);
        }
        //转换
        List<StudentVO> voList = new ArrayList<>();
        for (StudentModel studentModel:modelList
             ) {
            voList.add(studentController.convertFromModel(studentModel));
        }
        //返回
        return CommonReturnType.create(voList);
    }

    @PostMapping("addSingleStudent")
    @ApiOperation("添加单个学生信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "学号",required = true),
            @ApiImplicitParam(name = "name",value = "姓名",required = true),
            @ApiImplicitParam(name = "gender",value = "性别",required = true),
            @ApiImplicitParam(name = "major",value = "专业",required = true),
            @ApiImplicitParam(name = "politic",value = "政治面貌",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true),
            @ApiImplicitParam(name = "describe",value = "个人简介")
    })
    public CommonReturnType addSingleStudent(StudentVO studentVO) throws CommonException {
        //判空
        if(studentVO == null){
            throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
        }
        //insert数据
        studentService.addSingleStudent(studentController.convertFromViewObject(studentVO));
        //返回成功
        return CommonReturnType.create(null);
    }

    @PostMapping("addSingleTeacher")
    @ApiOperation("添加单个老师信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "工号",required = true),
            @ApiImplicitParam(name = "name",value = "姓名",required = true),
            @ApiImplicitParam(name = "gender",value = "性别",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "phone",value = "手机号码",required = true),
            @ApiImplicitParam(name = "direction",value = "研究方向",required = true),
            @ApiImplicitParam(name = "describe",value = "个人简介")
    })
    public CommonReturnType addSingleTeacher(TeacherVO teacherVO) throws CommonException {
        //判空
        if(teacherVO == null){
            throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
        }
        //insert数据
        teacherService.addSingleTeacher(teacherController.convertFromViewObject(teacherVO));
        //返回成功
        return CommonReturnType.create(null);
    }

    @GetMapping("selectSuccess")
    @ApiOperation("查看互选成功师生列表接口")
    public CommonReturnType selectSuccess() throws CommonException {
        //查找
        List<SuccessModel> successModels = studentService.selectSuccessStudents();
        //转换
        List<SuccessVO> list = new ArrayList<>();
        for (SuccessModel successModel:successModels
             ) {
            list.add(convertFromModel(successModel));
        }
        //返回
        return CommonReturnType.create(list);
    }

    /**
    * @Description 转换为SuccessVO
    * @param successModel
    * @Return com.wu.vms.controller.viewobject.SuccessVO
    */
    public SuccessVO convertFromModel(SuccessModel successModel){
        if(successModel == null) return null;
        SuccessVO successVO = new SuccessVO();
        BeanUtils.copyProperties(successModel,successVO);
        return successVO;
    }
}
