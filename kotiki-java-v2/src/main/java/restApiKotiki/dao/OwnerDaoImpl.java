package restApiKotiki.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restApiKotiki.models.Owner;
import restApiKotiki.repository.OwnerRepository;

import java.util.List;

@Component
public class OwnerDaoImpl implements OwnerDao {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public int createOwner(Owner owner) {
        Owner savedOwner=ownerRepository.save(owner);
        return savedOwner.getId();
    }

    @Override
    public Owner findOwnerByID(int id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public void deleteOwnerByID(int id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        }
    }

    @Override
    public void updateBirthDateOwner(int id, java.sql.Timestamp birthDateOwner) {
        ownerRepository.setOwnerBirthDateOwner(id,birthDateOwner);
        ownerRepository.findByOrderByIdAsc();
    }
}
