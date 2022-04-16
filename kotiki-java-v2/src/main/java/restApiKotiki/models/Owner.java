package restApiKotiki.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owner", schema = "public", catalog = "lab3")
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
//    @Column(name = "id", nullable = false)
    private int id;

//    @Column(name = "name", nullable = false, length = -1)
    @Column(name = "name")
    private String name;

    @Column(name = "birth_date_owner")
    private java.sql.Timestamp birthDateOwner;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats;


    public Owner() {
    }

//    public Owner(String name) {
//        this.name = name;
//    }
    public Owner(String name, java.sql.Timestamp birthDateOwner) {
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


}
