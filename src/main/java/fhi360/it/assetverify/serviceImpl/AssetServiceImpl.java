package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    @Override
    public boolean isAssetAlreadyPresent(final Asset asset) {
        boolean isAssetAlreadyExists = false;
        final Asset existingAsset = this.assetRepository.findByAssetId(asset.getAssetId());
        if (existingAsset != null) {
            isAssetAlreadyExists = true;
        }
        return isAssetAlreadyExists;
    }

    @Override
    public Asset save(@RequestBody final Asset asset) {
        return this.assetRepository.save(asset);
    }

    @Override
    public void delete(final int id) {
    }


    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public XSSFWorkbook createExcelFile(List<Asset> assets) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Asset Data");

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Description");
        headerRow.createCell(2).setCellValue("Category");
        headerRow.createCell(3).setCellValue("Type");
        headerRow.createCell(4).setCellValue("AssetID");
        headerRow.createCell(5).setCellValue("SerialNumber");
        headerRow.createCell(6).setCellValue("Date Received");
        headerRow.createCell(7).setCellValue("FundedBy");
        headerRow.createCell(8).setCellValue("Model");
        headerRow.createCell(9).setCellValue("Purchase Price");
        headerRow.createCell(10).setCellValue("State");
        headerRow.createCell(11).setCellValue("Year of purchase ");
        headerRow.createCell(12).setCellValue("Implementer");
        headerRow.createCell(13).setCellValue("Implementation Period");
        headerRow.createCell(14).setCellValue("Location");
        headerRow.createCell(15).setCellValue("Custodian");
        headerRow.createCell(16).setCellValue("Condition");
        headerRow.createCell(17).setCellValue("Email");
        headerRow.createCell(18).setCellValue("Phone");
        headerRow.createCell(19).setCellValue("Status");
        // ... add headers for other fields

        // Populate data
        int rowNum = 1;
        for (Asset asset : assets) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(asset.getId());
            row.createCell(1).setCellValue(asset.getDescription());
            row.createCell(2).setCellValue(asset.getCategory());
            row.createCell(3).setCellValue(asset.getType());
            row.createCell(4).setCellValue(asset.getAssetId());
            row.createCell(5).setCellValue(asset.getSerialNumber());
            row.createCell(6).setCellValue(asset.getDateReceived());
            row.createCell(7).setCellValue(asset.getFunder());
            row.createCell(8).setCellValue(asset.getModel());
            row.createCell(9).setCellValue(asset.getPurchasePrice());
            row.createCell(10).setCellValue(asset.getStates());
            row.createCell(11).setCellValue(asset.getYearOfPurchase());
            row.createCell(12).setCellValue(asset.getImplementer());
            row.createCell(13).setCellValue(asset.getImplementationPeriod());
            row.createCell(14).setCellValue(asset.getLocation());
            row.createCell(15).setCellValue(asset.getCustodian());
            row.createCell(16).setCellValue(asset.getCondition());
            row.createCell(17).setCellValue(asset.getEmailAddress());
            row.createCell(18).setCellValue(asset.getPhone());
            row.createCell(19).setCellValue(asset.getStatus());
            // ... add data for other fields
        }

        return workbook;
    }

}
