package mantests;

import datesourse.DateForDataProvider;
import utils.listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;

import java.time.LocalDate;

@Listeners(PersonTestListener.class)
public class ManIsRetiredTests {
    private static final int RETIRED_AGE = 65;


    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-date-is-retired")
    public void testManIsRetiredPositive(String firstName, String lastName, LocalDate dateOfBirth){
        Man man = new Man(firstName, lastName, dateOfBirth);
        Integer fullAge = man.getFullAge();
        if( fullAge>= RETIRED_AGE ){
            Assert.assertTrue(man.isRetired(), "Man is not retired, check " + RETIRED_AGE);
        } else{
            Assert.assertFalse(man.isRetired(), "Man is retired, check " + RETIRED_AGE);
        }
        System.out.println(dateOfBirth + " : " + fullAge + " : " + man.isRetired());
    }
}
