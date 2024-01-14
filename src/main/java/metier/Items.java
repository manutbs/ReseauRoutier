package metier;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
public class Items {
    private List<accident> accidents;

    @XmlElement(name = "item")
    public List<accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<accident> accidents) {
        this.accidents = accidents;
    }
}
