package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.StockStatusReport;
import org.springframework.stereotype.Service;

@Service
public interface StickStatusReportService {
    StockStatusReport addReport(StockStatusReport stockStatusReport);
}
