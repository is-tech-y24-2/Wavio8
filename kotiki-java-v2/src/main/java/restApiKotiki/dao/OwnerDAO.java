package restApiKotiki.dao;

import restApiKotiki.models.Owner;

import java.util.List;

public interface OwnerDAO {
    int createOwner(Owner owner);
    Owner findOwnerByID(int id);
    List<Owner> findAllOwners();
    void deleteOwnerByID(int id);
    void updateBirthDateOwner(int id, java.sql.Timestamp birthDateOwner);
}
