package microKotikiCats.service;


import dto.AccountResponse;
import dto.OwnerRequest;
import dto.OwnerResponse;
import microKotikiCats.dao.CatDao;
import microKotikiCats.rabbitMq.RabbitMqSender;
import models.Account;
import models.Cat;
import models.Owner;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatsServiceImpl implements CatsService {
    private static final int FORBIDDEN = -1;


//    @Autowired
//    private OwnerDao ownerDAO;
    @Autowired
    private CatDao catDAO;
    @Autowired
    private RabbitMqSender rabbitMqSender;

//    @Autowired
//    private AccountService accountService;
//
    @Override
    public int createCatWithOwner(Cat cat, int idOwner) {
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != idOwner) {
//            return FORBIDDEN;
//        }
        OwnerResponse ownerByID = rabbitMqSender.getOwnerByIDForCat(idOwner);
        Owner owner=new Owner();
        owner.setId(ownerByID.getId());
        owner.setBirthDateOwner(ownerByID.getBirthDateOwner());
        owner.setName(ownerByID.getName());
        cat.setOwner(owner);
        return catDAO.createCat(cat);
    }
//
//    @Override
//    public List<Cat> getAllCatsByOwner(int id) {
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
//            return null;
//        }
//        return catDAO.findByOwner(id);
//    }

    @Override
    public Cat getCatById(int id) {
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != catDAO.findCatByID(id).getOwner().getId()) {
//            return null;
//        }
        return catDAO.findCatByID(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDAO.findAllCats();
    }

    @Override
    public void deleteCatByID(int id) {
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != catDAO.findCatByID(id).getOwner().getId()) {
//            return;
//        }
        catDAO.deleteCatByID(id);
    }
//
//    @Override
//    @Transactional
//    public void makeFriendsCat(int firstCatID, int anotherCatID) {
//        catDAO.makeFriendsCat(firstCatID, anotherCatID);
//    }
//
    @Override
    public List<Cat> getCatByColour(AccountResponse accountResponse) {
//        Account account = new Account();
//        account.accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Cat> cats = new ArrayList<>();
        if (Objects.equals(accountResponse.getRole(), Role.USER)) {
            for (Cat cat : catDAO.findCatByColour(accountResponse.getColour())) {
                if (accountResponse.getOwnerResponse().getId() == cat.getOwner().getId()) {
                    cats.add(cat);
                }
            }
            return cats;
        }
        return catDAO.findCatByColour(accountResponse.getColour());
    }
}