package ws;

import jakarta.jws.WebParam;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@WebService
public class AccidentReasonWebService {
    private List<metier.accident> accidentsCauseData;

    public AccidentReasonWebService() {
        loadaccidentsCauseDataFromXML();
    }

    private void loadaccidentsCauseDataFromXML() {
        try {
            File file = new File("C:\\testSoc\\src\\main\\resources\\cause2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsCauseData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public int CauseGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.accident acc : accidentsCauseData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }

        return totalAccidents + totalTues + totalBlesses;
    }

    @WebMethod
    public metier.accident getAccidentDetailByCause(@WebParam(name = "cause") String cause) {
        for (metier.accident acc : accidentsCauseData) {
            if (acc.getLabelle().equalsIgnoreCase(cause)) {
                return acc;
            }
        }
        return new metier.accident();
    }
}
