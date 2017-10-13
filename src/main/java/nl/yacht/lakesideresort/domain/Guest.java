package nl.yacht.lakesideresort.domain;

public class Guest {

    //variabelen
    private int guestNumber;
    private String surname;
    private String firstName;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String mailAddress;

    //Constructor met alle variabelen
    public Guest(int guestNumber, String surname, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAddress) {
        this.guestNumber = guestNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

    public void updateGuest(String surname, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAddress) {
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public void printGuest(){
        System.out.println("Guest Number: " + this.guestNumber);
        System.out.println("Surname: " + this.surname);
        System.out.println("Firstname: " + this.firstName);
        System.out.println("Address: " + this.address);
        System.out.println("Postal Code: " + this.postalCode);
        System.out.println("City: " + this.city);
        System.out.println("Country: " + this.country);
        System.out.println("Phone: " + this.phoneNumber);
        System.out.println("Mail: " + this.mailAddress);
    }
}