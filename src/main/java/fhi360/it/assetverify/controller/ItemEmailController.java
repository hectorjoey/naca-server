package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.model.Item;
import fhi360.it.assetverify.service.ItemEmailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = { "https://asset-verify.netlify.app" })
@RestController
@RequestMapping({ "/api/v1/" })
@RequiredArgsConstructor
@Slf4j
public class ItemEmailController
{
    private final ItemEmailsService emailService;
    
    @PostMapping({ "sendEmails" })
    public String send(@Valid @RequestBody final Item item) throws MessagingException {
        this.emailService.sendItemEmail(item);
        return "Email sent successfully";
    }
}
