package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Report;
import fhi360.it.assetverify.repository.ReportRepository;
import fhi360.it.assetverify.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
//@CrossOrigin(origins = "https://naca-client.netlify.app/")
@RestController
@RequestMapping("/api/v1/")
public class ReportController {

    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @GetMapping("reports")
    public Page<Report> getAllReports(Pageable pageable) {
        return reportRepository.findByOrderByIdAsc(pageable);
    }


    @PostMapping("report")
    ResponseEntity<Report> createReport(@RequestBody Report report){
        return new ResponseEntity<>(reportService.addReport(report), HttpStatus.CREATED);
    }

    @GetMapping("report/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found for this id :: " + id));
        return ResponseEntity.ok().body(report);
    }

    // For searching report
    @GetMapping("reports/{keyword}")
    public Page<Report> getAllReport(Pageable pageable, @PathVariable("keyword") String keyword) {
        return reportRepository.findAll(pageable, keyword);
    }
}