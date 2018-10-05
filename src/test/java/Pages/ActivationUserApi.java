package Pages;

import Utils.Tools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

public class ActivationUserApi extends Tools{

    public ActivationUserApi(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static final String host = "pop.gmail.com";// change accordingly
    public static final String mailStoreType = "pop3s";
    public static final String username = "lihoy@singree.com";//"lihoy@singree.com";// change accordingly
    public static final String password = "xJtcQ2awcG26YyYw";//"lihoy92ivan";// change accordingly
    private static final Logger log = Logger.getLogger(ActivationUserApi.class);

    public static String getActivationLink() throws Exception {
        String reference = null;
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, password);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        Message[] messages = emailFolder.getMessages();
        int i = 0;
        for (Message message : messages) {
            System.out.println("---------------------------------");
            i++;
            if (message.getSubject().contains("Registration successfull")) {
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                String messageBody = message.getContent().toString();
                Document document = Jsoup.parseBodyFragment(messageBody);
                Elements link = document.body().getElementsByAttributeValueContaining("href", "https://testing.opporty.com/activate/user/");
                System.out.println(link);
                reference = link.attr("href").toString();
                System.out.println(reference);
            }
        }
        return reference;
    }

    public void openActivationLink() throws Exception {
        String url = getActivationLink();
        driver.get(url);
        //sleep(5);
    }

    public void deleteActivationMessage() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, password);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
        Message[] messages = emailFolder.getMessages();
        int i =0;
        for (Message message:messages){
            i++;
            //System.out.println(message.getSubject());
            if (message.getSubject().contains("Registration successfull")){
                message.setFlag(Flags.Flag.DELETED, true);
                emailFolder.close(true);
                System.out.println("Message: Registration successfull - WAS DELETED!");
                log.info("Registration successfull - WAS DELETED!");
                break;
            }
        }
    }
}

