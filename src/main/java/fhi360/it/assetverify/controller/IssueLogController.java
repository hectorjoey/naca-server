package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.BinCard;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.repository.IssueLogRepository;
import fhi360.it.assetverify.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class IssueLogController {
    private final IssueLogRepository issueLogRepository;
    private final IssueLogService issueLogService;
    private final InventoryRepository inventoryRepository;

    @GetMapping("all-issuelogs")
    List<IssueLog> getIssueLogs() {
        return issueLogService.getAllIssueLogs();
    }

    @GetMapping("issuelogs")
    public Page<IssueLog> getAllBinCards(Pageable pageable) {
        return issueLogRepository.findByOrderByIdAsc(pageable);
    }


    @PostMapping("issuelog")
    ResponseEntity<IssueLog> createIssueLog(@RequestBody IssueLog issueLog) {
        int closingStock = Integer.parseInt(issueLog.getOpeningBalance()) + Integer.parseInt(issueLog.getQuantityReceived()) - Integer.parseInt(issueLog.getQuantityIssued());
        int lossesAndAdjustments = Integer.parseInt(issueLog.getLosses()) + Integer.parseInt(issueLog.getPositiveAdjustment()) + Integer.parseInt(issueLog.getNegativeAdjustment());
        int stockOnHand = Integer.parseInt(issueLog.getOpeningBalance()) + Integer.parseInt(issueLog.getQuantityReceived()) - lossesAndAdjustments;
        issueLog.setClosingStock(String.valueOf(closingStock));
        issueLog.setStockBalance(String.valueOf(closingStock));
        issueLog.setClosingStock(String.valueOf(closingStock));
        issueLog.setLossesAndAdjustments(String.valueOf(lossesAndAdjustments));

        String quantityIssued = issueLog.getQuantityIssued();

        Inventory inventory = inventoryRepository.findById(issueLog.getInventoryId()).orElse(null);
        if (inventory != null) {
            inventory.setClosingStock(String.valueOf(closingStock));
            inventory.setStockOnHand(String.valueOf(closingStock));
            inventory.setStockBalance(String.valueOf(closingStock));
            inventory.setOpeningBalance(String.valueOf(closingStock));
            inventory.setQuantityIssued(quantityIssued);
        }
        return new ResponseEntity<>(issueLogRepository.save(issueLog), HttpStatus.CREATED);
    }

    @GetMapping("issuelog/{id}")
    public ResponseEntity<IssueLog> getBinCardById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        IssueLog issueLog = issueLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IssueLog not found for this id :: " + id));
        return ResponseEntity.ok().body(issueLog);
    }

    // For searching binCard
    @GetMapping("Issuelog/{keyword}")
    public Page<IssueLog> getAllIssueLogs(Pageable pageable, @PathVariable("keyword") String keyword) {
        return issueLogRepository.findAll(pageable, keyword);
    }

    @GetMapping("issuelog/inventory/{inventoryId}")
    public List<IssueLog> getIssueLogByInventoryId(@PathVariable Long inventoryId) {
        return issueLogRepository.findByInventoryId(inventoryId);
    }
}