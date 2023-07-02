package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.IssueLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueLogRepository extends JpaRepository<IssueLog, Long> {
    @Query("Select i from IssueLog i where i.warehouseName=:keyword OR i.batchNo=:keyword OR i.itemDescription=:keyboard OR i.dispatchedLocation =: keyword OR i.issuedTo =: keyword OR i.issuedToEmail=:keyword OR i.phone=:keyword")
    Page<IssueLog> findAll(Pageable pageable, @Param("keyword") String keyword);

    Page<IssueLog> findByOrderByIdAsc(Pageable pageable);

    List<IssueLog> findByInventoryId(Long inventoryId);


//    Page<IssueLog> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
