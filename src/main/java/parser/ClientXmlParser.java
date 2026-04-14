package parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import model.Client;
import model.Clients;
import service.ClientService;

import java.io.File;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Slf4j
public class ClientXmlParser {
    private static final Logger LOGGER =
            Logger.getLogger(ClientService.class.getName());
    public List<Client> parse(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Clients.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            LOGGER.info("Start to parse Clients from file: " + filePath);
            Clients clients = (Clients) unmarshaller.unmarshal(
                    new File(filePath)
            );
            LOGGER.info("Finish to parse Clients from file: " + filePath);
            return clients.getClients();

        } catch (JAXBException e) {
            throw new RuntimeException("Error parsing client xml", e);
        }
    }
}
