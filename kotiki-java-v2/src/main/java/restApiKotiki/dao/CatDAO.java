package restApiKotiki.dao;

import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import java.util.List;

public interface CatDAO {
    int createCat(Cat cat);
    Cat findCatByID(int id);
    List<Cat> findAllCats();
    void deleteCatByID(int id);
    List<Cat> findByOwner(int idOwner);
    void makeFriendsCat(int firstCatID, int anotherCatID);
    List<Cat> findCatByColour(String colour);
}
