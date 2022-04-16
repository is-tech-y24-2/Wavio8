package restApiKotiki.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restApiKotiki.models.Cat;
import restApiKotiki.models.Owner;
import restApiKotiki.repository.CatRepository;

import java.util.List;


@Component
public class CatDAOImpl implements CatDAO{
    @Autowired
    private CatRepository catRepository;

    @Override
    public int createCat(Cat cat) {
        Cat savedCat=catRepository.save(cat);
        return savedCat.getId();
    }

    @Override
    public Cat findCatByID(int id) {
        return catRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }

    @Override
    public void deleteCatByID(int id) {
        if (catRepository.existsById(id)) {
            catRepository.deleteById(id);
        }
    }

    @Override
    public List<Cat> findByOwner(int id) {
        return catRepository.findByOwnerId(id);
    }

    @Override
    public void makeFriendsCat(int firstCatID, int anotherCatID) {
        Cat firstCat=findCatByID(firstCatID);
        Cat anotherCat=findCatByID(anotherCatID);
        firstCat.getFriends().add(anotherCat);
//        anotherCat.getFriends().add(firstCat);
        firstCat.getFriendsAnother().add(firstCat);
        catRepository.save(firstCat);
    }

    @Override
    public List<Cat> findCatByColour(String colour) {
        return catRepository.findCatsByColour(colour);
    }


}
