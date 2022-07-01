package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cat", schema = "public", catalog = "lab3")
public class Cat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @Column(name = "id", nullable = false)
    @Column(name = "id")
    private int id;

    //    @Column(name = "name", nullable = false, length = -1)
    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private java.sql.Timestamp birthDate;

    //    @Column(name = "colour", nullable = true, length = -1)
    @Column(name = "colour")
    private String colour;

//    @OneToOne(cascade =CascadeType.ALL)
//    @JoinColumn(name = "id_owner")
//    private Owner owner;


    //    @Column(name = "bread", nullable = true, length = -1)
    @Column(name = "bread")
    private String bread;

    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "owners_id")
    private Owner owner;

//    @OneToOne(mappedBy = "catByIdCat")
//    private CatsFriendEntity catsFriendById;
//    @OneToMany(mappedBy = "catByIdCatFriend")
//    private List<CatsFriendEntity> catsFriendsById;
//    @OneToMany(mappedBy = "catByIdCat")
//    private List<OwnerCatsEntity> ownerCatsById;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "cat_friends",
            joinColumns = { @JoinColumn(name = "first_friend_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "second_friend_id", referencedColumnName = "id") }
    )
    private List<Cat> friends;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "friends")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Cat> friendsAnother;



    public Cat() {
    }

    public Cat(String name, java.sql.Timestamp birthDate, String colour, String bread) {
        this.name = name;
        this.birthDate = birthDate;
        this.colour = colour;
        this.bread = bread;
    }
    public Cat(String name, java.sql.Timestamp birthDate, String colour, String bread,Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.colour = colour;
        this.bread = bread;
        this.owner=owner;
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
    public List<Cat> getFriendsAnother() {
        return friendsAnother;
    }

    public void setFriendsAnother(List<Cat> friendsAnother) {
        this.friendsAnother = friendsAnother;
    }
//    public Integer getIdOwner() {
//        return idOwner;
//    }
//
//    public void setIdOwner(Integer idOwner) {
//        this.idOwner = idOwner;
//    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setFriends(List<Cat> friends) {
        this.friends = friends;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Cat> getFriends() {
        return friends;
    }
    //    public OwnerEntity getOwnerByIdOwner() {
//        return ownerByIdOwner;
//    }
//
//    public void setOwnerByIdOwner(OwnerEntity ownerByIdOwner) {
//        this.ownerByIdOwner = ownerByIdOwner;
//    }
//
//    public CatsFriendEntity getCatsFriendById() {
//        return catsFriendById;
//    }
//
//    public void setCatsFriendById(CatsFriendEntity catsFriendById) {
//        this.catsFriendById = catsFriendById;
//    }
//
//    public List<CatsFriendEntity> getCatsFriendsById() {
//        return catsFriendsById;
//    }
//
//    public void setCatsFriendsById(List<CatsFriendEntity> catsFriendsById) {
//        this.catsFriendsById = catsFriendsById;
//    }
//
//    public List<OwnerCatsEntity> getOwnerCatsById() {
//        return ownerCatsById;
//    }
//
//    public void setOwnerCatsById(List<OwnerCatsEntity> ownerCatsById) {
//        this.ownerCatsById = ownerCatsById;
//    }

}
