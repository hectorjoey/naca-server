////
//// Decompiled by Procyon v0.5.36
////
//
//package fhi360.it.assetverify.service;
//
//import fhi360.it.assetverify.model.Asset;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.time.LocalDate;
//
//@Service
//@RequiredArgsConstructor
//public class AssetEmailsService {
//    private final JavaMailSender javaMailSender;
//
//    public void sendEmailWithAttachment(final Asset asset) throws MessagingException {
//        final MimeMessage msg = this.javaMailSender.createMimeMessage();
//        final MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo(asset.getEmail());
//        helper.addBcc("OOloniyo@fhi360.org");
//        helper.addCc("schikeluba@epicact2.org");
//        helper.setSubject("Confirm Asset " + asset.getStatus());
//        helper.setText("<br><br>\n\t\t\t\t\t\t\tHi \n" + asset.getAssignee() + ",\n<br>\n\t\t\t\t\t\t\tAn Asset has been " + asset.getStatus().toLowerCase() + " under your name, details are below:\n\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</div>\n\t<table rules=\"all\" style=\"border-color: #666;\" cellpadding=\"10\">\n\t\t<tr style=\"background: #eee;\">\n\t\t\t<td>Description</td>\n\t\t\t<td>Manufacturer</td>\n\t\t\t<td>Asset ID</td>\n\t\t\t<td>Serial Number</td>\n\t\t\t<td>Model Number</td>\n\t\t\t<td>Assignee</td>\n\t\t\t<td>Email</td>\n\t\t\t<td>State</td>\n\t\t\t<td>Facility</td>\n\t\t\t<td>" + asset.getStatus() + " Date</td>\n\t\t\t<td>Status</td>\n\t\t</tr>\n\t<tr style=\"border-color: #666;\"background: #eee;\" cellpadding=\"10\"><td>" + asset.getDescription() + "<td>" + asset.getManufacturer() + "<td>" + asset.getAssetId() + "<td>" + asset.getSerialNumber() + "<td>" + asset.getModelNumber() + "<td>" + asset.getAssignee() + "<td>" + asset.getEmail() + "<td>" + asset.getStates() + "<td>" + asset.getFacility() + "<td>" + LocalDate.now() + "<td style=color:green>" + asset.getStatus() + "</tr>\n</tr>\n\t\t</table>\n<br>Thank you,<br>\t\t\t\t\t\t\tFor Support Contact: globalservicedesk@fhi360.org\n\t\t\t</tbody>", true);
//        this.javaMailSender.send(msg);
//    }
//}
