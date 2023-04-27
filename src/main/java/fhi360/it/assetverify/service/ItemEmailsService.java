package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class ItemEmailsService {
    private final JavaMailSender javaMailSender;

    public void sendItemEmail(final Item item) throws MessagingException {
        final MimeMessage msg = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(item.getRequesterEmail());
        helper.setSubject("Confirm item " + item.getStatus());
        helper.setText("<tbody>\n\t\t\t\t<tr>\n\t\t\t\t\t<td valign=\"top\">\n\t\t\t\t\t\t<div>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr height=\"10\"><td height=\"10\" style=\"min-height:10px;\">&nbsp;</td></tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td valign=\"top\">\n\t\t\t\t\t\t<div style=\"font-family:Helvetica Neue, arial, helvetica, freesans, sans-serif;color:#000000;color:#575756;\">\n\t\t\t\t\t\t\tHello \n" + item.getRequestedBy() + ",\t\t\t\t\t\t</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr height=\"10\"><td height=\"10\" style=\"min-height:10px;\">&nbsp;</td></tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td valign=\"top\">\n\t\t\t\t\t\t<div style=\"font-family:Helvetica Neue, arial, helvetica, freesans, sans-serif;color:#000000;color:#575756;\">\n\t\t\t\t\t\t\tAn item has been " + item.getStatus() + " under your name, details are below:\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td valign=\"top\">\n\t\t\t\t\t\t<div style=\"font-family:Helvetica Neue, arial, helvetica, freesans, sans-serif;color:#000000;color:#575756;\">\n\t\t\t\t<br>\n\t\t\t\t\t\t\t<b>\tDate : \t</b>\t   ----------------------------------------  " + item.getDate() + "\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t<b>\tDescription :\t</b>\t ------------------------------  " + item.getDescription().toUpperCase() + "\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t\t<b>\tQuantity :\t</b>\t-----------------------------------  " + item.getQuantity().toUpperCase() + " piece(s)\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t\t<b>\tRequested By :\t</b>\t --------------------------- " + item.getRequestedBy().toUpperCase() + "\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t\t<b>\tEmail :\t</b>\t--------------------------------------- " + item.getRequesterEmail() + "\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t\t<b>\tAuthorized By :\t</b>\t ---------------------------  " + item.getAuthorizedBy().toUpperCase() + "\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t\t\t<b>\tStatus :\t</b>\t  --------------------------------------- " + item.getStatus().toUpperCase() + "\n\t\t\t\t<br>\n\t\t\t\t\t\t\tThank you\n\t\t\t\t<br>\n\t\t\t\t<br>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n</td></tr>\n\t\t\t\t<tr height=\"10\"><td height=\"10\" style=\"min-height:10px;\">&nbsp;</td></tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td valign=\"top\">\n\t\t\t\t\t\t<div style=\"font-family:Helvetica Neue, arial, helvetica, freesans, sans-serif;color:#000000;font-size:14px;font-weight:bold;\">\n\t\t\t\t\t\t\tFor Support Contact: globalservicedesk@fhi360.org\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\n\n\n\n\t\t\t\t\t\t\t<img src=\"https://media-exp1.licdn.com/dms/image/C4D1BAQFl07u_40yfYw/company-background_10000/0/1624546064903?e=2159024400&v=beta&t=KtIapO9HHvIQg6SfBqaLkOtCHfEi8Cqq7gVR4T_o7Mw\" border=\"0\" alt=\"fhi\" style=\"margin:0;margin-left: 0px; max-width:388px;max-height:182px;\">\n\t\t\t</tbody>", true);
        this.javaMailSender.send(msg);
    }
}
