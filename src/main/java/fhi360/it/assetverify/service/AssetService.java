
package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.model.IssueLog;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public interface AssetService {
    boolean isAssetAlreadyPresent(final Asset asset);

    Asset save(final Asset asset);

    void delete(final int id);


    List<Asset> getAllAssets();
//    XSSFWorkbook createExcelFile(List<Asset> assets);
}
