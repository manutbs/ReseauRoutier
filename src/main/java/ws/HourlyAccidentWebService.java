package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import metier.accident;

import java.io.File;
import java.util.List;

@WebService
public class HourlyAccidentWebService {
    private List<accident> accidentsHourData;
    public HourlyAccidentWebService() {
        loadaccidentsHourDataFromXML();
    }
    private void loadaccidentsHourDataFromXML() {
        try {
            File file = new File("C:\\testSoc\\src\\main\\resources\\heure2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsHourData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public int HourGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.accident acc : accidentsHourData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }
        return totalAccidents + totalTues + totalBlesses;
    }
    @WebMethod
    public metier.accident getAccidentDetailByHour(@WebParam(name = "heure") String hour) {
        for (metier.accident acc : accidentsHourData) {
            if (acc.getLabelle().equalsIgnoreCase(hour)) {
                return acc;
            }
        }
        return new metier.accident();
    }

}
