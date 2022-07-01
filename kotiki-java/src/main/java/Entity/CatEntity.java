package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cat", schema = "public", catalog = "postgres")
public class CatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Basic
    @Column(name = "birth_date", nullable = true)
    private java.sql.Timestamp birthDate;
    @Basic
    @Column(name = "colour", nullable = true, length = -1)
    private String colour;
    @Basic
    @Column(name = "id_owner", nullable = true)
    private Integer idOwner;
    @Basic
    @Column(name = "bread", nullable = true, length = -1)
    private String bread;

    @ManyToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id", updatable = false, insertable = false)
    private OwnerEntity ownerByIdOwner;

    @OneToOne(mappedBy = "catByIdCat")
    private CatsFriendEntity catsFriendById;
    @OneToMany(mappedBy = "catByIdCatFriend")
    private List<CatsFriendEntity> catsFriendsById;
    @OneToMany(mappedBy = "catByIdCat")
    private List<OwnerCatsEntity> ownerCatsById;

    public CatEntity() {
    }

    public CatEntity(String name, java.sql.Timestamp birthDate, String colour, String bread, Integer idOwner) {
        this.name = name;
        this.birthDate = birthDate;
        this.colour = colour;
        this.bread = bread;
        this.idOwner = idOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.sql.Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatEntity catEntity = (CatEntity) o;
        return id == catEntity.id && Objects.equals(name, catEntity.name) && Objects.equals(birthDate, catEntity.birthDate) && Objects.equals(colour, catEntity.colour) && Objects.equals(idOwner, catEntity.idOwner) && Objects.equals(bread, catEntity.bread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, colour, idOwner, bread);
    }

    public OwnerEntity getOwnerByIdOwner() {
        return ownerByIdOwner;
    }

    public void setOwnerByIdOwner(OwnerEntity ownerByIdOwner) {
        this.ownerByIdOwner = ownerByIdOwner;
    }

    public CatsFriendEntity getCatsFriendById() {
        return catsFriendById;
    }

    public void setCatsFriendById(CatsFriendEntity catsFriendById) {
        this.catsFriendById = catsFriendById;
    }

    public List<CatsFriendEntity> getCatsFriendsById() {
        return catsFriendsById;
    }

    public void setCatsFriendsById(List<CatsFriendEntity> catsFriendsById) {
        this.catsFriendsById = catsFriendsById;
    }

    public List<OwnerCatsEntity> getOwnerCatsById() {
        return ownerCatsById;
    }

    public void setOwnerCatsById(List<OwnerCatsEntity> ownerCatsById) {
        this.ownerCatsById = ownerCatsById;
    }
}
