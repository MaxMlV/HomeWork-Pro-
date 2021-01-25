package parseJsonTxt;

public class Person {
    private String name;
    private String surname;
    private String phone;
    private String site;
    private Address address;

    public Person(String name, String surname, String phone, String site, Address address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.site = site;
        this.address = address;
    }

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", site='" + site + '\'' +
                ", address=" + address +
                '}';
    }
}
