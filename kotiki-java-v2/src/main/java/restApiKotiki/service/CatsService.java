package restApiKotiki.service;

import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import java.util.List;

public interface CatsService {
    int createOwner(Owner owner);
    Owner getOwnerByID(int id);
    List<Owner> getAllOwners();
    void deleteOwnerByID(int id);
    void changeBirthDateOwner(int id,java.sql.Timestamp birthDateOwner);

    int createCatWithOwner(Cat cat,int idOwner);
    List<Cat> getAllCatsByOwner(int ownerId);
    Cat getCatById(int id);
    List<Cat> getAllCats();
    void deleteCatByID(int id);
    void makeFriendsCat(int firstCatID, int anotherCatID);
    List<Cat> getCatByColour(String colour);

}
