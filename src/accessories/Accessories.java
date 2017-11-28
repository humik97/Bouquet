package accessories;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by java on 11/28/2017.
 */
public class Accessories {
    String name;
    public int tprice;
    public Accessories() {}
    public int getTprice()
    {
        return tprice;
    }
    @XmlElement(name="acprice")
    public void setPrice(int price)
    {
        this.tprice = price;
    }
    @XmlElement(name="acname")
    public void setName(String name) {
        this.name = name;}

    @Override
    public String toString() {
        return "Accessories{" +
                "name='" + name + '\'' +
                ", tprice=" + tprice +
                '}';
    }
}

