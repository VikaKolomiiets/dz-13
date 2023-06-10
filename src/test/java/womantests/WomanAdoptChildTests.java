package womantests;

import datesourse.DateForDataProvider;
import exceptions.DeadPersonException;
import exceptions.MerriedStatusException;
import exceptions.ParentAgeException;
import listeners.PersonTestListener;
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
public class WomanAdoptChildTests {
    private Woman woman;
    @BeforeMethod
    public void setUpMan(){
        this.woman = new Woman("Aelita", "White", LocalDate.of(2000, 1, 7));
        this.woman.setPartner(new Man("Gans", "Anderson", LocalDate.of(1998, 12, 24)));
        this.woman.setStatus(Status.IS_MARRIED);
    }
    @AfterMethod
    public void tearDown(){
        this.woman = null;
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testWomanAdoptChildPositive(Person person){
        String beforeLastNameChild = person.getLastName();
        String expectedLastName = this.woman.getPartner().getLastName();
        this.woman.adoptChild(person);
        String actualLastName = this.woman.getChildren().get(0).getLastName();

        Assert.assertTrue(this.woman.getChildren().size() != 0, "Woman has not adopted");
        Assert.assertEquals(actualLastName, expectedLastName, "LastName was not changed oo last Mane of Man");
        Assert.assertNotEquals(actualLastName, beforeLastNameChild, "LastName was not changed");
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testWomanAdoptChildCheckIsAliveException(Person child){
        child.setDateOfDeath(LocalDate.of(2023, 01,01));
        Assert.assertThrows(DeadPersonException.class, () -> this.woman.adoptChild(child));
    }

    @Test
    public void testWomanAdoptChildCheckMerriedStatusException(){
        this.woman.setStatus(Status.SINGLE);
        Assert.assertThrows(MerriedStatusException.class
                , () -> this.woman.adoptChild(new Man("Lo", "Lee", LocalDate.of(2020, 2, 2))));
    }
    @Test
    public void testWomanAdoptChildCheckAgeException(){
        Woman newMother = new Woman("Aelita", "White", LocalDate.of(2016, 1, 7));
        newMother.setPartner(new Man("Fred", "Gong", LocalDate.of(2015, 12, 24)));
        newMother.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(ParentAgeException.class
                , () -> newMother.adoptChild(new Woman("Li", "Leen", LocalDate.of(2019, 1, 12))));
    }
}
