package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cats_friend", schema = "public", catalog = "postgres")
public class CatsFriendEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "id_cat", nullable = true)
    private int idCat;
    @Basic
    @Column(name = "id_cat_friend", nullable = true)
    private Integer idCatFriend;
    @OneToOne
    @JoinColumn(name = "id_cat", referencedColumnName = "id", insertable = false, updatable = false)
    private CatEntity catByIdCat;
    @ManyToOne
    @JoinColumn(name = "id_cat_friend", referencedColumnName = "id", insertable = false, updatable = false)
    private CatEntity catByIdCatFriend;

    public CatsFriendEntity() {
    }

    public CatsFriendEntity(int idCat, Integer idCatFriend) {
        this.idCat = idCat;
        this.idCatFriend = idCatFriend;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public Integer getIdCatFriend() {
        return idCatFriend;
    }

    public void setIdCatFriend(Integer idCatFriend) {
        this.idCatFriend = idCatFriend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatsFriendEntity that = (CatsFriendEntity) o;
        return idCat == that.idCat && Objects.equals(idCatFriend, that.idCatFriend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCat, idCatFriend);
    }

    public CatEntity getCatByIdCat() {
        return catByIdCat;
    }

    public void setCatByIdCat(CatEntity catByIdCat) {
        this.catByIdCat = catByIdCat;
    }

    public CatEntity getCatByIdCatFriend() {
        return catByIdCatFriend;
    }

    public void setCatByIdCatFriend(CatEntity catByIdCatFriend) {
        this.catByIdCatFriend = catByIdCatFriend;
    }
}
