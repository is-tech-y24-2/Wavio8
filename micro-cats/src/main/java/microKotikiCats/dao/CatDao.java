package microKotikiCats.dao;

import models.Cat;

import java.util.List;

public interface CatDao {

    int createCat(Cat cat);
    Cat findCatByID(int id);
    List<Cat> findAllCats();
    void deleteCatByID(int id);
    List<Cat> findByOwner(int idOwner);
    void makeFriendsCat(int firstCatID, int anotherCatID);
    List<Cat> findCatByColour(String colour);
}