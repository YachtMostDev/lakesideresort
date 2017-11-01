//package nl.yacht.lakesideresort.domain;
//
//import nl.yacht.lakesideresort.controller.GuestRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Assert;
//
//public class GuestTest {
//
//
//    private String fn = "Firstname";
//    private String sn = "Surname";
//    private String address = "Address";
//    private String postal = "PostalCode";
//    private String country = "Country";
//    private String email = "EmailAddress";
//    private String phone = "PhoneNumber";
//    private String city = "City";
//    private Guest guest;
//    private Guest gcGuest;
//    private GuestRepository gc;
//
//    @Before
//    public void setUp(){
//        guest = new Guest(1, sn, fn, address, postal, city, country, phone, email);
//        gc = new GuestRepository();
//        this.gcGuest = gc.createNewGuest(guest.getSurName(),
//                guest.getFirstName(),
//                guest.getAddress(),
//                guest.getPostalCode(),
//                guest.getCity(),
//                guest.getCountry(),
//                guest.getPhoneNumber(),
//                guest.getMailAddress());
//    }
//
//    @Test
//    public void testGetGuestFromController(){
//        Guest g = gc.getGuestFromList(gcGuest.getGuestNumber());
//        Assert.assertTrue(g.getGuestNumber() == gcGuest.getGuestNumber());
//    }
//
//    @Test
//    public void testGetFirstname(){
//        Assert.assertTrue(guest.getFirstName().equals(this.fn));
//    }
//    @Test
//    public void testSetFirstname(){
//        String newFirstName = "NewFirstName";
//        guest.setFirstName(newFirstName);
//        Assert.assertTrue(guest.getFirstName().equals(newFirstName));
//    }
//
//    @Test
//    public void testGetSurname(){
//        Assert.assertTrue(guest.getSurName().equals(this.sn));
//    }
//    @Test
//    public void testSetSurname(){
//        String newSurName = "NewSurName";
//        guest.setSurName(newSurName);
//        Assert.assertTrue(guest.getSurName().equals(newSurName));
//    }
//
//    @Test
//    public void testGetAddress(){
//        Assert.assertTrue(guest.getAddress().equals(this.address));
//    }
//    @Test
//    public void testSetAddress(){
//        String newAddress = "NewAddress";
//        guest.setAddress(newAddress);
//        Assert.assertTrue(guest.getAddress().equals(newAddress));
//    }
//
//    @Test
//    public void testGetCity(){
//        Assert.assertTrue(guest.getCity().equals(this.city));
//    }
//    @Test
//    public void testSetCity(){
//        String newCity = "NewCity";
//        guest.setCity(newCity);
//        Assert.assertTrue(guest.getCity().equals(newCity));
//    }
//
//    @Test
//    public void testGetPhoneNumber(){
//        Assert.assertTrue(guest.getPhoneNumber().equals(this.phone));
//    }
//    @Test
//    public void testSetPhoneNumber(){
//        String newPhonenumber = "New PhoneNumber";
//        guest.setPhoneNumber(newPhonenumber);
//        Assert.assertTrue(guest.getPhoneNumber().equals(newPhonenumber));
//    }
//
//    @Test
//    public void testGetEmail(){
//        Assert.assertTrue(guest.getMailAddress().equals(this.email));
//    }
//    @Test
//    public void testSetEmail(){
//        String newEmail = "NewEmailAddress";
//        guest.setMailAddress(newEmail);
//        Assert.assertTrue(guest.getMailAddress().equals(newEmail));
//    }
//
//    @Test
//    public void testGetCountry(){
//        Assert.assertTrue(guest.getCountry().equals(this.country));
//    }
//    @Test
//    public void testSetCountry(){
//        String newCountry = "NewCountry";
//        guest.setCountry(newCountry);
//        Assert.assertTrue(guest.getCountry().equals(newCountry));
//    }
//
//    @Test
//    public void testGetPostal(){
//        Assert.assertTrue(guest.getPostalCode().equals(this.postal));
//    }
//    @Test
//    public void testSetPostal(){
//        String newPostalCOde = "NewPostalCOde";
//        guest.setPostalCode(newPostalCOde);
//        Assert.assertTrue(guest.getPostalCode().equals(newPostalCOde));
//    }
//}
//
