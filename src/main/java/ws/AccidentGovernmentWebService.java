package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import metier.accident;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@WebService
public class AccidentGovernmentWebService {
    private List<accident> accidentsGovernmentData;

    public AccidentGovernmentWebService() {
        loadaccidentsGovernmentDataFromXML();
    }
    private void loadaccidentsGovernmentDataFromXML() {
        try {
            File file = new File("C:\\testSoc\\src\\main\\resources\\gouvernorat2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsGovernmentData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public int GovernmentGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.accident acc :accidentsGovernmentData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }
        return totalAccidents + totalTues + totalBlesses;
    }
    @WebMethod
    public String GetGouvermentWithMaxBlesses() {
        String gouvernementMaxBlesses = "";
        int maxBlesses = 0;

        for (metier.accident acc :accidentsGovernmentData) {
            int blesses = 0;
            if (acc.getBlesses() > maxBlesses) {
                maxBlesses = blesses;
                gouvernementMaxBlesses = acc.getLabelle();
            }
        }

        return gouvernementMaxBlesses;
    }
    @WebMethod
    public void DeleteAccidentByGovernment(String governmentLabelle) {
        accidentsGovernmentData.removeIf(acc -> acc.getLabelle().equals(governmentLabelle));
    }

    @WebMethod
    public void AddAccidentGovernment(String governmentLabelle, accident newAccident) {
        accidentsGovernmentData.add(newAccident);
    }
    @WebMethod
    public void AddAccidentByUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le labelle du gouvernement : ");
        String labelle = scanner.nextLine();

        System.out.println("Entrez le nombre d'accidents : ");
        int accidents = scanner.nextInt();

        System.out.println("Entrez le nombre de tués : ");
        int tues = scanner.nextInt();

        System.out.println("Entrez le nombre de blessés : ");
        int blesses = scanner.nextInt();

        accident newAccident = new accident(labelle, accidents, tues, blesses);
        accidentsGovernmentData.add(newAccident);

        System.out.println("Accident ajouté avec succès.");
    }
}
