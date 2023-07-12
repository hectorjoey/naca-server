package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.AssetLog;
import fhi360.it.assetverify.model.IssueLog;
import fhi360.it.assetverify.repository.AssetLogRepository;

import fhi360.it.assetverify.service.AssetLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AssetLogServiceImpl implements AssetLogService {
    private final AssetLogRepository assetLogRepository;


    @Override
    public AssetLog addAssetLog(AssetLog assetLog) {
        return assetLogRepository.save(assetLog);
    }

    @Override
    public List<AssetLog> getAllAssetLogs() {
        return assetLogRepository.findAll();
    }

    @Override
    public Page<AssetLog> searchByDate(String startDate, String endDate, Pageable pageable) {
        return assetLogRepository.findByDateBetween(startDate, endDate, pageable);
    }
}
