import Entity.CatEntity;
import Entity.OwnerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CatsServiceImpl;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CatsTest {
    private CatsServiceImpl catsService;

    @BeforeEach
    void setUp() {
        catsService = new CatsServiceImpl();
    }

    @Test
    void createOwnerAndCat() {
        OwnerEntity ownerMaks = catsService.createOwner("Maks", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("Bob", new Timestamp(2002, 7, 8, 0, 0, 0, 0), "orange", "ww", ownerMaks);
        assertEquals(cat1.getColour(), "orange");
        assertEquals(cat1.getBread(), "ww");
        assertEquals(ownerMaks.getId(), cat1.getIdOwner());
        assertEquals(ownerMaks.getName(), "Maks");
    }

    @Test
    void checkCatsAtOwner() {
        OwnerEntity ownerMaks = catsService.createOwner("Maks", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("Bob", new Timestamp(110, 7, 8, 0, 0, 0, 0), "orange", "ww", ownerMaks);
        CatEntity cat2 = catsService.createCat("Tim", new Timestamp(109, 7, 8, 0, 0, 0, 0), "red", "tt", ownerMaks);
        assertEquals(cat2.getBread(), "tt");
        catsService.findOwnerCatsById(ownerMaks);
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(0), cat1);
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(1), cat2);

    }

    @Test
    void checkCatsAtOwner2() {
        OwnerEntity ownerMaks = catsService.createOwner("Maks", new Timestamp(120, 6, 3, 0, 0, 0, 0));
        CatEntity cat1 = catsService.createCat("Bob", new Timestamp(110, 5, 8, 0, 0, 0, 0), "orange", "ww", ownerMaks);
        CatEntity cat2 = catsService.createCat("Tim", new Timestamp(109, 7, 8, 0, 0, 0, 0), "red", "tt", ownerMaks);
        assertEquals(cat2.getColour(), "red");
        catsService.makeFriendsCat(cat1, cat2);
        catsService.findOwnerCatsById(ownerMaks);
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(0), cat1);
        assertEquals(catsService.findOwnerCatsById(ownerMaks).get(1), cat2);
        assertEquals(catsService.getFriendsCat(cat1).get(0),cat2);

    }
}
