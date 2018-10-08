package Pages;

import Utils.Tools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class ForgotPasswordApi extends Tools {

    public ForgotPasswordApi(WebDriver driver) throws IOException{
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final String host = "pop.gmail.com";// change accordingly
    public static final String mailStoreType = "pop3s";
    public static final String username = "lihoy@singree.com";// change accordingly
    public static final String passworduser = "xJtcQ2awcG26YyYw";// change accordingly

    public static String getForgetPassword() throws Exception {
        String reference = null;
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, passworduser);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        Message[] messages = emailFolder.getMessages();
        int i = 0;
        for (Message message : messages) {
            System.out.println("---------------------------------");
            i++;
            if (message.getSubject().contains("Password reset")) {
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                String messageBody = message.getContent().toString();
                Document document = Jsoup.parseBodyFragment(messageBody);
                Elements link = document.body().getElementsByAttributeValueContaining("href", "https://testing.opporty.com/reset-password/");
                System.out.println(link);
                reference = link.attr("href").toString();
                System.out.println(reference);
            }
        }
        return reference;
    }

    public void enterPassword() throws Exception {
        String url = getForgetPassword();
        driver.get(url);
        sleep(3);
    }

    public void deletePasswordMessage() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, passworduser);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
        Message[] messages = emailFolder.getMessages();
        int i = 0;
        for (Message message : messages) {
            i++;
            System.out.println(message.getSubject());
            if (message.getSubject().contains("Password reset")) {
                message.setFlag(Flags.Flag.DELETED, true);
                emailFolder.close(true);
                System.out.println("Message: Updated password - WAS DELETED!");
                break;

            }
        }
    }
}