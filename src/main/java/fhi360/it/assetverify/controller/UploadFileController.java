
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.message.ResponseMessage;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.repository.UserRepository;
import fhi360.it.assetverify.service.FileServices;
import fhi360.it.assetverify.service.GeneralEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:3000"})
//@CrossOrigin(origins = "https://naca-client.netlify.app/")
@Controller
@RequestMapping({"api/v1/"})
@RequiredArgsConstructor
@Slf4j
public class UploadFileController {
    private final UserRepository userRepository;
    private final FileServices fileServices;
    private final AssetRepository assetRepository;
    private final GeneralEmailService generalEmailService;

    @PostMapping({"/uploadAsset"})
    public ResponseEntity<ResponseMessage> uploadAssetFile(@RequestParam("files") final MultipartFile files) {
        String message = "";
        try {
            log.debug("AssetFile::{}", files.getOriginalFilename());
            this.fileServices.save(files);
            message = "Uploaded the file successfully: " + files.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + files.getOriginalFilename() + "!";
            log.debug(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping({"/uploadItem"})
    public ResponseEntity<ResponseMessage> uploadItemFile(@RequestParam("files") final MultipartFile files) {
        String message = "";
        try {
            log.debug("ItemFile::{}", files.getOriginalFilename());
            this.fileServices.saveItem(files);
            message = "Uploaded the file successfully: " + files.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + files.getOriginalFilename() + "!";
            log.debug(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping({"/uploadInventory"})
    public ResponseEntity<ResponseMessage> uploadInventoryFile(@RequestParam("files") final MultipartFile files) {
        String message = "";
        try {
            log.debug("InventoriesFiles::{}", files.getOriginalFilename());
            this.fileServices.saveInventories(files);
            message = "Uploaded the file successfully: " + files.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + files.getOriginalFilename() + "!";
            log.debug(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
