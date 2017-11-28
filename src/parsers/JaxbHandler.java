package parsers;

import bouquets.Bouquets;
import com.sun.istack.internal.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by java on 11/28/2017.
 */
public class JaxbHandler {
        private static final Logger log = Logger.getLogger(JaxbHandler.class);
        public JaxbHandler() throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(Bouquets.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Bouquets bouquet = (Bouquets) unmarshaller.unmarshal(new File("src/bouquet.xml"));
            log.info(bouquet.toString());
        }
}
