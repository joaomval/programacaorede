
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public String sendMail(String mailServer, String subject, String to, String from, String mensagem) throws AddressException, MessagingException {

        Properties props = System.getProperties();
        /*props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");*/
        //testes
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.mime.charset", "ISO-8859-1");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props);//recebe props

        InternetAddress destinatario = new InternetAddress(to);
        InternetAddress remetente = new InternetAddress(from);

        Message msg = new MimeMessage(session);
        msg.setSentDate(new Date());//novo
        msg.setFrom(remetente);
        msg.setRecipient(Message.RecipientType.TO, destinatario);
        msg.setSubject(subject);
        msg.setContent(mensagem.toString(), "text/HTML");

        Transport transport = session.getTransport("smtp");
        transport.connect(mailServer, "seu_Usuario", "Sua_senha");
        msg.saveChanges();
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        return "<center><h2>email enviado com sucesso!!</h2></center>";
    }
}
