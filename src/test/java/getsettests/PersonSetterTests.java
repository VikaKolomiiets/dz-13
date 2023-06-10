package getsettests;

import exceptions.NameException;
import exceptions.ObjectNullException;
import exceptions.OutOfDataRangeException;
import listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.*;
import personalization.Man;
import personalization.Person;
import personalization.Status;
import personalization.Woman;

import java.time.LocalDate;
@Listeners(PersonTestListener.class)
public class PersonSetterTests {
    Person person;
    @BeforeMethod
    public void setUpPerson(){
        person = new Man("Van", "Gog", LocalDate.of(1995, 02,17));
    }

    @AfterMethod
    public void tearDown(){
        person = null;
    }

    @Parameters("name")
    @Test
    public void testSetFirstNamePositive(String firstName){
        String nameBeforeSet = person.getFirstName();
        person.setFirstName(firstName);
        Assert.assertNotEquals(person.getFirstName(), nameBeforeSet, "Setter for First Name doesn't work");
        Assert.assertEquals( person.getFirstName(), firstName, "Setter for First Name doesn't work");
    }

    @Parameters("name")
    @Test
    public void testSetLastNamePositive(String lastName){
        String nameBeforeSet = person.getLastName();
        person.setLastName(lastName);
        Assert.assertNotEquals(person.getLastName(), nameBeforeSet, "Setter for Last Name doesn't work");
        Assert.assertEquals( person.getLastName(), lastName, "Setter for Last Name doesn't work");
    }

    @Parameters({"yyyy", "mm", "dd"})
    @Test
    public void testSetDateOfDeathPositive(int year, int month, int day){
        LocalDate dateBeforeSet = person.getDateOfDeath();
        person.setDateOfDeath(LocalDate.of(year, month, day));
        Assert.assertEquals(person.getDateOfDeath().getYear(), year, "Setter for Date of death doesn't work for year");
        Assert.assertEquals(person.getDateOfDeath().getMonth().getValue(), month, "Setter for Date of death doesn't work for month");
        Assert.assertEquals(person.getDateOfDeath().getDayOfMonth(), day, "Setter for Date of death doesn't work for day");
        Assert.assertNotEquals(person.getDateOfDeath(), dateBeforeSet, "Setter for Date of death doesn't work");
    }


    @Parameters({"name1", "name2"})
    @Test
    public void testPersonSetPartnerPositive(String firstName, String lastName){
        person.setPartner(new Woman(firstName, lastName, LocalDate.of(1998, 12, 30)));
        Assert.assertNotNull(person.getPartner());
        Assert.assertEquals(person.getPartner().getFirstName(), firstName, "Setter Partner doesn't work for first Name");
        Assert.assertEquals(person.getPartner().getLastName(), lastName, "Setter Partner doesn't work for last Name");
    }
    @Parameters("status")
    @Test
    public void testPersonSetStatusPositive(String status){
        person.setStatus(Status.valueOf(status));
        Assert.assertEquals(person.getStatus().name(), status, "Getter of Status doesn't work");
    }

    @Parameters("name-mistake")
    @Test
    public void testSetFirstNameException(String firstName){
        Assert.assertThrows(NameException.class, () -> person.setFirstName(firstName));
    }

    @Parameters("name-mistake")
    @Test
    public void testSetLastNameException(String lastName){
        Assert.assertThrows(NameException.class, () -> person.setLastName(lastName));
    }

    @Parameters("yyyy-bad")
    @Test
    public void testSetDateOfDeathException(int year){
        Assert.assertThrows(OutOfDataRangeException.class, () -> person.setDateOfDeath(LocalDate.of(year, 11, 10)));
    }

    @Parameters("name-null")
    @Test
    public void testSetFirstNameNullException(String name){
        Assert.assertThrows(ObjectNullException.class, () -> person.setFirstName(name));
    }

    @Parameters("name-null")
    @Test
    public void testSetLastNameNullException(String name){
        Assert.assertThrows(ObjectNullException.class, () -> person.setLastName(name));
    }




}
