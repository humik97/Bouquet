package flower;

import javax.xml.bind.annotation.XmlElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by java on 11/28/2017.
 */
public class Flower
{
    private String name;
    private double length;
    private String date;
    private int price;

    public String getName()
    {
        return name;
    }
    @XmlElement(name="flname")
    public void setName(String name)
    {
        this.name = name;
    }

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Flower()
    {
    }

    public String getDate()
    {
        return date;
    }

    public void setLength(double length)
    {
        this.length = length;
    }

    @XmlElement(name="price")
    public void setPrice(int price)
    {
        this.price = price;
    }
    @XmlElement(name="date")
    public void setData(String date)
    {
        this.date = date;
    }

    public double getLength()
    {
        return length;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        if (Double.compare(flower.length, length) != 0) return false;
//        if (price != flower.price) return false;
        if (date != null ? !date.equals(flower.date) : flower.date != null) return false;
        return format != null ? format.equals(flower.format) : flower.format == null;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        //      result = 31 * result + price;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        return result;
    }


    @Override
    public String toString()
    {
        return "Flower{" +name+": "+
                "length=" + length +
                ", date:" + date +
                ", price=" + price +
                '}';
    }
}
