package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>
{
    Page<Loan> findByOrderByExpiryDateDesc(final Pageable pageable, final String expiryDate);
    
    Page<Loan> findLoansByLocation(final Pageable pageable, final String location);
    
    @Query("Select l from Loan l where l.serialnumber =:keyword OR l.status =:keyword OR l.email =:keyword")
    Page<Loan> findAll(final Pageable pageable, @Param("keyword") final String keyword);
}
