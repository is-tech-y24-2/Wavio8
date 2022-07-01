package microKotikiCats.service;


import models.Cat;

import java.util.List;

public interface CatsService {

    int createCatWithOwner(Cat cat, int idOwner);
//    List<Cat> getAllCatsByOwner(int ownerId);
    Cat getCatById(int id);
    List<Cat> getAllCats();
    void deleteCatByID(int id);
//    void makeFriendsCat(int firstCatID, int anotherCatID);
    List<Cat> getCatByColour(String colour);

}
