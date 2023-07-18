package fhi360.it.assetverify.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        response.setHeader("Content-Disposition", "attachment; filename=health-commodities.csv");

        // Write the CSV content to the response output stream
        try (PrintWriter writer = response.getWriter()) {
            writer.write(csvContent.toString());
        }
    }

    @GetMapping("issuelog/export-to-pdf")
    public ResponseEntity<byte[]> exportToPDF(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            HttpServletResponse response) {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate start = LocalDate.parse(startDate, df);
            LocalDate end = LocalDate.parse(endDate, df);

            DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedStartDate = start.format(desiredFormat);
            String formattedEndDate = end.format(desiredFormat);

            List<IssueLog> issueLogs = issueLogRepository.findByDateBetween(formattedStartDate, formattedEndDate);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4); // Use default page size

            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Create a table with 13 columns
            PdfPTable table = new PdfPTable(14);
            table.setWidthPercentage(100);

            // Add table headers
            addTableHeader(table);

            // Add table rows
            addTableRows(table, issueLogs);

            // Add the table to the document
            document.add(table);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "health-commodities-logs.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (DateTimeParseException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell(createCell("Date", true));
        table.addCell(createCell("Warehouse Name", true));
        table.addCell(createCell("Item Description", true));
        table.addCell(createCell("Voucher or Ref. Number", true));
        table.addCell(createCell("Received from", true));
        table.addCell(createCell("Issued to", true));
        table.addCell(createCell("Batch Number", true));
        table.addCell(createCell("Expiry date", true));
        table.addCell(createCell("Quantity received", true));
        table.addCell(createCell("Quantity issued", true));
        table.addCell(createCell("Stock balance", true));
        table.addCell(createCell("Issuedto email", true));
        table.addCell(createCell("Phone", true));
        table.addCell(createCell("Dispatched Location", true));
    }

    private void addTableRows(PdfPTable table, List<IssueLog> issueLogs) {
        for (IssueLog issueLog : issueLogs) {
            table.addCell(createCell(issueLog.getDate(), false));
            table.addCell(createCell(issueLog.getWarehouseName(), false));
            table.addCell(createCell(issueLog.getItemDescription(), false));
            table.addCell(createCell(issueLog.getVoucherOrRefNumber(), false));
            table.addCell(createCell(issueLog.getReceivedFrom(), false));
            table.addCell(createCell(issueLog.getIssuedTo(), false));
            table.addCell(createCell(issueLog.getBatchNo(), false));
            table.addCell(createCell(issueLog.getExpiryDate(), false));
            table.addCell(createCell(issueLog.getQuantityReceived(), false));
            table.addCell(createCell(issueLog.getQuantityIssued(), false));
            table.addCell(createCell(issueLog.getStockBalance(), false));
            table.addCell(createCell(issueLog.getIssuedToEmail(), false));
            table.addCell(createCell(issueLog.getPhone(), false));
            table.addCell(createCell(issueLog.getDispatchedLocation(), false));
        }
    }

    private PdfPCell createCell(String content, boolean isHeader) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(2);

        if (isHeader) {
            cell.setPhrase(new Paragraph(content, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6)));
        } else {
            cell.setPhrase(new Paragraph(content, FontFactory.getFont(FontFactory.HELVETICA, 6)));
        }

        return cell;
    }

}