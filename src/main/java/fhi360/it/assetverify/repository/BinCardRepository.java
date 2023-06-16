package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.BinCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BinCardRepository extends JpaRepository<BinCard, Long> {

    @Query("Select b from BinCard b where b.warehouseName=:keyword OR b.batchNo=:keyword OR b.description=:keyboard OR b.dispatchedLocation =: keyword OR b.issuedTo =: keyword OR b.issuedToEmail=:keyword OR b.phone=:keyword")
    Page<BinCard> findAll(Pageable pageable, @Param("keyword") String keyword);

    Page<BinCard> findByOrderByIdAsc(Pageable pageable);
}
