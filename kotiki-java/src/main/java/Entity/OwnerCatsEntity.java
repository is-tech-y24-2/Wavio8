package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owner_cats", schema = "public", catalog = "postgres")
public class OwnerCatsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "id_owner", nullable = true)
    private int idOwner;
    @Basic
    @Column(name = "id_cat", nullable = true)
    private Integer idCat;
    @OneToOne
    @JoinColumn(name = "id_owner", referencedColumnName = "id", insertable = false, updatable = false)
    private OwnerEntity ownerByIdOwner;
    @ManyToOne
    @JoinColumn(name = "id_cat", referencedColumnName = "id", insertable = false, updatable = false)
    private CatEntity catByIdCat;

    public OwnerCatsEntity() {
    }

    public OwnerCatsEntity(int idOwner, Integer idCat) {
        this.idOwner = idOwner;
        this.idCat = idCat;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCatsEntity that = (OwnerCatsEntity) o;
        return idOwner == that.idOwner && Objects.equals(idCat, that.idCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOwner, idCat);
    }

    public OwnerEntity getOwnerByIdOwner() {
        return ownerByIdOwner;
    }

    public void setOwnerByIdOwner(OwnerEntity ownerByIdOwner) {
        this.ownerByIdOwner = ownerByIdOwner;
    }

    public CatEntity getCatByIdCat() {
        return catByIdCat;
    }

    public void setCatByIdCat(CatEntity catByIdCat) {
        this.catByIdCat = catByIdCat;
    }
}
