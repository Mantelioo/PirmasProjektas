package lt.vu.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //Entity, kuris bus toks pat objektas duomenu bazeje
@NamedQueries({@NamedQuery(name = "Shop.findAll", query = "select s from Shop as s")}) //vardine uzklausa. naudosiu veliau. Nereikes perrasyti per nauja
public class Shop {
    private long shopID;
    private String name;
    private Set<Car> shopCars = new HashSet<>(); //Kiekvienas automobiliu salonas/internetinis tinklalapis gali pardavineti automobilius.
    //saugomas ju sarasas

    @Id //Pimary key. Unikalas raktas Shop entity
    @GeneratedValue
    @Column(name = "ID") //Column in the dataBase
    //set ir get. Turetu buti galima naudoti ir Lombok.
    public  long getShopID()
    {
        return  this.shopID;
    }
    public  void setShopID(long id)
    {
        this.shopID = id;
    }

    @Basic //simple Mapping
    @Column(name = "NAME") //Column in the dataBase
    //set ir get. Turetu buti galima naudoti ir Lombok.
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    //Rysiai gali buti UNIDIRECTIONAL ir BiDIRECTIONAL
    //@OneToOne
    //@OneToMany
    //@ManyToMany

    //Bidirectional @OneToOne(mappedBy="shop")

    //Uztenka nurodyti tik koki rysi naudojam. ORM toliau pats susivarkys su rysiais.

    @ManyToMany(cascade = CascadeType.ALL) //Many To Many lentele. Tarpine lentele sujungti parduotuvem ir automobiliams
    @JoinTable(
            name = "SHOPCARS",
            joinColumns = @JoinColumn(name = "SHOP_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
    public Set<Car> getShopCars()
    {
        return this.shopCars;
    }
    public void setShopCars(Set<Car> shopCars)
    {
        this.shopCars = shopCars;
    }

    //JEI PERRASOME EQUALS METODA, REIKIA PERRASYTI IR HASHCODE METODA.

    @Override //Metodas, kuris palygina objektus
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return shopID == shop.shopID && Objects.equals(name, shop.name);
    }

    @Override //Padeda greiciaus susirasti objekta. Naudojant hashTables
    public int hashCode()
    {
        return Objects.hash(shopID, name);
    } //Nesu sukures verslo raktui papildomu stulpeliu, bet juos galima sudeti
    //arba panaudoti +20, +30 jei noretume kad operacija butu efektyvesne. Turetu IDE automatiskai sugeneruoti.

    //Lombok gali automatiskai sugeneruoti @EqualsAndHashCode(of={"name"})
}
