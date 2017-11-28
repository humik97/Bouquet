package bouquets;

import accessories.Accessories;
import flower.Chamomile;
import flower.Flower;
import flower.Tulip;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by java on 11/28/2017.
 */
public class Bouquet {
    String name;
    String id;
    public static List<Flower> flowers =new ArrayList<>();
    Accessories accessories;
    public int totalprice;
    @XmlElement(name="accessory")
    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }
    public void setTulip(){
        flowers.add(new Tulip());
    }
    public String getId() {
        return id;
    }
    @XmlElement(name="flower")
    public List<Flower> getFlowers() {
        return flowers;
    }
    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setChamomile(){
        flowers.add(new Chamomile());
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "bouquets.Bouquet{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", flowers=" + flowers +
                ", accessories=" + accessories +
                ", totalprice=" + totalprice +
                '}';
    }
}

