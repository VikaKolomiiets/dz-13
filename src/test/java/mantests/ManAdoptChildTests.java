package mantests;

import datesourse.DateForDataProvider;
import exceptions.DeadPersonException;
import exceptions.MerriedStatusException;
import exceptions.ParentAgeException;
import utils.listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;
import personalization.Person;
import personalization.Status;
import personalization.Woman;

import java.time.LocalDate;

@Listeners(PersonTestListener.class)
public class ManAdoptChildTests {
    private Man man;
    @BeforeMethod
    public void setUpMan(){
        this.man = new Man("James", "Gunn", LocalDate.of(1995, 1, 7));
        this.man.setPartner(new Woman("Ann", "Gunn", LocalDate.of(1998, 12, 24)));
        this.man.setStatus(Status.IS_MARRIED);
    }
    @AfterMethod
    public void tearDown(){
        this.man = null;
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testManAdoptChildPositive(Person person){
        String beforeLastNameChild = person.getLastName();
        this.man.adoptChild(person);
        String afterLastNameChild = this.man.getChildren().get(0).getLastName();
        Assert.assertTrue(this.man.getChildren().size() != 0);
        Assert.assertEquals(afterLastNameChild, this.man.getLastName(), "LastName was not changed");
        Assert.assertNotEquals(afterLastNameChild, beforeLastNameChild, "LastName was not changed");
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testManAdoptChildCheckIsAliveException(Person child){
        child.setDateOfDeath(LocalDate.of(2023, 01,01));
        Assert.assertThrows(DeadPersonException.class, () -> this.man.adoptChild(child));
    }

    @Test
    public void testManAdoptChildCheckMerriedStatusException(){
        this.man.setStatus(Status.IS_DIVORCED);
        Assert.assertThrows(MerriedStatusException.class
                , () -> this.man.adoptChild(new Man("Lo", "Lee", LocalDate.of(2020, 2, 2))));
    }
    @Test
    public void testManAdoptChildCheckAgeException(){
        Man newFather = new Man("James", "Gunn", LocalDate.of(2000, 1, 7));
        newFather.setPartner(new Woman("Ann", "Gunn", LocalDate.of(2014, 12, 24)));
        newFather.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(ParentAgeException.class
                , () -> newFather.adoptChild(new Woman("Li", "Leen", LocalDate.of(2022, 1, 12))));
    }
}
