package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.StockStatusReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockStatusReportRepository extends JpaRepository<StockStatusReport, Long> {

    @Query("Select s from StockStatusReport s where s.warehouseName=:keyword OR s.itemDescription =: keyword")
    Page<StockStatusReport> findAll(Pageable pageable, @Param("keyword") String keyword);

    List<StockStatusReport> findByDateBetween(LocalDate startDate, LocalDate endDate);

    Page<StockStatusReport> findByOrderByIdAsc(Pageable pageable);

}