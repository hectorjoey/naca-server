package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("Select i from Inventory i where i.warehouseName=:keyword OR  i.category=:keyword OR i.batchNo =: keyword OR i.expiryDate=: keyword OR i.reportingMonth =: keyword OR i.donor=:keyword")
    Page<Inventory> findAll(Pageable pageable, @Param("keyword") String keyword);
    Page<Inventory> findByOrderByIdAsc(Pageable pageable);
}