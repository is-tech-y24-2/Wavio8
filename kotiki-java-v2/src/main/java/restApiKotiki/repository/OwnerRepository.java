package restApiKotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    List<Owner> findByOrderByIdAsc();
    @Modifying
    @Query(value = "update Owner o set o.birthDateOwner = :birthDate WHERE o.id = :ownerId")
    void setOwnerBirthDateOwner(@Param("ownerId") Integer id, @Param("birthDate") java.sql.Timestamp birthDateOwner);
}