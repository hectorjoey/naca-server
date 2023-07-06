
package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Asset;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public interface AssetService {
    boolean isAssetAlreadyPresent(final Asset asset);

    Asset save(final Asset asset);

    void delete(final int id);


}
