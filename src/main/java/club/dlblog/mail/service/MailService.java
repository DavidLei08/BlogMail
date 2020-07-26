package club.dlblog.mail.service;

import club.dlblog.mail.config.MailConfigBean;
import club.dlblog.mail.config.MailContentBuilder;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.io.File;
import java.util.Map;

/**
 * MailServiceImpl 邮件发送服务实现类
 * @author machenike
 */
public interface MailService {

    /**
     * 配置取得
     * @return mail邮箱配置
     */
    MailProperties getConfig();

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @param linkFiles 链接文件
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text, Map<String, File > linkFiles);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @param cc  邮件cc
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text,String[] cc);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @param cc 邮件cc
     * @param linkFiles 链接文件
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text,String[] cc,Map<String, File > linkFiles);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @param linkFiles 链接文件
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template,Map<String, File > linkFiles);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @param cc 邮件cc
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template,String[] cc);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @param cc 邮件cc
     * @param linkFiles 链接文件
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template,String[] cc,Map<String, File > linkFiles);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @param configBean 邮箱配置
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text, MailConfigBean configBean);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param text 文本内容
     * @param linkFiles 链接文件
     * @param configBean 邮箱配置
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, String text,Map<String, File > linkFiles,MailConfigBean configBean);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @param configBean 邮箱配置
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template,MailConfigBean configBean);

    /**
     * 邮件发送
     * @param to 接收者
     * @param subject 主题
     * @param params 模板参数
     * @param template 模板文件
     * @param linkFiles 链接文件
     * @param configBean 邮箱配置
     * @return true -成功  false -失败
     */
    boolean send(String to, String subject, Map<String,Object> params, String template,Map<String, File > linkFiles,MailConfigBean configBean);

    /**
     *  邮件发送
     * @param builder 邮件内容构造器
     * @return true -成功  false -失败
     */
    boolean send(MailContentBuilder builder);

    /**
     * 邮件发送
     * @param builder 邮件内容构造器
     * @param configBean 邮件配置
     * @return true -成功  false -失败
     */
    boolean send(MailContentBuilder builder,MailConfigBean configBean);


}
