package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("Select r from Report r where r.warehouseName=:keyword OR r.description =: keyword OR r.category =: keyword")
    Page<Report> findAll(Pageable pageable, @Param("keyword") String keyword);


    Page<Report> findByOrderByIdAsc(Pageable pageable);
}