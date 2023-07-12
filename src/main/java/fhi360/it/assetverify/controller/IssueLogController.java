package fhi360.it.assetverify.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fhi360.it.assetverify.dto.InventoryDto;
import fhi360.it.assetverify.dto.IssueLogDto;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
        return issueLogService.getAllIssueLogs(pageable);
    }

    @PostMapping("issuelog")
    ResponseEntity<IssueLog> createIssueLog(@RequestBody IssueLog issueLog) {
        int closingStock = Integer.parseInt(issueLog.getOpeningBalance()) + Integer.parseInt(issueLog.getQuantityReceived())
                - Integer.parseInt(issueLog.getQuantityIssued()) - Integer.parseInt(issueLog.getLosses())
                + Integer.parseInt(issueLog.getPositiveAdjustment()) - Integer.parseInt(issueLog.getNegativeAdjustment());

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


    @PatchMapping("issuelog/{id}")
    public void updateIssueLog(@PathVariable("id") Long id, @Valid @RequestBody IssueLogDto issueLogDto) throws ResourceNotFoundException {
        System.out.println("Update Issue log with ID = " + id + "...");
        IssueLog issueLog = issueLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("issue log not found for this id :: " + id));
        int oldQuantityIssued = Integer.parseInt(issueLog.getQuantityIssued());
        int newQuantityIssued = Integer.parseInt(issueLogDto.getQuantityIssued());
        int balanceOfStock = Integer.parseInt(issueLog.getStockBalance()) + oldQuantityIssued;
        int newBalanceStock = balanceOfStock - newQuantityIssued;

        issueLog.setIssuedTo(issueLogDto.getIssuedTo());
        issueLog.setPhone(issueLogDto.getPhone());
        issueLog.setIssuedToEmail(issueLogDto.getIssuedToEmail());
        issueLog.setDispatchedLocation(issueLogDto.getDispatchedLocation());

        IssueLog issueLog1 = issueLogRepository.findById(id).get();
        issueLog1.setQuantityIssued(String.valueOf(newQuantityIssued));

        issueLog1.setClosingStock(String.valueOf(newBalanceStock));
        issueLog1.setStockBalance(String.valueOf(newBalanceStock));

        System.out.println("old issued " + oldQuantityIssued);
        System.out.println("balance Stock " + balanceOfStock);

        System.out.println("new issued " + newQuantityIssued);
        System.out.println("new balance  " + newBalanceStock);

        issueLog.setStockBalance(String.valueOf(newBalanceStock));
        Inventory inventory = inventoryRepository.findById(issueLog.getInventoryId()).orElse(null);
        if (inventory != null) {
            inventory.setClosingStock(String.valueOf(newBalanceStock));
            inventory.setStockOnHand(String.valueOf(newBalanceStock));
            inventory.setStockBalance(String.valueOf(newBalanceStock));
            inventory.setOpeningBalance(String.valueOf(newBalanceStock));
            inventory.setQuantityIssued(String.valueOf(newQuantityIssued));

            System.out.println("Opening balance:: " + balanceOfStock);
        }
        final IssueLog updatedIssueLog = issueLogRepository.save(issueLog);
        System.out.println("Updated Inventory " + updatedIssueLog);
//        return issueLogRepository.save(updatedIssueLog);
    }


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

    @GetMapping(value = "issuelog/exports/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void exportToPdf(HttpServletResponse response,
                            @RequestParam String startDate,
                            @RequestParam String endDate) throws IOException, DocumentException {
        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=search_results.pdf");

        // Convert start and end dates to LocalDate objects
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, df);
        LocalDate parsedEndDate = LocalDate.parse(endDate, df);

        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = parsedStartDate.format(desiredFormat);
        String formattedEndDate = parsedEndDate.format(desiredFormat);

        // Your logic to retrieve data based on start and end dates
        List<IssueLog> data = getData(formattedStartDate, formattedEndDate);

        // Create a new document
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        // Open the document
        document.open();

        // Add content to the document
        Font headingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        Paragraph heading = new Paragraph("Search Results", headingFont);
        heading.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(heading);

        Paragraph content = new Paragraph("Your search results go here.", normalFont);
        document.add(content);

        // Create a table to display the data
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // Add table headers
        table.addCell("Date");
        table.addCell("Warehouse Name");
        table.addCell("Item Description");
        table.addCell("Voucher OR Ref No.");

        // Add table rows with data
        for (IssueLog item : data) {
            table.addCell(item.getDate());
            table.addCell(item.getWarehouseName());
            table.addCell(item.getItemDescription());
            table.addCell(item.getVoucherOrRefNumber());
        }

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();
    }

    // Your logic to retrieve data based on start and end dates
    // Replace this with your actual data retrieval logic
    private List<IssueLog> getData(String startDate, String endDate) {
        // Replace issueLogRepository with your actual repository or service class
        return issueLogRepository.findByDateBetween(startDate, endDate);
    }
}