package mantests;

import datesourse.DateForDataProvider;
import exceptions.DeadPersonException;
import exceptions.DoubleActionException;
import exceptions.ObjectNullException;
import listeners.PersonTestListener;
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
public class ManCreateFamilyTests {
    private Man man;

    @BeforeMethod
    public void setUpMan(){
        this.man = new Man("Gans", "Forest", LocalDate.of(1980, 01, 06 ));
    }

    @AfterMethod
    public void tearDown(){
        this.man = null;
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "woman-data-create-family")
    public void testManCreateFamilyPositive(Woman newWife, boolean isChangeLastName, boolean isChangeLastNameNewWife){

        this.man.createFamily(newWife, isChangeLastName, isChangeLastNameNewWife);
        Assert.assertNotNull(this.man.getPartner(), "Family was not created");
        Assert.assertEquals(this.man.getPartner().getFirstName(), newWife.getFirstName(), "The FirstName of newWife doesn't correct");
        Assert.assertEquals(this.man.getPartner().getLastName(), newWife.getLastName(), "The LastName of newWife doesn't correct");
        Assert.assertEquals(this.man.getPartner().getDateOfBirth(), newWife.getDateOfBirth(), "The LastName of newWife doesn't correct");
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "woman-data-create-family")
    public void testManCreateFamilyChangeLastName(Woman newWife, boolean isChangeLastName, boolean isChangeLastNameNewWife){
        this.man.createFamily(newWife, isChangeLastName, isChangeLastNameNewWife);
        if(isChangeLastName){
            Assert.assertEquals(this.man.getLastName(), newWife.getLastName(), "Man didn't change LastName");
        }
        if(isChangeLastNameNewWife){
            Assert.assertEquals(this.man.getPartner().getLastName(), this.man.getLastName(), "Woman didn't cange LastName");
        }
    }
    @Test
    public void testManCreateFamilyNullObjectException(){
        Assert.assertThrows(ObjectNullException.class, () -> this.man.createFamily(null, false, true));
    }

    @Test
    public void testManCreateFamilyDoubleActionException(){
        Assert.assertThrows(DoubleActionException.class, () -> this.man.createFamily(
                new Woman("Hanna", "Hang", LocalDate.of(2010, 01, 01))
                , true, true));
    }
    @Test
    public void testManCreateFamilyCheckIsAliveException(){
        this.man.setDateOfDeath(LocalDate.of(2020, 10, 10));
        Assert.assertThrows(DeadPersonException.class,() -> this.man.createFamily(
                new Woman("Hanna", "Hang", LocalDate.of(2010, 01, 01))
                , false, true));
    }
    @Test
    public void testManCreateFamilyCheckIsAliveWomanException(){
        Woman newWife =  new Woman("Hanna", "Hang", LocalDate.of(2010, 01, 01));
        newWife.setDateOfDeath(LocalDate.of(2021,01,02));
        Assert.assertThrows(DeadPersonException.class,() -> this.man.createFamily(
                newWife, false, true));
    }

    @Test
    public void testManCreateFamilyCheckMarriedException(){
        this.man.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(DoubleActionException.class, () -> this.man.createFamily(
                new Woman("Hanna", "Hang", LocalDate.of(2010, 01, 01))
                , false, true));
    }
    @Test
    public void testManCreateFamilyCheckMarriedWomanException(){
        Woman newWife =  new Woman("Hanna", "Hang", LocalDate.of(2010, 01, 01));
        newWife.setStatus(Status.IS_MARRIED);
        Assert.assertThrows(DoubleActionException.class, () -> this.man.createFamily(
                newWife, false, true));
    }


}
