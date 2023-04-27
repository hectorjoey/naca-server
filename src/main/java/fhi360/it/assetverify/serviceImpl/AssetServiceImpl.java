package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.service.AssetService;
import lombok.RequiredArgsConstructor;
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


}
