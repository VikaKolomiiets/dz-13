package womantests;

import datesourse.DateForDataProvider;
import exceptions.DeadPersonException;
import exceptions.ObjectNullException;
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
public class WomanGiveBabyBirthTests {
    private Woman woman;
    private final int MIN_YEARS_BETWEEN_WOMAN_BABY = 12;

    @BeforeMethod
    public void setUpWoman(){
        woman = new Woman("Ilia", "Noir", LocalDate.of(2002, 01, 01));
        woman.setStatus(Status.SINGLE);
    }
    @AfterMethod
    public void tearDown(){
        this.woman = null;
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testWomanGiveBabyBirthNotMarriedPositive(Person child){
        this.woman.giveBabyBirth(child);
        Assert.assertTrue(this.woman.getChildren().size() != 0);
        String expectedBabyLastName = this.woman.getLastName();
        String actualBabyLastName = this.woman.getChildren().get(0).getLastName();
        Assert.assertEquals(actualBabyLastName, expectedBabyLastName, "Baby Last Name did not change on mother's.");
    }
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-adopt-getbirth-child")
    public void testWomanGiveBabyBirthIsMarriedPossitive(Person child){
        this.woman.createFamily(
                new Man ("Ioan", "Great", LocalDate.of(1998, 10, 10))
                , false, false);
        this.woman.giveBabyBirth(child);
        String expectedBabyLastName = this.woman.getPartner().getLastName();
        String actualBabyLastName = this.woman.getChildren().get(0).getLastName();
        Assert.assertTrue(this.woman.getChildren().size() != 0);
        Assert.assertEquals(actualBabyLastName, expectedBabyLastName, "Baby Last Name did not change on father's.");
    }
    @Test
    public void testWomanGiveBabyBirthParentAgeException(){
        Assert.assertThrows(ParentAgeException.class, () -> this.woman.giveBabyBirth(
                new Woman("FirstName", "LastName", LocalDate.of(2012, 1,1))));
    }
    @Test
    public void testWomanGiveBabyBirthObjectNullException(){
        Assert.assertThrows(ObjectNullException.class, () -> this.woman.giveBabyBirth(null));
    }
    @Test
    public void testWomanGiveBirthBabyDeadPersonException(){
        this.woman.setDateOfDeath(LocalDate.of(2022, 10, 6));
        Assert.assertThrows(DeadPersonException.class, () -> this.woman.giveBabyBirth(
                new Man("FirstName", "LastName", LocalDate.of(2022, 10, 5))));
    }
}
