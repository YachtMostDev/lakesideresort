package nl.yacht.lakesideresort.domain;

public class Guest {

    //variabelen
    private int guestNumber;
    private String surname;
    private String firstName;
    private String adress;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String mailAdress;

    //Constructor met alle variabelen
    public Guest(int guestNumber, String surname, String firstName, String adress, String postalCode, String city, String country, String phoneNumber, String mailAdress) {
        this.guestNumber = guestNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
    }

    public void updateGuest(String surname, String firstName, String adress, String postalCode, String city, String country, String phoneNumber, String mailAdress) {
        this.surname = surname;
        this.firstName = firstName;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
    }
    //Setters&getters
    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public void printGuest(){
        System.out.println("Guest Number: " + this.guestNumber);
        System.out.println("Surname: " + this.surname);
        System.out.println("Firstname: " + this.firstName);
        System.out.println("Adress: " + this.adress);
        System.out.println("Postal Code: " + this.postalCode);
        System.out.println("City: " + this.city);
        System.out.println("Country: " + this.country);
        System.out.println("Phone: " + this.phoneNumber);
        System.out.println("Mail: " + this.mailAdress);
    }
}