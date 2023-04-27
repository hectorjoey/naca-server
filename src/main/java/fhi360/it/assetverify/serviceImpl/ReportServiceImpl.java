package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Report;
import fhi360.it.assetverify.repository.ReportRepository;
import fhi360.it.assetverify.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) {
        int add = Integer.parseInt(report.getQuantityIssuedInMonthOne()) + Integer.parseInt(report.getQuantityIssuedInMonthTwo()) + Integer.parseInt(report.getQuantityIssuedInMonthThree());
        double amcCalculation = (Double.parseDouble(report.getQuantityIssuedInMonthOne()) + Double.parseDouble(report.getQuantityIssuedInMonthTwo()) + Double.parseDouble(report.getQuantityIssuedInMonthThree())) / 3;
        Report newReport = new Report();
        newReport.setDate(report.getDate());
        newReport.setWarehouseName(report.getWarehouseName());
        newReport.setDescription(report.getDescription());
        newReport.setExpiryDate(report.getExpiryDate());
        newReport.setUnit(report.getUnit());
        newReport.setStockState(report.getStockState());
        newReport.setStockOnHand(report.getStockOnHand());
        newReport.setQuantityIssuedInMonthOne(report.getQuantityIssuedInMonthOne());
        newReport.setQuantityIssuedInMonthTwo(report.getQuantityIssuedInMonthTwo());
        newReport.setQuantityIssuedInMonthThree(report.getQuantityIssuedInMonthThree());

        newReport.setSum(String.valueOf(add));
        newReport.setAmc(String.valueOf(amcCalculation));
        double mosCalculation = Double.parseDouble(report.getStockOnHand()) / amcCalculation;
        newReport.setMos(String.valueOf(mosCalculation));
        newReport.setRemark(report.getRemark());

        System.out.println("Add::  " + add);
        System.out.println("AMc: : " + amcCalculation);
        System.out.println("Mos:: " + mosCalculation);
        return reportRepository.save(newReport);
    }
}
