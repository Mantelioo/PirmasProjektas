package lt.vu.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//Independed entities. Sukurtas automobilis gali egzistuoti be Shop esybes

@Entity //Entity, kluris bus toks pat objektas duomenu bazeje
@NamedQueries({@NamedQuery(name = "Car.findAll", query = "select c from Car as c")})
public class Car //By default, JPA imama CAR lentele.
{
    private Long carID;
    private String description;
    private Set<Shop> carShops;
    private String gearbox;
    private int speed;

    @Id //Kiekvienas entity turi turetu ID lauka is duomenu bazeje. Tai yra primary key siuo atveju
    @GeneratedValue
    @Column(name = "ID") //Priskiriamas default stulpelis lenteleje.
    //set ir get. Turetu buti galima naudoti ir Lombok.
    public Long getCarID()
    {
        return carID;
    }
    public void setCarID(long id)
    {
        this.carID = id;
    }


    @Basic
    @Column(name = "Gearbox")
    public String getGearbox() {
        return gearbox;
    }
    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }


    @Basic
    @Column(name = "Speed")
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Basic //Paprastas mappinimo budas.
    @Column(name = "Description") // default column
    //set ir get. Turetu buti galima naudoti ir Lombok.
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    //Daug automobiliu rodyus i daug parduotuviu

    @ManyToMany(cascade = CascadeType.ALL) //Many To Many rysys, sukurta tarpine lentele duomenu bazeje
    @JoinTable(
    name = "SHOPCARS",
    joinColumns = @JoinColumn(name = "CAR_ID"),
    inverseJoinColumns = @JoinColumn(name = "SHOP_ID"))
    public Set<Shop> getCarShops()
    {
        return carShops;
    }
    public void setCarShops(Set<Shop> carShops)
    {
        this.carShops = carShops;
    }

    //Verslo raktas reikalingas jeigu pas mus objektas duomenu bazeje gali buti sukurtas ir be PrimaryKey, pvz gali nebuti ID

    @Override //perrasomi default metodai. Verslo raktas, kuris susidaro is keliu duomenu baziu savybiu. Juos galima perrasyti
    public boolean equals(Object o) //Palygina ar tikrai objektas yra tas, kurio ieskomm. This == o
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carID == car.carID && Objects.equals(description, car.description);
    }

    //Galima naudoti ir sudetingesne funkcija. Esme ta, kad mums reikia sugeneruoti kiek imanoma unikalesni rakta.
    @Override //Padeda greiciaus susirasti objekta. Naudojant hashTables
    public int hashCode()
    {
        return Objects.hash(carID, description);
    }
}
