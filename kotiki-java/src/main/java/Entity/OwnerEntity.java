package Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owner", schema = "public", catalog = "postgres")
public class OwnerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "birth_date_owner", nullable = true)
    private java.sql.Timestamp birthDateOwner;
    @OneToMany(mappedBy = "ownerByIdOwner")
    private List<CatEntity> catsById;


    public OwnerEntity() {
    }

    public OwnerEntity(String name, java.sql.Timestamp birthDateOwner) {
        this.name = name;
        this.birthDateOwner = birthDateOwner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Timestamp getBirthDateOwner() {
        return birthDateOwner;
    }

    public void setBirthDateOwner(java.sql.Timestamp birthDateOwner) {
        this.birthDateOwner = birthDateOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerEntity that = (OwnerEntity) o;
        return id == that.id && Objects.equals(birthDateOwner, that.birthDateOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthDateOwner);
    }

    public List<CatEntity> getCatsById() {
        return catsById;
    }

    public void setCatsById(List<CatEntity> catsById) {
        this.catsById = catsById;
    }

//    public OwnerCatsEntity getOwnerCatsById() {
//        return ownerCatsById;
//    }
//
//    public void setOwnerCatsById(OwnerCatsEntity ownerCatsById) {
//        this.ownerCatsById = ownerCatsById;
//    }
}
