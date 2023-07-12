package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.AssetLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetLogRepository extends JpaRepository<AssetLog, Long> {

    Page<AssetLog> findByOrderByAssetsId(Pageable pageable);

    List<AssetLog> findByDateBetween(String startDate, String endDate);

    Page<AssetLog> findByDateBetween(String startDate, String endDate, Pageable pageable);
}
