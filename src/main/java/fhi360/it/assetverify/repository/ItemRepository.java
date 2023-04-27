package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{
    Page<Item> findItemsByStates(final Pageable items, final String pageable);
    
    @Query("Select i from Item i where i.status=:keyword OR lower (i.requesterEmail) =:keyword")
    Page<Item> findAll(final Pageable pageable, @Param("keyword") final String keyword);
}
