package womantests;

import datesourse.DateForDataProvider;
import exceptions.NameException;
import exceptions.ObjectNullException;
import listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;
import personalization.Woman;

import java.time.LocalDate;

@Listeners(PersonTestListener.class)
public class WomanConstructorTests {

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data")
    public void testWomanConstructorPositive(String firstName, String lastName, LocalDate dateOfBirth){
        Woman woman = new Woman(firstName, lastName, dateOfBirth);
        Assert.assertEquals(woman.getFirstName(), firstName, "Constructor Man doesn't set first Name");
        Assert.assertEquals(woman.getLastName(), lastName, "Constructor Man doesn't set last Name");
        Assert.assertEquals(woman.getDateOfBirth(), dateOfBirth);
    }
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-name-exception")
    public void testWomanConstructorException(String firstName, String lastName, LocalDate dateOfBirth ) {
        Assert.assertThrows( NameException.class,() -> new Woman(firstName, lastName, dateOfBirth));
    }
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-data-null-mane-exception")
    public void testWomanConstructorNullNameException(String firstName, String lastName, LocalDate dateOfBirth){
        Assert.assertThrows(ObjectNullException.class,() -> new Woman(firstName, lastName, dateOfBirth));
    }
}
