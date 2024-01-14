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
public class AccidentImpliedByWebService {
    private List<metier.accident> accidentsImpliedByData;

    public AccidentImpliedByWebService() {
        loadaccidentsImpliedByDataFromXML();
    }
    private void loadaccidentsImpliedByDataFromXML() {
        try {
            File file = new File("C:\\testSoc\\src\\main\\resources\\implique2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsImpliedByData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public int ImpliedByGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.accident acc : accidentsImpliedByData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }
        return totalAccidents + totalTues + totalBlesses;
    }
    @WebMethod
    public metier.accident getAccidentDetailByImpliedBy(@WebParam(name = "ImpliedBy") String ImpliedBy) {
        for (metier.accident acc : accidentsImpliedByData) {
            if (acc.getLabelle().equalsIgnoreCase(ImpliedBy)) {
                return acc;
            }
        }
        return new metier.accident();
    }
    @WebMethod
    public void AddAccidentImpliedBy(String ImpliedByLabelle, accident newAccident) {
        accidentsImpliedByData.add(newAccident);
    }
    @WebMethod
    public void DeleteAccidentByGovernment(String ImpliedByLabelle) {
        accidentsImpliedByData.removeIf(acc -> acc.getLabelle().equals(ImpliedByLabelle));
    }
}
