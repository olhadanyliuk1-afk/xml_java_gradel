package model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class Clients {

    @XmlElement(name = "client")
    private List<Client> clients;

}

