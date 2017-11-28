package bouquets;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by java on 11/28/2017.
 */
@XmlRootElement
public class Bouquets {
    static List<Bouquet> bouquets=new ArrayList<>();
    public List<Bouquet> getBouquets() {
        return bouquets;
    }

    public static void addBouquet(Bouquet bouquet){
        bouquets.add(bouquet);
    }
    @XmlElement(name="bouquet")
    public void setBouquets(List<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    @Override
    public String toString() {
        return "bouquets.Bouquets{" +
                "bouquets=" + bouquets +
                '}';
    }
}
