package club.dlblog.mail.config;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * mail内容构造器
 * @author machenike
 */
public class MailContentBuilder {

    /**
     * 内容
     */
    private String content;

    /**
     * 接收者
     */
    private String to;

    /**
     * 发送者
     */
    private String from;

    /**
     * 主题
     */
    private String subject;

    /**
     * cc
     */
    private String[] cc;

    /**
     * 链接文件
     */
    private Map<String, File> linkFiles;


    /**
     * 是否类型为html
     */
    private boolean isHtml = false;

    /**
     * 构建content
     * @param content
     * @return
     */
    public MailContentBuilder content(String content){
        this.content = content;
        return  this;
    }
    public String  content(){
        return  content;
    }

    /**
     * 构建接收者
     * @param to
     * @return
     */
    public MailContentBuilder to(String to){
        this.to = to;
        return  this; 
    }

    public String to(){
        return  to;
    }

    /**
     * 构建发送者
     * @param from
     * @return
     */
    public MailContentBuilder from(String from){
        this.from = from;
        return  this;
    }

    public String from(){
        return from;
    }


    /**
     * 构建主题
     * @param suject
     * @return
     */
    public MailContentBuilder subject(String suject){
        this.subject =suject;
        return this;
    }

    public String subject(){
        return subject;
    }

    /**
     * 构建cc
     * @param cc
     * @return
     */
    public MailContentBuilder cc(String[] cc){
        this.cc = cc;
        return this;
    }

    public String[] cc(){
        return cc;
    }

    /**
     * 链接文件
     * @param files
     * @return
     */
    public MailContentBuilder linkFiles(Map<String,File> files){
        this.linkFiles = files;
        return this;
    }

    public Map<String,File> linkFiles(){
        return linkFiles;
    }


    /**
     * 构建isHtml
     * @param isHtml
     * @return
     */
    public MailContentBuilder isHtml(boolean isHtml){
        this.isHtml = isHtml;
        return  this;
    }

    public boolean isHtml(){
        return isHtml;
    }

    /**
     * 私有化构造方法
     */
    private MailContentBuilder(){
    }


    /**
     * 实例对象获取方法
     * @return
     */
    public static MailContentBuilder getInstance(){
        MailContentBuilder builder = new MailContentBuilder();
        return builder;
    }

    /**
     * 集成Helper，中间操作
     * @param helper
     */
    public void setInvocation(MimeMessageHelper helper){
        try {
            if(!StringUtils.isEmpty(from)){
                helper.setFrom(from);
            }
            if(!StringUtils.isEmpty(to)){
                helper.setTo(to);
            }
            if(!StringUtils.isEmpty(subject)){
                helper.setSubject(subject);
            }
            if(!StringUtils.isEmpty(content)){
                helper.setText(content, isHtml);
            }
            if(linkFiles!=null){
                Set<String>  keySet = linkFiles.keySet();
                for (String key:keySet){
                    helper.addAttachment(key,linkFiles.get(key));
                }
            }
            if(cc!=null&&cc.length>0){
                helper.setCc(cc);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
