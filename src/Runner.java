import org.xml.sax.SAXException;
import parsers.DomHandler;
import parsers.JaxbHandler;
import parsers.SaxHandler;
import parsers.StaxHandler;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

/**
 * Created by java on 11/28/2017.
 */
public class Runner {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, JAXBException, XMLStreamException {
        String file = "src/bouquet.xml";
        SAXParserFactory factory= SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler= new SaxHandler();
        parser.parse(new File(file),handler);
        DomHandler handler1 = new DomHandler();
        handler1.BuildBouquets(file);
        StaxHandler handler2 = new StaxHandler();
        JaxbHandler handler3= new JaxbHandler();
    }
}

