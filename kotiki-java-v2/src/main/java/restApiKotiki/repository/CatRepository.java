package restApiKotiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findByOwnerId(int id);
    List<Cat> findCatsByColour(String colour);

}
