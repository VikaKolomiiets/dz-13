package womantests;

import datesourse.DateForDataProvider;
import exceptions.DeadPersonException;
import exceptions.DoubleActionException;
import exceptions.ObjectNullException;
import io.qameta.allure.Description;
import utils.listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;
import personalization.Status;
import personalization.Woman;

import java.time.LocalDate;
@Listeners(PersonTestListener.class)
public class WomanCreateFamilyTests {
    private Woman woman;
    @BeforeMethod
    public void setUpWoman(){
        this.woman = new Woman("Inna", "Krassovsky", LocalDate.of(1980, 01, 06 ));
    }

    @AfterMethod
    public void tearDown(){
        this.woman = null;
    }

    @Description("Positive test")
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "man-data-create-family")
    public void testWomanCreateFamilyPositive(Man newHusband, boolean isChangeLastName, boolean isChangeLastNameNewHusband){

        this.woman.createFamily(newHusband, isChangeLastName, isChangeLastNameNewHusband);
        Assert.assertNotNull(this.woman.getPartner(), "Family was not created");
        Assert.assertEquals(this.woman.getPartner().getFirstName(), newHusband.getFirstName(), "The FirstName of Husband doesn't correct");
        Assert.assertEquals(this.woman.getPartner().getLastName(), newHusband.getLastName(), "The LastName of Husband doesn't correct");
        Assert.assertEquals(this.woman.getPartner().getDateOfBirth(), newHusband.getDateOfBirth(), "The LastName of Husband doesn't correct");
    }

    @Description("Positive test")
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "man-data-create-family")
    public void testWomanCreateFamilyChangeLastName(Man newHusband, boolean isChangeLastName, boolean isChangeLastNameNewHusband){
        this.woman.createFamily(newHusband, isChangeLastName, isChangeLastNameNewHusband);
        if(isChangeLastName){
            Assert.assertEquals(this.woman.getLastName(), newHusband.getLastName(), "Man didn't change LastName");
        }
        if(isChangeLastNameNewHusband){
            Assert.assertEquals(this.woman.getPartner().getLastName(), this.woman.getLastName(), "Woman didn't cange LastName");
        }
    }

    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyNullObjectException(){
        Assert.assertThrows(ObjectNullException.class, () -> this.woman.createFamily(null, false, true));
    }

    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyDoubleActionException(){
        Assert.assertThrows(DoubleActionException.class, () -> this.woman.createFamily(
                new Man("Hans", "Hang", LocalDate.of(2010, 01, 01))
                , true, true));
    }

    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyCheckIsAliveException(){
        this.woman.setDateOfDeath(LocalDate.of(2020, 10, 10));
        Assert.assertThrows(DeadPersonException.class,() -> this.woman.createFamily(
                new Man("Hans", "Hang", LocalDate.of(2010, 01, 01))
                , false, true));
    }

    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyCheckIsAliveManException(){
        Man newHusband =  new Man("Hans", "Hang", LocalDate.of(2010, 01, 01));
        newHusband.setDateOfDeath(LocalDate.of(2021,01,02));
        Assert.assertThrows(DeadPersonException.class,() -> this.woman.createFamily(
                newHusband, false, true));
    }

    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyCheckMarriedException(){
        this.woman.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(DoubleActionException.class, () -> this.woman.createFamily(
                new Man("Hans", "Hang", LocalDate.of(2010, 01, 01))
                , false, true));
    }
    @Description("Exception test")
    @Test
    public void testWomanCreateFamilyCheckMarriedManException(){
        Man newHusband =  new Man("Hans", "Hang", LocalDate.of(2010, 01, 01));
        newHusband.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(DoubleActionException.class, () -> this.woman.createFamily(
                newHusband, false, true));
    }
}
