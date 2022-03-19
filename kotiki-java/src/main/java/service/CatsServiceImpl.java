package service;

import Entity.CatEntity;
import Entity.CatsFriendEntity;
import Entity.OwnerCatsEntity;
import Entity.OwnerEntity;
import dao.CatDao;
import dao.CatsFriendDao;
import dao.OwnerCatsDao;
import dao.OwnerDao;


import java.util.ArrayList;
import java.util.List;

public class CatsServiceImpl implements CatsService {
    private CatDao catDao = new CatDao();
    private OwnerDao ownerDao = new OwnerDao();
    private OwnerCatsDao ownerCatsDao = new OwnerCatsDao();
    private CatsFriendDao catsFriendDao = new CatsFriendDao();


    @Override
    public OwnerEntity createOwner(String name, java.sql.Timestamp birthDateOwner) {
        OwnerEntity ownerEntity = new OwnerEntity(name, birthDateOwner);
        ownerDao.saveOwner(ownerEntity);
        return ownerEntity;
    }

    @Override
    public CatEntity createCat(String name, java.sql.Timestamp birthDate, String colour, String bread, OwnerEntity owner) {
        CatEntity catEntity = new CatEntity(name, birthDate, colour, bread, owner.getId());
        catDao.saveNewCat(catEntity);
        int ownerId = owner.getId();
        int catId = catEntity.getId();

        ownerCatsDao.saveOwnerCats(new OwnerCatsEntity(ownerId, catId));

        catDao.updateCatData(catEntity);
        ownerDao.updateOwner(owner);

        return catEntity;
    }

    @Override
    public void makeFriendsCat(CatEntity catEntity, CatEntity anotherCat) {
        CatsFriendEntity catsFriendEntity = new CatsFriendEntity(catEntity.getId(), anotherCat.getId());
        catsFriendDao.saveNewData(catsFriendEntity);
        CatsFriendEntity anotherCatEntity = new CatsFriendEntity(anotherCat.getId(), catEntity.getId());
        catsFriendDao.saveNewData(anotherCatEntity);
    }

    @Override
    public List<CatEntity> getFriendsCat(CatEntity catEntity) {
        List<CatEntity> catEntityList = new ArrayList<CatEntity>();
        for (CatsFriendEntity catsFriendEntity : catsFriendDao.getAllTableCatsFriend()) {
            if (catsFriendEntity.getCatByIdCat().getId() == catEntity.getId()) {
                catEntityList.add(catsFriendEntity.getCatByIdCatFriend());
            }
        }
        return catEntityList;
    }

    @Override
    public List<CatEntity> catsAtOwner(OwnerEntity ownerEntity) {

        return ownerEntity.getCatsById();
    }

    @Override
    public List<OwnerEntity> findOwnerByName(String name) {
        List<OwnerEntity> ownerSameName = new ArrayList<OwnerEntity>();
        for (OwnerEntity owner : ownerDao.getAllOwnerTable()) {
            if (owner.getName() == name) {
                ownerSameName.add(owner);
            }
        }
        return ownerSameName;
    }

    @Override
    public List<CatEntity> findAllCat() {
        return catDao.getAllCatTable();
    }

    @Override
    public CatEntity findCat(List<CatEntity> catEntityList, int id) {
        for (CatEntity cat : catEntityList) {
            if (cat.getId() == id) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public List<CatEntity> findOwnerCatsById(OwnerEntity ownerEntity) {
        List<CatEntity> catEntityList = new ArrayList<CatEntity>();
        for (OwnerCatsEntity ownerCatsEntity : ownerCatsDao.getAllOwnerCats()) {
            if (ownerEntity.getId() == ownerCatsEntity.getIdOwner()) {
                catEntityList.add(catDao.findCatById(ownerCatsEntity.getIdCat()));
            }
        }
        return catEntityList;
    }

    @Override
    public int findNumFriendsCat(CatEntity cat) {
        return cat.getCatsFriendsById().size();
    }

    @Override
    public void deleteCatByID(int id) {
        catDao.deleteCat(catDao.findCatById(id));
    }

    @Override
    public int findIDByCat(CatEntity cat) {
        return cat.getId();
    }

    @Override
    public int findIDByOwner(OwnerEntity owner) {
        return owner.getId();
    }
}
