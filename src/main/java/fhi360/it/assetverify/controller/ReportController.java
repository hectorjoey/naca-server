package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.StockStatusReport;
import fhi360.it.assetverify.repository.StockStatusReportRepository;
import fhi360.it.assetverify.service.StickStatusReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/")
public class ReportController {

    private final StickStatusReportService stickStatusReportService;
    private final StockStatusReportRepository stockStatusReportRepository;

    @GetMapping("reports")
    public Page<StockStatusReport> getAllReports(Pageable pageable) {
        return stockStatusReportRepository.findByOrderByIdAsc(pageable);
    }


    @PostMapping("report")
    ResponseEntity<StockStatusReport> createReport(@RequestBody StockStatusReport stockStatusReport){
        return new ResponseEntity<>(stickStatusReportService.addReport(stockStatusReport), HttpStatus.CREATED);
    }

    @GetMapping("report/{id}")
    public ResponseEntity<StockStatusReport> getReportById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        StockStatusReport stockStatusReport = stockStatusReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found for this id :: " + id));
        return ResponseEntity.ok().body(stockStatusReport);
    }


    @GetMapping("status/search")
    public ResponseEntity<List<StockStatusReport>> getData(
            @RequestParam(value = "startDate", required = false)String  startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {

        List<StockStatusReport> data;

        if (startDate != null && endDate != null) {
            data = stockStatusReportRepository.findByDateBetween(startDate, endDate);
        } else {
            data = stockStatusReportRepository.findAll();
        }

        return ResponseEntity.ok(data);
    }


    // For searching report
    @GetMapping("reports/{keyword}")
    public Page<StockStatusReport> getAllReport(Pageable pageable, @PathVariable("keyword") String keyword) {
        return stockStatusReportRepository.findAll(pageable, keyword);
    }
}