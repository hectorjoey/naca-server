//
//package fhi360.it.assetverify.controller;
//
//import fhi360.it.assetverify.model.Asset;
//import fhi360.it.assetverify.service.AssetEmailsService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;
//
//@CrossOrigin(origins = {"http://localhost:3001"})
//@RestController
//@RequestMapping({"/api/v1/"})
//@RequiredArgsConstructor
//@Slf4j
//public class AssetEmailController {
//    private final AssetEmailsService emailService;
//
//    @PostMapping({"sendEmail"})
//    public String send(@Valid @RequestBody final Asset asset) throws MessagingException {
//        this.emailService.sendEmailWithAttachment(asset);
//        return "Email sent successfully";
//    }
//}
