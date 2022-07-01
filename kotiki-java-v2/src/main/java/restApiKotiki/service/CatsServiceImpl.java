package restApiKotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import restApiKotiki.dao.CatDao;
import restApiKotiki.dao.OwnerDao;
import restApiKotiki.models.Account;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatsServiceImpl implements CatsService {
    private static final int FORBIDDEN = -1;


    @Autowired
    private OwnerDao ownerDAO;
    @Autowired
    private CatDao catDAO;

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public int createOwner(Owner owner) {
        return ownerDAO.createOwner(owner);
    }

    @Override
    public Owner getOwnerByID(int id) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
            return null;
        }
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
    public void changeBirthDateOwner(int id, Timestamp birthDateOwner) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
            return;
        }
        ownerDAO.updateBirthDateOwner(id, birthDateOwner);
    }

    @Override
    public int createCatWithOwner(Cat cat, int idOwner) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != idOwner) {
            return FORBIDDEN;
        }
        Owner ownerByID = getOwnerByID(idOwner);
        cat.setOwner(ownerByID);
        return catDAO.createCat(cat);
    }

    @Override
    public List<Cat> getAllCatsByOwner(int id) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
            return null;
        }
        return catDAO.findByOwner(id);
    }

    @Override
    public Cat getCatById(int id) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != catDAO.findCatByID(id).getOwner().getId()) {
            return null;
        }
        return catDAO.findCatByID(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDAO.findAllCats();
    }

    @Override
    public void deleteCatByID(int id) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != catDAO.findCatByID(id).getOwner().getId()) {
            return;
        }
        catDAO.deleteCatByID(id);
    }

    @Override
    @Transactional
    public void makeFriendsCat(int firstCatID, int anotherCatID) {
        catDAO.makeFriendsCat(firstCatID, anotherCatID);
    }

    @Override
    public List<Cat> getCatByColour(String colour) {
        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Cat> cats = new ArrayList<>();
        if (Objects.equals(account.getRole(), Role.USER)) {
            for (Cat cat : catDAO.findCatByColour(colour)) {
                if (account.getOwner().getId() == cat.getOwner().getId()) {
                    cats.add(cat);
                }
            }
            return cats;
        }
        return catDAO.findCatByColour(colour);
    }
}
