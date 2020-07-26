package club.dlblog.mail.service.impl;

import club.dlblog.mail.config.MailConfigBean;
import club.dlblog.mail.config.MailContentBuilder;
import club.dlblog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;


/**
 * MailServiceImpl 邮件发送服务实现类
 * @author machenike
 */
@Service
public class MailServiceImpl implements MailService {


    /**
     * 邮件发送者
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 用来发送模版邮件
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 邮箱配置
     */
    @Autowired
    private MailProperties mailProperties;

    @Override
    public MailProperties getConfig() {
        return mailProperties;
    }

    @Override
    public boolean send(String to, String subject, String text) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance().to(to).from(from).subject(subject).content(text);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, String text, Map<String, File> linkFiles) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(text).linkFiles(linkFiles);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, String text, String[] cc) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(text).cc(cc);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, String text, String[] cc, Map<String, File> linkFiles) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(text).cc(cc).linkFiles(linkFiles);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance().to(to).from(from).subject(subject).content(emailContent);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template, Map<String, File> linkFiles) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(emailContent).linkFiles(linkFiles);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template, String[] cc) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(emailContent).cc(cc);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template, String[] cc, Map<String, File> linkFiles) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(emailContent).cc(cc).linkFiles(linkFiles);
        sendResult = this.send(builder);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, String text, MailConfigBean configBean) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(text);
        sendResult = this.send(builder,configBean);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, String text, Map<String, File> linkFiles, MailConfigBean configBean) {
        boolean sendResult;
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(text).linkFiles(linkFiles);
        sendResult = this.send(builder,configBean);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template, MailConfigBean configBean) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(emailContent);
        sendResult = this.send(builder,configBean);
        return sendResult;
    }

    @Override
    public boolean send(String to, String subject, Map<String, Object> params, String template, Map<String, File> linkFiles, MailConfigBean configBean) {
        boolean sendResult;
        //取得模板内容
        String emailContent = getTemplateContent(template,params);
        //构造邮件内容
        MailContentBuilder builder = MailContentBuilder.getInstance()
                .to(to).from(from).subject(subject).content(emailContent).linkFiles(linkFiles);
        sendResult = this.send(builder,configBean);
        return sendResult;
    }

    @Override
    public boolean
    send(MailContentBuilder builder) {
        boolean result = true;
        try {
            //构造message
            MimeMessage message = mailSender.createMimeMessage();
            //取得MessaeHelper
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //拦截预处理
            builder.setInvocation(helper);
            mailSender.send(message);
        } catch (Throwable e) {
            e.printStackTrace();
            result =false;
        }
        return result;
    }

    @Override
    public boolean send(MailContentBuilder builder, MailConfigBean configBean) {
        JavaMailSenderImpl sender = null;
        boolean result = true;
        try {
            //构造sender
            sender = getMailSender(configBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            result =false;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            result =false;
        }
        try {
           //构造message
            MimeMessage message = sender.createMimeMessage();
            //取得MessaeHelper
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //拦截预处理
            builder.setInvocation(helper);
            sender.send(message);
        } catch (Throwable e) {
            e.printStackTrace();
            result =false;
        }
        return result;
    }

    /**
     * 通过html模板生成邮件内容
     * @param template 模板文件
     * @param params 模板参数
     * @return 模板解析完了内容
     */
    private String getTemplateContent(String template,Map<String,Object> params){
        //初始化模板上下文
        Context context = new Context();
        //设定模板参数
        context.setVariables(params);
        //取得模板内容
        String emailContent = templateEngine.process(template, context);
        return  emailContent;
    }

    /**
     * 通过配置取得邮件sender
     * @param configBean 邮箱配置
     * @return 邮件发送器
     * @throws IllegalAccessException 非法访问异常
     * @throws NoSuchFieldException  字段不存在异常
     */
    private JavaMailSenderImpl getMailSender(MailConfigBean configBean) throws IllegalAccessException, NoSuchFieldException {
        //初始化邮件发射器实现类
        JavaMailSenderImpl newSender =  new JavaMailSenderImpl();
        //字段取出
        Field[] fields = configBean.getClass().getFields();
        //迭代字段
        for ( Field field:fields){
            //设定允许访问
            field.setAccessible(true);
            //字段取得
            Field tmpField = JavaMailSenderImpl.class.getField(field.getName());
            //设定允许访问
            tmpField.setAccessible(true);
            //发送器设定
            tmpField.set(newSender,field.get(configBean));
        }
        return newSender;
    }



}
