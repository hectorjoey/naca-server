package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public void sendDetails(final Users users) throws MessagingException {
        final MimeMessage msg = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(users.getEmail());
        helper.addCc("schikeluba@epicact2.org");
        helper.setSubject("Account Creation Detail for " + users.getFirstname());
        helper.setText("<br><br>\n\t\t\t\t\t\t\tHi \n" + users.getFirstname() + ",\n<br>\n\t\t\t\t\t\t\tKindly find your login detail below as: \n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t<table rules=\"all\" style=\"border-color: #666;\" cellpadding=\"10\">\n\t\t<tr style=\"background: #eee;\">\n\t\t\t<td>Email</td>\n\t\t\t<td>Password</td>\n\t\t</tr>\n\t<tr style=\"border-color: #666;\"background: #eee;\" cellpadding=\"10\"><td>" + users.getEmail() + "<td>" + users.getPassword() + "</tr>\n\t\t</table>\n\t\t\t\t\t\t\tNote that you are advised to change your password after successful login. \n<br>Thank you,<br>\t\t\t\t\t\t\tFor Support Contact: globalservicedesk@fhi360.org\n\t\t\t</tbody>", true);
        this.javaMailSender.send(msg);
    }
}
