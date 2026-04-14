package service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import model.Client;
import model.Clients;
import parser.ClientXmlParser;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
public class ClientService {
    private static final Logger LOGGER =
            Logger.getLogger(ClientService.class.getName());

    public static void main(String[] args) throws JAXBException {
        ClientXmlParser parser = new ClientXmlParser();
        LOGGER.log(Level.INFO, "Starting ClientService");
        List<Client> clients = parser.parse("C:\\Eleks\\java_proj\\xml_java_gradel\\src\\main\\resources\\clients.xml");
        // clients.forEach(System.out::println);
        List<Client> activeClients = clients.stream().filter(c -> !c.isActive()).toList();
        //activeClients.forEach(System.out::println);
        List<Client> olderThanTwentyFive = clients.stream().filter(c -> c.getAge() > 25).toList();
        olderThanTwentyFive.forEach(System.out::println);
        long newCount = clients.stream().filter(Client::isActive).count();
        System.out.println(newCount);
        List<Client> sortedClients = clients.stream().sorted(Comparator.comparing(Client::getAge)).collect(Collectors.toList());
        sortedClients.forEach(System.out::println);
        Clients wrapper = new Clients(sortedClients);
        JAXBContext context = JAXBContext.newInstance(Clients.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapper,new File("C:\\Eleks\\java_proj\\xml_java_gradel\\src\\main\\resources\\clients.xml"));

    }
}
