package womantests;

import datesourse.DateForDataProvider;
import io.qameta.allure.Description;
import listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Woman;

import java.time.LocalDate;
@Listeners(PersonTestListener.class)
public class WomanIsRetiredTests {
    private static final int RETIRED_AGE = 60;


    @Description("Positive test")
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-date-is-retired")
    public void testWomanisRetiredPositive(String firstName, String lastName, LocalDate dateOfBirth){
        Woman woman = new Woman(firstName, lastName, dateOfBirth);
        Integer fullAge = woman.getFullAge();
        if( fullAge>= RETIRED_AGE ){
            Assert.assertTrue(woman.isRetired(), "Woman is not retired, check " + RETIRED_AGE);
        } else{
            Assert.assertFalse(woman.isRetired(), "Woman is retired, check " + RETIRED_AGE);
        }
        System.out.println(dateOfBirth + " : " + fullAge + " : " + woman.isRetired());
    }
}
