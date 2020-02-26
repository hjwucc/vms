package com.wu.vms.thread;

import com.wu.vms.dataobject.StudentDO;
import com.wu.vms.dataobject.TeacherDO;
import com.wu.vms.mapper.StudentDOMapper;
import com.wu.vms.mapper.TeacherDOMapper;
import com.wu.vms.service.model.StudentModel;
import com.wu.vms.service.model.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/24 11:27
 * @description：发送邮件线程
 */

public class EmailRunnable implements Runnable {

    private StudentDO studentDO;
    private TeacherModel teacherModel;
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    public EmailRunnable(StudentDO studentDO, TeacherModel teacherModel, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.studentDO = studentDO;
        this.teacherModel = teacherModel;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void run() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(studentDO.getEmail());
            helper.setFrom("wmnihaoya@qq.com");
            helper.setSubject("平顶山学院导师双选系统：通知");
            Context ctx = new Context();
            ctx.setVariable("name",teacherModel.getName());
            ctx.setVariable("phone",teacherModel.getPhone());
            ctx.setVariable("email",teacherModel.getEmail());
            String mail = templateEngine.process("email.html", ctx);
            helper.setText(mail, true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送邮件失败");
        }
    }
}
