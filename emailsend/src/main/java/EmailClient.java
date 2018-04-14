import org.apache.commons.io.FileUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailClient {
    private static String ENCODE = "UTF-8";
    private JavaMailSenderImpl mailSender;

    public EmailClient() throws Exception {
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost("mx01.vetech.cn");
        this.mailSender.setPort(25);
        this.mailSender.setUsername("8370@vetech.cn");
        this.mailSender.setPassword("xsy15391539");
        this.mailSender.setDefaultEncoding(ENCODE);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.connectiontimeout", "15000");
        properties.setProperty("mail.smtp.timeout", "15000");
        if (this.mailSender.getPort() == 465) {
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        this.mailSender.setJavaMailProperties(properties);
    }

    public void sendMsg() throws Exception {
        javax.mail.internet.MimeMessage message = null;

        try {
            message = this.mailSender.createMimeMessage();
            MimeMessageHelper t = new MimeMessageHelper(message, false, ENCODE);
            t.setFrom("6666@vetech.cn", "baron");//发件人及名称
            t.setReplyTo("8471@vetech.cn");//设置回复邮箱

            t.setSubject("推送异常提醒");//设置主题

            //设置邮件内容
            String file = "推送异常提醒正文";
            if (false) {//如果是HTML，则需要从读取文件加载
                //EmailDataResolve i = new EmailDataResolve(WebUtils.getRootPath(""), t);
                //file = i.resolveDataPath(file);
                // t.setText(file, true);
            } else {
                String text = readTxt("D:\\TR17081702048_4137.log", "UTF-8");


                // t.setText(file, false);
                t.setText(text);
            }

            //设置收件人

            t.setTo(new String[]{"8471@vetech.cn"});
            //设置抄送人
            if (false) {
                t.setCc("");
            }

            //设置附件
            if (false) {
                File var9 = new File("");
                t.addAttachment(MimeUtility.encodeWord(var9.getName()), var9);
            }
        } catch (Exception var7) {
            throw new Exception(var7);
        }

        //开始发送邮件
        long var8 = System.currentTimeMillis();
        int var10 = 0;

        while (var10 < 3) {
            try {
                this.mailSender.send(message);
                break;
            } catch (Exception var6) {
                var6.printStackTrace();
                System.out.println("第" + var10 + "次发送邮件失败");
                if (var10 == 2) {
                    throw new Exception(var6);
                }
                ++var10;
            }
        }
        System.out.println("发送耗时:" + (System.currentTimeMillis() - var8) + "毫秒");
    }

    public static void main(String[] args) throws Exception {

        System.out.println(readTxt("D:\\TR17081702048_4137.log", "UTF-8"));


        EmailClient emailClient = new EmailClient();
        emailClient.sendMsg();
    }


    public static String readTxt(String filePathAndName, String encoding) throws IOException {
        File file = new File(filePathAndName);
        return FileUtils.readFileToString(file, encoding);
    }


}
