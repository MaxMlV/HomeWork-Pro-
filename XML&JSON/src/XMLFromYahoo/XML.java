package XMLFromYahoo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exchangerates")
public class XML {
    @XmlElement
    private Row[] rows;

    public Row[] getRows() {
        return rows;
    }
}
