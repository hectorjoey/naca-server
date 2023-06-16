package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Asset findByAssetId(final String assetId);

    Asset findBySerialNumber(final String serialNumber);
   Page< Asset> findByOrderByStatesAsc(Pageable pageable);

    @Query("Select a from Asset a where a.category=:keyword OR a.description=:keyword OR a.states=:keyword OR a.serialNumber =:keyword OR a.assetId=:keyword OR a.emailAddress =:keyword OR a.custodian =: keyword")
    Page<Asset> findAll(final Pageable pageable, @Param("keyword") final String keyword);
}