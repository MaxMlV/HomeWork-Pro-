package XMLFromYahoo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
public class Row {
    @XmlElement
    private Rate[] exchangerate;

    public Rate[] getExchangerate() {
        return exchangerate;
    }
}
