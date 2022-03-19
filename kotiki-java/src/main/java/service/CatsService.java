package service;

import Entity.CatEntity;
import Entity.OwnerEntity;


import java.util.List;

public interface CatsService {
    OwnerEntity createOwner(String name, java.sql.Timestamp birthDateOwner);

    CatEntity createCat(String name, java.sql.Timestamp birthDate, String colour, String bread, OwnerEntity owner);

    void makeFriendsCat(CatEntity catEntity, CatEntity anotherCat);

    List<CatEntity> getFriendsCat(CatEntity catEntity);

    List<CatEntity> catsAtOwner(OwnerEntity ownerEntity);

    List<OwnerEntity> findOwnerByName(String name);

    List<CatEntity> findAllCat();

    CatEntity findCat(List<CatEntity> catEntityList, int id);

    public List<CatEntity> findOwnerCatsById(OwnerEntity ownerEntity);

    int findNumFriendsCat(CatEntity cat);

    void deleteCatByID(int id);

    int findIDByCat(CatEntity cat);

    int findIDByOwner(OwnerEntity owner);


}
