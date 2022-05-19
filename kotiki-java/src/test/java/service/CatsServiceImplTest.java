package service;

import Entity.CatEntity;
import Entity.CatsFriendEntity;
import Entity.OwnerEntity;
import dao.CatDao;
import dao.CatsFriendDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatsServiceImplTest {

    private CatsServiceImpl catsService;

    @BeforeEach
    void setUp() {
        catsService = Mockito.mock(CatsServiceImpl.class);
    }

    @Test
    void createOwnerAndCat() {
        OwnerEntity Maks = new OwnerEntity("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cotik = new CatEntity("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", Maks.getId());
        when(catsService.createOwner(Maks.getName(), Maks.getBirthDateOwner())).thenReturn(Maks);
        when(catsService.createCat(cotik.getName(), cotik.getBirthDate(), "orange", "ww", Maks)).thenReturn(cotik);
        OwnerEntity ownerMaks = catsService.createOwner("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", Maks);
        assertEquals(cat1.getColour(), "orange");
        assertEquals(cat1.getBread(), "ww");
        assertEquals(ownerMaks.getId(), cat1.getIdOwner());
        assertEquals(ownerMaks.getName(), "Tim");

    }

    @Test
    void checkCatsAtOwner() {
        OwnerEntity Maks = new OwnerEntity("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cotik = new CatEntity("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", Maks.getId());
        when(catsService.createOwner(Maks.getName(), Maks.getBirthDateOwner())).thenReturn(Maks);
        when(catsService.createCat(cotik.getName(), cotik.getBirthDate(), "orange", "ww", Maks)).thenReturn(cotik);
        CatEntity cotik2 = new CatEntity("cotik2", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "tt", Maks.getId());
        when(catsService.createCat(cotik2.getName(), cotik2.getBirthDate(), cotik2.getColour(), cotik2.getBread(), Maks)).thenReturn(cotik2);
        OwnerEntity ownerMaks = catsService.createOwner("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", ownerMaks);
        CatEntity cat2 = catsService.createCat("cotik2", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "tt", ownerMaks);
        assertEquals(cat2.getBread(), "tt");
        List<CatEntity> cotiks = new ArrayList<CatEntity>();
        cotiks.add(cat1);
        cotiks.add(cat2);
        ownerMaks.setCatsById(cotiks);
        when(catsService.findOwnerCatsById(ownerMaks)).thenReturn(ownerMaks.getCatsById());
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(0), cat1);
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(1), cat2);

    }

    @Test
    void checkCatsAtOwner2() {
        OwnerEntity Maks = new OwnerEntity("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cotik = new CatEntity("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", Maks.getId());
        when(catsService.createOwner(Maks.getName(), Maks.getBirthDateOwner())).thenReturn(Maks);
        when(catsService.createCat(cotik.getName(), cotik.getBirthDate(), "orange", "ww", Maks)).thenReturn(cotik);
        CatEntity cotik2 = new CatEntity("cotik2", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "tt", Maks.getId());
        when(catsService.createCat(cotik2.getName(), cotik2.getBirthDate(), cotik2.getColour(), cotik2.getBread(), Maks)).thenReturn(cotik2);
        OwnerEntity ownerMaks = catsService.createOwner("Tim", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("cotik", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", ownerMaks);
        CatEntity cat2 = catsService.createCat("cotik2", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "tt", ownerMaks);
        doNothing().when(catsService).makeFriendsCat(cat1, cat2);
        catsService.makeFriendsCat(cat1, cat2);
        CatsFriendEntity catsFriendEntity = new CatsFriendEntity(cat1.getId(), cat2.getId());
        cat1.setCatsFriendById(catsFriendEntity);
        List<CatEntity> catEntityList = new ArrayList<CatEntity>();
        catEntityList.add(cat2);
        when(catsService.getFriendsCat(cat1)).thenReturn(catEntityList);
        assertEquals(catsService.getFriendsCat(cat1).get(0), cat2);

    }
}