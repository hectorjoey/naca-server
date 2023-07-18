package fhi360.it.assetverify.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import fhi360.it.assetverify.dto.AssetDto;
import fhi360.it.assetverify.exception.AlreadyExistsException;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping({"/api/v1/"})
@RequiredArgsConstructor
@Slf4j
public class AssetController {
    //    private final DeleteAssetService deleteAssetService;
//    private final AssetEmailsService assetEmailsService;
    private final AssetRepository assetRepository;
    private final AssetService assetService;

    @GetMapping({"assets"})
    public Page<Asset> getAllAssets(final Pageable pageable) {
        return this.assetRepository.findByOrderById(pageable);
    }

    @GetMapping({"assets/{keyword}"})
    public Page<Asset> getAllAsset(final Pageable pageable, @PathVariable("keyword") final String keyword) {
        return this.assetRepository.findAll(pageable, keyword);
    }

    @GetMapping({"all-assets"})
    List<Asset> getAssets() {
        return this.assetRepository.findAll();
    }

    @GetMapping({"all-assets/{id}"})
    public Optional<Asset> getAssetsById(@PathVariable("id") final Long id) {
        return this.assetRepository.findById(id);
    }

    @GetMapping("/states")
    public List<Asset> getAssetsByState() {
        return assetRepository.findAll();
    }

    @GetMapping({"asset/{id}"})
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") final Long id) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        return ResponseEntity.ok().body(asset);
    }

    @PostMapping({"asset/serial/{serialNumber}"})
    public Asset getByAssetSerialNumber(@PathVariable("serialNumber") final String serialNumber) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findBySerialNumber(serialNumber);
        if (asset == null) {
            throw new ResourceNotFoundException("Asset not found for this asset Tag " + serialNumber);
        }
        return this.assetRepository.findBySerialNumber(serialNumber);
    }

    @GetMapping({"asset/tag/{assetId}"})
    public Asset getByAssetTag(@PathVariable("assetId") final String assetId) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findByAssetId(assetId);
        if (asset == null) {
            throw new ResourceNotFoundException("Asset not found for this asset Tag " + assetId);
        }
        return this.assetRepository.findByAssetId(assetId);
    }

    @PostMapping({"asset"})
    public ResponseEntity<?> createAsset(@Valid @RequestBody final Asset asset) throws AlreadyExistsException, MessagingException {
        final Asset assetsID = this.assetRepository.findByAssetId(asset.getAssetId());
        final Asset assetsSerial = this.assetRepository.findBySerialNumber(asset.getSerialNumber());
        if (assetsID != null) {
            throw new AlreadyExistsException(String.format("Asset with assetsID %s already exist", asset.getAssetId()));
        }
        if (assetsSerial != null) {
            throw new AlreadyExistsException(String.format("Asset with Serial Number %s already exist", asset.getSerialNumber()));
        }
        return new ResponseEntity<>(this.assetService.save(asset), HttpStatus.CREATED);
    }

    @PatchMapping({"asset/{id}"})
    public Asset updateAsset(@PathVariable("id") final long id, @Valid @RequestBody final AssetDto asset) throws ResourceNotFoundException, MessagingException {
        log.debug("Update Asset with Id = {}", id);
        final Asset asset2 = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        asset2.setEmailAddress(asset.getEmailAddress());
        asset2.setCustodian(asset.getCustodian());
        asset2.setStatus(asset.getStatus());
        asset2.setPhone(asset.getPhone());
        asset2.setLocation(asset.getLocation());
        asset2.setStates(asset.getStates());
        asset2.setCondition(asset.getCondition());
        final Asset updatedAsset = this.assetRepository.save(asset2);
        log.debug("Updated Asset {}", updatedAsset);
        return this.assetRepository.save(updatedAsset);
    }

    @DeleteMapping({"asset/{id}"})
    public Map<String, Boolean> deleteAsset(@PathVariable("id") final Long id) throws ResourceNotFoundException, MessagingException {
        final Asset asset = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        this.assetRepository.delete(asset);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @GetMapping("asset-search")
//    public Page<Asset> search(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("page") int page) {
//        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate start = LocalDate.parse(startDate, inputFormat);
//        LocalDate end = LocalDate.parse(endDate, inputFormat);
//
//        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedStartDate = start.format(desiredFormat);
//        String formattedEndDate = end.format(desiredFormat);
//
//        PageRequest pageRequest = PageRequest.of(page, 100);
//        return assetService.searchByDateReceived(formattedStartDate, formattedEndDate, pageRequest);
//
//    }


//    @GetMapping("asset/exports")
//    public void exportToCSV(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletResponse response) throws IOException {
//
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate start = LocalDate.parse(startDate, df);
//        LocalDate end = LocalDate.parse(endDate, df);
//
//        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedStartDate = start.format(desiredFormat);
//        String formattedEndDate = end.format(desiredFormat);
//
//        List<Asset> assetLogs = assetRepository.findByDateReceivedBetween(formattedStartDate, formattedEndDate);
//
//        // Create a StringBuilder to store the CSV content
//        StringBuilder csvContent = new StringBuilder();
//        csvContent.append("Description,Category, Type,Asset ID,Serial Number, Date Received,  Funder, Model, States, Location, Custodian, Condition, Email Address, Phone, Status  \n"); // Replace with actual column names
//
//        // Append each IssueLog entry as a CSV row
//        for (Asset asset : assetLogs) {
//            csvContent.append(asset.getDescription()).append(",");
//            csvContent.append(asset.getCategory()).append(",");
//            csvContent.append(asset.getType()).append(",");
//            csvContent.append(asset.getAssetId()).append(",");
//            csvContent.append(asset.getSerialNumber()).append(",");
//            csvContent.append(asset.getDateReceived()).append(",");
//            csvContent.append(asset.getFunder()).append(",");
//            csvContent.append(asset.getModel()).append(",");
//            csvContent.append(asset.getStates()).append(",");
//            csvContent.append(asset.getLocation()).append(",");
//            csvContent.append(asset.getCustodian()).append(",");
//            csvContent.append(asset.getCondition()).append(",");
//            csvContent.append(asset.getEmailAddress()).append(",");
//            csvContent.append(asset.getPhone()).append(",");
//            csvContent.append(asset.getStatus()).append("\n");
//            // Append additional properties as needed
//        }
//
//        // Set the response headers for CSV file download
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=export.csv");
//
//        // Write the CSV content to the response output stream
//        try (PrintWriter writer = response.getWriter()) {
//            writer.write(csvContent.toString());
//        }
//    }

//    @GetMapping("asset-export")
//    public ResponseEntity<byte[]> exportToExcel(HttpServletResponse response) throws IOException {
//        List<Asset> assets = assetService.getAllAssets();
//
//        XSSFWorkbook workbook = assetService.createExcelFile(assets);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        byte[] excelContent = outputStream.toByteArray();
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=exported_models.xlsx");
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"exported_models.xlsx\"")
//                .body(excelContent);
//    }


    @GetMapping("/export-to-pdf")
    public ResponseEntity<byte[]> exportToPDF() {
        try {
            List<Asset> data = assetService.getAllAssets();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4.rotate()); // Set landscape orientation
            PdfWriter.getInstance(document, outputStream);

            document.open();
            addDataToPDF(document, data);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "data.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addDataToPDF(Document document, List<Asset> data) throws DocumentException {
        PdfPTable table = new PdfPTable(19); // Number of columns
        table.setWidthPercentage(100); // Set table width to 100% of the page

        // Set table headers
        table.addCell(createCell("Description", true));
        table.addCell(createCell("Category", true));
        table.addCell(createCell("Type", true));
        table.addCell(createCell("Asset ID", true));
        table.addCell(createCell("Serial Number", true));
        table.addCell(createCell("Date Received", true));
        table.addCell(createCell("Funder", true));
        table.addCell(createCell("Model", true));
        table.addCell(createCell("Purchase Price", true));
        table.addCell(createCell("States", true));
        table.addCell(createCell("Year of Purchase", true));
        table.addCell(createCell("Implementer", true));
        table.addCell(createCell("Implementation Period", true));
        table.addCell(createCell("Location", true));
        table.addCell(createCell("Custodian", true));
        table.addCell(createCell("Condition", true));
        table.addCell(createCell("Email Address", true));
        table.addCell(createCell("Phone", true));
        table.addCell(createCell("Status", true));

        // Add data rows
        for (Asset obj : data) {
            table.addCell(createCell(obj.getDescription(), false));
            table.addCell(createCell(obj.getCategory(), false));
            table.addCell(createCell(obj.getType(), false));
            table.addCell(createCell(obj.getAssetId(), false));
            table.addCell(createCell(obj.getSerialNumber(), false));
            table.addCell(createCell(obj.getDateReceived(), false));
            table.addCell(createCell(obj.getFunder(), false));
            table.addCell(createCell(obj.getModel(), false));
            table.addCell(createCell(obj.getPurchasePrice(), false));
            table.addCell(createCell(obj.getStates(), false));
            table.addCell(createCell(obj.getYearOfPurchase(), false));
            table.addCell(createCell(obj.getImplementer(), false));
            table.addCell(createCell(obj.getImplementationPeriod(), false));
            table.addCell(createCell(obj.getLocation(), false));
            table.addCell(createCell(obj.getCustodian(), false));
            table.addCell(createCell(obj.getCondition(), false));
            table.addCell(createCell(obj.getEmailAddress(), false));
            table.addCell(createCell(obj.getPhone(), false));
            table.addCell(createCell(obj.getStatus(), false));
        }

        // Add the table to the document
        document.add(table);
    }

    private PdfPCell createCell(String content, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Paragraph(content));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(2);

        Font font = isHeader ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6) : FontFactory.getFont(FontFactory.HELVETICA, 7);
        cell.setPhrase(new Paragraph(content, font));

        return cell;
    }



    @GetMapping("/export-to-csv")
    public ResponseEntity<byte[]> exportToCSV(HttpServletResponse response) {
        try {
            List<Asset> data = assetService.getAllAssets();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);

            // Create CSVWriter object
            CSVWriter csvWriter = new CSVWriter(writer);

            // Write CSV headers
            String[] headers = {
                    "Description", "Category", "Type", "Asset ID", "Serial Number", "Date Received",
                    "Funder", "Model", "Purchase Price", "States", "Year of Purchase", "Implementer",
                    "Implementation Period", "Location", "Custodian", "Condition", "Email Address",
                    "Phone", "Status"
            };
            csvWriter.writeNext(headers);

            // Write data rows
            for (Asset obj : data) {
                String[] row = {
                        obj.getDescription(), obj.getCategory(), obj.getType(), obj.getAssetId(),
                        obj.getSerialNumber(), obj.getDateReceived(), obj.getFunder(), obj.getModel(),
                        obj.getPurchasePrice(), obj.getStates(), obj.getYearOfPurchase(),
                        obj.getImplementer(), obj.getImplementationPeriod(), obj.getLocation(),
                        obj.getCustodian(), obj.getCondition(), obj.getEmailAddress(),
                        obj.getPhone(), obj.getStatus()
                };
                csvWriter.writeNext(row);
            }

            csvWriter.flush();
            byte[] csvBytes = outputStream.toByteArray();

            writer.flush();
            writer.close();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.TEXT_PLAIN);
            header.setContentDispositionFormData("attachment", "asset.csv");

            // Return the byte array as the response
            return new ResponseEntity<>(csvBytes, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}