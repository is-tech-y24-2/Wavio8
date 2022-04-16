package restApiKotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restApiKotiki.dao.CatDAO;
import restApiKotiki.dao.OwnerDAO;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CatsServiceImpl implements CatsService {

    @Autowired
    private OwnerDAO ownerDAO;
    @Autowired
    private CatDAO catDAO;

    @Override
    @Transactional
    public int createOwner(Owner owner) {
        return ownerDAO.createOwner(owner);
    }

    @Override
    public Owner getOwnerByID(int id) {
        return ownerDAO.findOwnerByID(id);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDAO.findAllOwners();
    }

    @Override
    public void deleteOwnerByID(int id) {
        ownerDAO.deleteOwnerByID(id);
    }

    @Override
    @Transactional
    public void changeBirthDateOwner(int id, Timestamp birthDateOwner){
        ownerDAO.updateBirthDateOwner(id,birthDateOwner);
    }

    @Override
    public int createCatWithOwner(Cat cat, int idOwner) {
       Owner ownerByID=getOwnerByID(idOwner);
       cat.setOwner(ownerByID);
       return catDAO.createCat(cat);
    }

    @Override
    public List<Cat> getAllCatsByOwner(int id) {
        return catDAO.findByOwner(id);
    }

    @Override
    public Cat getCatByID(int id) {
        return catDAO.findCatByID(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDAO.findAllCats();
    }

    @Override
    public void deleteCatByID(int id) {
        catDAO.deleteCatByID(id);
    }

    @Override
    @Transactional
    public void makeFriendsCat(int firstCatID, int anotherCatID) {
        catDAO.makeFriendsCat(firstCatID,anotherCatID);
    }

    @Override
    public List<Cat> getCatByColour(String colour) {
        return catDAO.findCatByColour(colour);
    }
}
