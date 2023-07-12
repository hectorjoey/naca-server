package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.model.AssetLog;
import fhi360.it.assetverify.repository.AssetLogRepository;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.service.AssetLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class AssetLogController {
        private final AssetLogService assetLogService;
    private final AssetLogRepository assetLogRepository;
    private final AssetRepository assetRepository;

    @GetMapping("all-assetlogs")
    List<AssetLog> getAssetLogs() {
        return assetLogRepository.findAll();
    }

    @GetMapping("assetlogs")
    public Page<AssetLog> getAllAssetLogs(Pageable pageable) {
        return assetLogRepository.findByOrderByAssetsId(pageable);
    }

    @PostMapping("assetlog")
    public ResponseEntity<Object> createAssetLog(@Valid @RequestBody final AssetLog assetLog) {
        Asset asset = assetRepository.findById(assetLog.getAssetsId()).orElse(null);
        if (asset != null) {
            asset.setEmailAddress(assetLog.getEmailAddress());
            asset.setCustodian(assetLog.getCustodian());
            asset.setStatus(assetLog.getStatus());
            asset.setLocation(assetLog.getLocation());

            // Save the updated asset
            assetRepository.save(asset);
        }

        AssetLog createdAssetLog = assetLogRepository.save(assetLog);
        return new ResponseEntity<>(createdAssetLog, HttpStatus.CREATED);
    }

    @GetMapping("assetlog/search")
    public Page<AssetLog> search(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("page") int page) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, inputFormat);
        LocalDate end = LocalDate.parse(endDate, inputFormat);


        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);

        return assetLogService.searchByDate(formattedStartDate, formattedEndDate, PageRequest.of(page - 1, 10));
    }

    @GetMapping("assetlog/exports")
    public void exportToCSV(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpServletResponse response) throws IOException {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(startDate, df);
        LocalDate end = LocalDate.parse(endDate, df);

        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = start.format(desiredFormat);
        String formattedEndDate = end.format(desiredFormat);

        List<AssetLog> assetLogs = assetLogRepository.findByDateBetween(formattedStartDate, formattedEndDate);

        // Create a StringBuilder to store the CSV content
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Date,Description,Category, Type,Asset ID,Serial Number, Date Received,  Funder, Model, States, Location, Custodian, Condition, Email Address, Phone, Status  \n"); // Replace with actual column names

        // Append each IssueLog entry as a CSV row
        for (AssetLog assetLog : assetLogs) {
            csvContent.append(assetLog.getDate()).append(",");
            csvContent.append(assetLog.getDescription()).append(",");
            csvContent.append(assetLog.getCategory()).append(",");
            csvContent.append(assetLog.getType()).append(",");
            csvContent.append(assetLog.getAssetId()).append(",");
            csvContent.append(assetLog.getSerialNumber()).append(",");
            csvContent.append(assetLog.getDateReceived()).append(",");
            csvContent.append(assetLog.getFunder()).append(",");
            csvContent.append(assetLog.getModel()).append(",");
            csvContent.append(assetLog.getStates()).append(",");
            csvContent.append(assetLog.getLocation()).append(",");
            csvContent.append(assetLog.getCustodian()).append(",");
            csvContent.append(assetLog.getCondition()).append(",");
            csvContent.append(assetLog.getEmailAddress()).append(",");
            csvContent.append(assetLog.getPhone()).append(",");
            csvContent.append(assetLog.getStatus()).append("\n");
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

