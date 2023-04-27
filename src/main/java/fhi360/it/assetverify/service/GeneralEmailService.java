package fhi360.it.assetverify.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class GeneralEmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmails() throws MessagingException {
        final MimeMessage msg = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("NAdhekoyibo@fhi360.org");
        helper.setTo("OOloniyo@fhi360.org");
        helper.setTo("schikeluba@epicact2.org");
        helper.setSubject("New Asset Upload");
        helper.setText("<br><br>\n\t\t\t\t\t\t\tHello Admin,\n<br>\n\t\t\t\t\t\t\tKindly note that Assets have been uploaded to the server\t\t\t\t\t\t</div>\n</tr>\n\t\t</table>\n<br>Thank you,<br>\t\t\t</tbody>", true);
        this.javaMailSender.send(msg);
    }
}
