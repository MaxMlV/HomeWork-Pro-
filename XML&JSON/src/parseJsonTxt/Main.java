package parseJsonTxt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Main {

    public static String getJson(String path) {
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String str = getJson("C:\\Development\\HomeWork (Pro)\\XML&JSON\\src\\parseJsonTxt\\json.txt");
        Gson gson = new GsonBuilder().create();

        Person person = (Person) gson.fromJson(str, Person.class);
        System.out.println(person.toString());
    }
}
