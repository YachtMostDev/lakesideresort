package nl.yacht.lakesideresort.domain;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Guest {

    //variabelen
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long guestNumber;
	@NotNull
    private String surName;
	@NotNull
    private String firstName;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String mailAddress;

    public Guest(){}

    //Constructor met alle variabelen
    public Guest(long guestNumber, String surName, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAddress) {
        this.guestNumber = guestNumber;
        this.surName = surName;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

    public void updateGuest(String surname, String firstName, String address, String postalCode, String city, String country, String phoneNumber, String mailAddress) {
        this.surName = surname;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }
    //Setters&getters
    public long getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(long guestNumber) {
        this.guestNumber = guestNumber;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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

    public String toString(){
        StringBuilder sb = new StringBuilder(512);
        sb.append("Guest Number: " + this.guestNumber);
        sb.append("\nSurname: " + this.surName);
        sb.append("\nFirstname: " + this.firstName);
        sb.append("\nAddress: " + this.address);
        sb.append("\nPostal Code: " + this.postalCode);
        sb.append("\nCity: " + this.city);
        sb.append("\nCountry: " + this.country);
        sb.append("\nPhone: " + this.phoneNumber);
        sb.append("\nMail: " + this.mailAddress);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (surName != null ? !surName.equals(guest.surName) : guest.surName != null) return false;
        if (firstName != null ? !firstName.equals(guest.firstName) : guest.firstName != null) return false;
        if (address != null ? !address.equals(guest.address) : guest.address != null) return false;
        if (postalCode != null ? !postalCode.equals(guest.postalCode) : guest.postalCode != null) return false;
        if (city != null ? !city.equals(guest.city) : guest.city != null) return false;
        if (country != null ? !country.equals(guest.country) : guest.country != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(guest.phoneNumber) : guest.phoneNumber != null) return false;
        return mailAddress != null ? mailAddress.equals(guest.mailAddress) : guest.mailAddress == null;
    }
}