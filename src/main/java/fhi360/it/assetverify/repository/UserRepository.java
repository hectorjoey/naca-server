package fhi360.it.assetverify.repository;

import fhi360.it.assetverify.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>
{
    Users findByEmail(final String email);
    
    Users getByEmail(final String email);
    
    Page<Users> findByOrderByUserTypeAsc(final Pageable pageable);
    
    @Query("Select u from Users u where lower (u.firstname) =:keyword OR lower (u.email) =:keyword OR lower (u.states) =:keyword OR lower(u.department)=:keyword ")
    Page<Users> findAll(final Pageable pageable, @Param("keyword") final String keyword);
}
