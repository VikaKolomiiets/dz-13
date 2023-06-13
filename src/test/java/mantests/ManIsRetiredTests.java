package mantests;

import com.beust.ah.A;
import datesourse.DateForDataProvider;
import io.qameta.allure.Description;
import utils.database.DataBaseReader;
import utils.listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;

import java.time.LocalDate;
import java.util.List;

@Listeners(PersonTestListener.class)
public class ManIsRetiredTests {
    private static final int RETIRED_AGE = 65;

    @Description("Positive test")
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "person-date-is-retired")
    public void testManIsRetiredPositive(String firstName, String lastName, LocalDate dateOfBirth) {
        Man man = new Man(firstName, lastName, dateOfBirth);
        Integer fullAge = man.getFullAge();
        if (fullAge >= RETIRED_AGE) {
            Assert.assertTrue(man.isRetired(), "Man is not retired, check " + RETIRED_AGE);
        } else {
            Assert.assertFalse(man.isRetired(), "Man is retired, check " + RETIRED_AGE);
        }
        System.out.println(dateOfBirth + " : " + fullAge + " : " + man.isRetired());
    }

    @Description("From SQL DataBase")
    @Test
    public void testManIsRetiredFromDataBasePositive() {
        List<Man> persons = DataBaseReader.getManFromDataBase();
        int retiredNumber = 0;
        for(Man person: persons){
            Integer fullAge = person.getFullAge();
            System.out.println(fullAge);
            if (fullAge >= RETIRED_AGE) {
                retiredNumber++;
            }
        }
        Assert.assertEquals(retiredNumber, 0, "Retired number should be zero.");
    }
}
