package mantests;

import datesourse.DateForDataProvider;
import exceptions.NameException;
import exceptions.ObjectNullException;
import listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;

import java.time.LocalDate;

@Listeners(PersonTestListener.class)
public class ManConstructorTests {

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data")
    public void testManConstructorPositive(String firstName, String lastName, LocalDate dateOfBirth ) {
        Man man = new Man(firstName, lastName, dateOfBirth);
        Assert.assertEquals(man.getFirstName(), firstName, "Constructor Man doesn't set first Name");
        Assert.assertEquals(man.getLastName(), lastName, "Constructor Man doesn't set last Name");
        Assert.assertEquals(man.getDateOfBirth(), dateOfBirth);
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-name-exception")
    public void testManConstructorException(String firstName, String lastName, LocalDate dateOfBirth ) {
        Assert.assertThrows( NameException.class,() -> new Man(firstName, lastName, dateOfBirth));
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-null-mane-exception")
    public void testManConstructorNullNameException(String firstName, String lastName, LocalDate dateOfBirth){
        Assert.assertThrows(ObjectNullException.class,() -> new Man(firstName, lastName, dateOfBirth));
    }

}
