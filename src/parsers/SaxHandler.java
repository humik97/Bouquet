package parsers;

import accessories.Accessories;
import accessories.Paper;
import accessories.Tape;
import bouquets.Bouquet;
import bouquets.Bouquets;
import com.sun.istack.internal.logging.Logger;
import flower.Chamomile;
import flower.Flower;
import flower.Rose;
import flower.Tulip;

/**
 * Created by java on 11/28/2017.
 */
public class SaxHandler extends org.xml.sax.helpers.DefaultHandler implements Parametres {
    final static Logger logger = Logger.getLogger(SaxHandler.class);
    int count=0;
    Bouquet current;
    Flower flower;
    Accessories accessories;

    @Override
    public void startDocument() throws org.xml.sax.SAXException {
        super.startDocument();
        logger.warning("start parsing..");
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
        switch (qName) {
            case "bouquet":
                current=new Bouquet();
                current.setId(attributes.getValue(0));break;
            case "name":
                count = NAME;
                break;
            case "flname":
                count = FLNAME;
                break;
            case "acname":
                count = ACNAME;
                break;
            case "price":
                count = PRICE;
                break;
            case "date":
                count = DATE;
                break;
            case "acprice":
                count=ACPRICE;
                break;
            case "length":
                count=LENGTH;
                break;
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws org.xml.sax.SAXException {
        super.characters(chars, i, i1);
        String s = new String(chars, i, i1).trim();
        if (current != null) {
            switch (count) {
                case ACNAME:
                    if (s.equals("tape")) {
                        accessories = new Tape();
                    } else if (s.equals("paper")) {
                        accessories = new Paper();
                    }
                    accessories.setName(s);
                    count = 0;
                    break;
                case FLNAME:
                    switch (s) {
                        case "rose":
                            flower = new Rose();
                            flower.setName(s);
                            count = 0;
                            break;
                        case "tulip":
                            flower = new Tulip();
                            flower.setName(s);
                            count=0;
                            break;
                        case "chamomile":
                            flower = new Chamomile();
                            flower.setName(s);
                            count=0;
                            break;
                    }
                    break;
                case PRICE:
                    current.totalprice+=Integer.parseInt(s);
                    flower.setPrice(Integer.parseInt(s));
                    count = 0;
                    break;
                case ACPRICE:
                    current.totalprice+=Integer.parseInt(s);
                    accessories.setPrice(Integer.parseInt(s));
                    count = 0;
                    break;
                case DATE:
                    flower.setData(s);
                    count = 0;
                    break;
                case LENGTH:
                    flower.setLength(Double.parseDouble(s));
                    count=0;
                    break;
                case NAME:
                    current.setName(s);
                    count = 0;
                    break;
            }
        }
    }

    @Override
    public void endElement(String s, String s1, String s2) throws org.xml.sax.SAXException {
        super.endElement(s, s1, s2);
        switch (s2) {
            case "flower":
                current.flowers.add(flower);
                break;
            case "bouquet":
                Bouquets.addBouquet(current);
                logger.info(current.toString());
                break;
            case "accessory":
                current.setAccessories(accessories);
                break;
        }
    }

    @Override
    public void endDocument() throws org.xml.sax.SAXException {
        super.endDocument();
        logger.warning("Stop parsing...");
    }
}

