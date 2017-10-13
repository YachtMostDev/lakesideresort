package nl.yacht.lakesideresort.domain;

import nl.yacht.lakesideresort.controller.GuestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GuestTest {


    private String fn = "Firstname";
    private String sn = "Surname";
    private String address = "Address";
    private String postal = "PostalCode";
    private String country = "Country";
    private String email = "EmailAddress";
    private String phone = "PhoneNumber";
    private String city = "City";
    private Guest guest;
    @Before
    public void setUp(){
        guest = new Guest(1, sn, fn, address, postal, city, country, phone, email);
    }
    @Test
    public void testGuestController(){

    GuestController guestController = new GuestController();

    Guest guest1 = new Guest(guestController.generateGuestNumber(), "de Vries", "Henk", "Parkweg 42", "9462AB", "Groningen", "the Netherlands", "0612345678", "henk.de.vries@gmail.com");
        guestController.getGuestList().add(guest1);

    Guest guest2 = new Guest(guestController.generateGuestNumber(), "Jansen", "Thomas", "Schoolstraat 5", "9813JF", "Leek", "the Netherlands", "0609123102", "thomas.jansen@gmail.com");
        guestController.getGuestList().add(guest2);
        guestController.getGuestList().get(1).setGuestNumber(12);

    Guest guest3 = new Guest(guestController.generateGuestNumber(), "de Vries", "Alex", "Doorweg 12", "7671GH", "Zwolle", "the Netherlands", "0693559272", "alex.de.vries@gmail.com");
        guestController.getGuestList().add(guest3);
        //assert.assertEquals
    }

//    @Test
//    public void testFirstname(){
//        // gast maken met een voornaam(a)
//        // string checkvoornaam = gast.getvoornaam
//        // als checkvoornaam == voornaam(a)
//        // als true > test ok
//        // als false > test niet ok
//        String voornaam = "Voornaam";
//        Guest g = new Guest(1, "a", voornaam, "adres", "postcode", "woonplaats", "land", "telefoonnummer", "emailadres");
//        String checkVoornaam = g.getFirstName();
//        boolean result = false;
//        if (voornaam.equals(checkVoornaam)) {
//            result = true;
//        }
//        Assert.assertTrue(result);
//    }

    @Test
    public void testGetFirstname(){
        Assert.assertTrue(guest.getFirstName().equals(this.fn));
    }
    @Test
    public void testSetFirstname(){
        String newFirstName = "NewFirstName";
        guest.setFirstName(newFirstName);
        Assert.assertTrue(guest.getFirstName().equals(newFirstName));
    }

    @Test
    public void testGetSurname(){
        Assert.assertTrue(guest.getSurname().equals(this.sn));
    }
    @Test
    public void testSetSurname(){
        String newSurName = "NewSurName";
        guest.setSurname(newSurName);
        Assert.assertTrue(guest.getSurname().equals(newSurName));
    }

    @Test
    public void testGetAddress(){
        Assert.assertTrue(guest.getAddress().equals(this.address));
    }
    @Test
    public void testSetAddress(){
        String newAddress = "NewAddress";
        guest.setAddress(newAddress);
        Assert.assertTrue(guest.getAddress().equals(newAddress));
    }

    @Test
    public void testGetCity(){
        Assert.assertTrue(guest.getCity().equals(this.city));
    }
    @Test
    public void testSetCity(){
        String newCity = "NewCity";
        guest.setCity(newCity);
        Assert.assertTrue(guest.getCity().equals(newCity));
    }

    @Test
    public void testGetPhoneNumber(){
        Assert.assertTrue(guest.getPhoneNumber().equals(this.phone));
    }
    @Test
    public void testSetPhoneNumber(){
        String newPhonenumber = "New PhoneNumber";
        guest.setPhoneNumber(newPhonenumber);
        Assert.assertTrue(guest.getPhoneNumber().equals(newPhonenumber));
    }

    @Test
    public void testGetEmail(){
        Assert.assertTrue(guest.getMailAddress().equals(this.email));
    }
    @Test
    public void testSetEmail(){
        String newEmail = "NewEmailAddress";
        guest.setMailAddress(newEmail);
        Assert.assertTrue(guest.getMailAddress().equals(newEmail));
    }

    @Test
    public void testGetCountry(){
        Assert.assertTrue(guest.getCountry().equals(this.country));
    }
    @Test
    public void testSetCountry(){
        String newCountry = "NewCountry";
        guest.setCountry(newCountry);
        Assert.assertTrue(guest.getCountry().equals(newCountry));
    }

    @Test
    public void testGetPostal(){
        Assert.assertTrue(guest.getPostalCode().equals(this.postal));
    }
    @Test
    public void testSetPostal(){
        String newPostalCOde = "NewPostalCOde";
        guest.setPostalCode(newPostalCOde);
        Assert.assertTrue(guest.getPostalCode().equals(newPostalCOde));
    }
}

