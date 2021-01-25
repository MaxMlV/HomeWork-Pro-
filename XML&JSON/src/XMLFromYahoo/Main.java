package XMLFromYahoo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.lang.reflect.Field;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        String request = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
        URL url = new URL(request);
        JAXBContext jc = JAXBContext.newInstance(XML.class);
        Unmarshaller u = jc.createUnmarshaller();
        XML xml = (XML) u.unmarshal(url);

        for (Row row : xml.getRows()) {
            Rate[] rates = row.getExchangerate();
            for (Rate rate : rates) {
                Field[] fields = rate.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.isAccessible()) field.setAccessible(true);
                    System.out.println(field.getName() + " = " + field.get(rate));
                }
            }
            System.out.println("-----------------------------------------");
        }
    }
}
