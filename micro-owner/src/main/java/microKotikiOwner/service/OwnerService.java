package microKotikiOwner.service;

import models.Owner;

import java.util.List;

public interface OwnerService {
    int createOwner(Owner owner);
    Owner getOwnerByID(int id);
    List<Owner> getAllOwners();
    void deleteOwnerByID(int id);
    void changeBirthDateOwner(int id,java.sql.Timestamp birthDateOwner);

}
