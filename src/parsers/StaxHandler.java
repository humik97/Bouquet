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

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

/**
 * Created by java on 11/28/2017.
 */
public class StaxHandler implements Parametres{
    final static Logger logger = Logger.getLogger(StaxHandler.class);
    Bouquet current;
    Accessories acc;
    Flower flower;
    int count=0;
    public StaxHandler() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("src/bouquet.xml"));
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();

                    String qName = startElement.getName().getLocalPart();
                    switch (qName.toString()){
                        case "bouquet":
                            current=new Bouquet();
                            Iterator iterator = startElement.getAttributes();
                            Attribute attribute = (Attribute) iterator.next();
                            current.setId(attribute.getValue());
                        case "name":
                            count=NAME;
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
                            break;}
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    switch (count) {
                        case NAME:
                            current.setName(characters.getData());
                            count = 0;
                            break;
                        case FLNAME:
                            switch (characters.getData()) {
                                case "rose":
                                    flower = new Rose();
                                    break;
                                case "tulip":flower= new Tulip();
                                    break;
                                case "chamomile":flower =new Chamomile();
                                    break;
                            }
                            flower.setName(characters.getData());
                            count = 0;
                            break;
                        case LENGTH:
                            flower.setLength(Integer.parseInt(characters.getData()));
                            count = 0;
                            break;
                        case PRICE:
                            flower.setPrice(Integer.parseInt(characters.getData()));
                            count = 0;
                            current.totalprice+=Integer.parseInt(characters.getData());
                            break;
                        case DATE:
                            flower.setData(characters.getData());
                            count = 0;
                            break;
                        case ACNAME:
                            switch (characters.getData()) {
                                case "paper":
                                    acc = new Paper();
                                    break;
                                case "tape":
                                    acc = new Tape();
                                    break;
                            }
                            acc.setName(characters.getData());
                            count = 0;
                            break;
                        case ACPRICE:
                            acc.setPrice(Integer.parseInt(characters.getData()));
                            current.totalprice+=Integer.parseInt(characters.getData());
                            count = 0;
                            break;
                    }break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    switch (endElement.getName().getLocalPart()){
                        case "flower":
                            current.flowers.add(flower);
                            break;
                        case "accessory":
                            current.setAccessories(acc);
                            break;
                        case  "bouquet":
                            Bouquets.addBouquet(current);
                            logger.info(current.toString());
                            break;
                    }break;
            }
        }
    }
}
