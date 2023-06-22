package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.StockStatusReport;
import fhi360.it.assetverify.repository.StockStatusReportRepository;
import fhi360.it.assetverify.service.StickStatusReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StockStatusReportServiceImpl implements StickStatusReportService {
    private final StockStatusReportRepository stockStatusReportRepository;

    @Override
    public StockStatusReport addReport(StockStatusReport stockStatusReport) {
        StockStatusReport newStockStatusReport = new StockStatusReport();
        newStockStatusReport.setDate(stockStatusReport.getDate());
        newStockStatusReport.setWarehouseName(stockStatusReport.getWarehouseName());
        newStockStatusReport.setItemDescription(stockStatusReport.getItemDescription());
        newStockStatusReport.setExpiryDate(stockStatusReport.getExpiryDate());

        newStockStatusReport.setBatchNo(stockStatusReport.getBatchNo());
        newStockStatusReport.setShellLife(stockStatusReport.getShellLife());
        newStockStatusReport.setStockBalance(stockStatusReport.getStockBalance());

        double mosCalculation = Double.parseDouble(stockStatusReport.getQuantityReceived())/ Double.parseDouble(stockStatusReport.getStockBalance());

        newStockStatusReport.setMos(String.valueOf(mosCalculation));
        newStockStatusReport.setRemark(stockStatusReport.getRemark());

        System.out.println("Mos:: " + mosCalculation);
        return stockStatusReportRepository.save(newStockStatusReport);
    }
}
