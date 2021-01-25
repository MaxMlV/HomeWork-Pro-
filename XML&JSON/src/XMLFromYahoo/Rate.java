package XMLFromYahoo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exchangerate")
public class Rate {
    private String baseCurrency;
    private String currency;
    private float saleRateNB;
    private float purchaseRateNB;
    private float saleRate;
    private float purchaseRate;

    public Rate(String baseCurrency, String currency, float saleRateNB, float purchaseRateNB, float saleRate, float purchaseRate) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    @XmlAttribute
    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlAttribute
    public float getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(float saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    @XmlAttribute
    public float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(float purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    @XmlAttribute
    public float getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(float saleRate) {
        this.saleRate = saleRate;
    }

    @XmlAttribute
    public float getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(float purchaseRate) {
        this.purchaseRate = purchaseRate;
    }
}
