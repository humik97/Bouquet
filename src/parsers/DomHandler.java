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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by java on 11/28/2017.
 */
public class DomHandler {
    final static Logger logger = Logger.getLogger(DomHandler.class);
    DocumentBuilder builder;
    Bouquet current;
    Accessories acc;
    Flower flower = new Chamomile();

    public DomHandler() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
        builder = dom.newDocumentBuilder();
    }

    public void BuildBouquets(String file) throws IOException, SAXException {
        Document document;
        document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList bouquetsList = element.getElementsByTagName("bouquet");
        for (int a = 0; a < bouquetsList.getLength(); a++) {
            Element bouquetlist = (Element) bouquetsList.item(a);
            logger.info(BuildBouquet(bouquetlist).toString());
            Bouquets.addBouquet(BuildBouquet(bouquetlist));
        }
    }

    public Bouquet BuildBouquet(Element bouquetList) {
        current = new Bouquet();
        if (bouquetList.getNodeType() == Node.ELEMENT_NODE) {
            current.setId(bouquetList.getAttribute("id"));
            current.setName(bouquetList.getElementsByTagName("name").item(0).getTextContent());
            NodeList lmnt = bouquetList.getElementsByTagName("flower");
            for (int a = 0; a < lmnt.getLength(); a++) {
                Element flowerList = (Element) lmnt.item(a);
                current.flowers.add(BuildFlower(flowerList));
            }
            NodeList aclmnt = bouquetList.getElementsByTagName("accessory");
            Element accessory = (Element) aclmnt.item(0);
            if (aclmnt.item(0)!=null) {
                current.setAccessories(BuildAccessories(accessory));
            }
        }
        return current;
    }

    public Flower BuildFlower(Element flowerList) {
        if (flowerList.getNodeType() == Node.ELEMENT_NODE) {
            switch (flowerList.getElementsByTagName("flname").item(0).getTextContent()) {
                case "rose":
                    flower = new Rose();
                    break;
                case "tulip":
                    flower = new Tulip();
                    break;
                case "chamomile":
                    flower = new Chamomile();
                    break;
            }
            flower.setLength(Integer.parseInt(flowerList.getElementsByTagName("length").item(0).getTextContent()));
            flower.setPrice(Integer.parseInt(flowerList.getElementsByTagName("price").item(0).getTextContent()));
            flower.setName(flowerList.getElementsByTagName("flname").item(0).getTextContent());
            flower.setData(flowerList.getElementsByTagName("date").item(0).getTextContent());
            current.totalprice+=Integer.parseInt(flowerList.getElementsByTagName("price").item(0).getTextContent());
        }
        return flower;
    }

    public Accessories BuildAccessories(Element accessories) {
        if (accessories.getNodeType() == Node.ELEMENT_NODE) {
            switch (accessories.getElementsByTagName("acname").item(0).getTextContent()) {
                case "paper":
                    acc = new Paper();
                    break;
                case "tape":
                    acc = new Tape();
                    break;
            }
            current.totalprice+=Integer.parseInt(accessories.getElementsByTagName("acprice").item(0).getTextContent());
            acc.setName(accessories.getElementsByTagName("acname").item(0).getTextContent());
            acc.setPrice(Integer.parseInt(accessories.getElementsByTagName("acprice").item(0).getTextContent()));
        }
        return acc;
    }
}


