import dao.OwnerCatsDao;
import service.CatsServiceImpl;

public class Main {
    public static void main(final String[] args) throws Exception {
        CatsServiceImpl catsService=new CatsServiceImpl();
       OwnerCatsDao ownerCatsDao=new OwnerCatsDao();
       System.out.println(ownerCatsDao.getAllOwnerCats().get(0).getIdOwner());
//
//      OwnerCatsEntity ownerCatsEntity=new OwnerCatsEntity(7,9);
//      ownerCatsDao.save(ownerCatsEntity);
//        OwnerEntity ownerMaks=catsService.createOwner("Maks",new Timestamp(120,6,3,0,0,0,0));
//        CatEntity cat1=catsService.createCat("Bob", new Timestamp(2002,7,8,0,0,0,0),"orange","ww",ownerMaks);

    }
}