package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.repository.IssueLogRepository;
import fhi360.it.assetverify.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        int closingStock = Integer.parseInt(issueLog.getOpeningBalance()) + Integer.parseInt(issueLog.getQuantityReceived()) - Integer.parseInt(issueLog.getQuantityIssued())
                - Integer.parseInt(issueLog.getLosses()) + Integer.parseInt(issueLog.getPositiveAdjustment()) - Integer.parseInt(issueLog.getNegativeAdjustment());
        issueLog.setClosingStock(String.valueOf(closingStock));
        issueLog.setStockBalance(String.valueOf(closingStock));
        issueLog.setClosingStock(String.valueOf(closingStock));

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

    @GetMapping("issuelogs/invent/{itemDescription}")
    public List<IssueLog> getIssueLogByItemDescription(@PathVariable String itemDescription) {
        return issueLogRepository.findByItemDescriptionContaining(itemDescription);
    }

    @GetMapping("search")
    public Page<IssueLog> search(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("page") int page) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, inputFormat);
        LocalDate end = LocalDate.parse(endDate, inputFormat);


        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);

        return issueLogService.searchByDate(formattedStartDate, formattedEndDate, PageRequest.of(page - 1, 10));
    }


//    @GetMapping("/api/export")
//    public void export(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletResponse httpResponse) throws IOException {
//        ByteArrayOutputStream baos =  issueLogService.exportToCSV(startDate, endDate);
//        writeStream(baos, httpResponse);
//    }

//    private void writeStream(ByteArrayOutputStream baos, HttpServletResponse response) throws IOException {
//
//        response.setHeader("Content-Type", "application/octet-stream");
//
//        response.setHeader("Content-Length", Integer.valueOf(baos.size()).toString());
//
//        OutputStream outputStream = response.getOutputStream();
//
//        outputStream.write(baos.toByteArray());
//
//        outputStream.close();
//
//        response.flushBuffer();
//
//    }


    @GetMapping("exports")
    public void exportToCSV(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletResponse response) throws IOException {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, df);
        LocalDate end = LocalDate.parse(endDate, df);

        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);

        List<IssueLog> issueLogs = issueLogRepository.findByDateBetween(formattedStartDate, formattedEndDate);

        // Create a StringBuilder to store the CSV content
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Date,Warehouse Name,Item Description, Voucher or Ref. Number,Received from,Issued to, Batch Number, Expiry date, Quantity received, Quantity issued, Stock balance, Issuedto email, Phone, Dispatched Location  \n"); // Replace with actual column names

        // Append each IssueLog entry as a CSV row
        for (IssueLog issueLog : issueLogs) {
            csvContent.append(issueLog.getDate()).append(",");
            csvContent.append(issueLog.getWarehouseName()).append(",");
            csvContent.append(issueLog.getItemDescription()).append(",");
            csvContent.append(issueLog.getVoucherOrRefNumber()).append(",");
            csvContent.append(issueLog.getReceivedFrom()).append(",");
            csvContent.append(issueLog.getIssuedTo()).append(",");
            csvContent.append(issueLog.getBatchNo()).append(",");
            csvContent.append(issueLog.getExpiryDate()).append(",");
            csvContent.append(issueLog.getQuantityReceived()).append(",");
            csvContent.append(issueLog.getQuantityIssued()).append(",");
            csvContent.append(issueLog.getStockBalance()).append(",");
            csvContent.append(issueLog.getIssuedToEmail()).append(",");
            csvContent.append(issueLog.getPhone()).append(",");
            csvContent.append(issueLog.getDispatchedLocation()).append("\n");
            // Append additional properties as needed
        }

        // Set the response headers for CSV file download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=export.csv");

        // Write the CSV content to the response output stream
        try (PrintWriter writer = response.getWriter()) {
            writer.write(csvContent.toString());
        }
    }
}