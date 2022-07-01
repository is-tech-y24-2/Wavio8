package microKotikiOwner.service;

import microKotikiOwner.dao.OwnerDao;
import models.Account;
import models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService{
    private static final int FORBIDDEN = -1;


    @Autowired
    private OwnerDao ownerDAO;
//    @Autowired
//    private CatDao catDAO;

//    @Autowired
//    private AccountService accountService;

    @Override
    @Transactional
    public int createOwner(Owner owner) {
        return ownerDAO.createOwner(owner);
    }

    @Override
    public Owner getOwnerByID(int id) {
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
//            return null;
//        }
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
//        Account account = accountService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
//        if (Objects.equals(account.getRole(), Role.USER) && account.getOwner().getId() != id) {
//            return;
//        }
        ownerDAO.updateBirthDateOwner(id, birthDateOwner);
    }


}
