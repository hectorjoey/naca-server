package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Report;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    Report addReport(Report report);
}
