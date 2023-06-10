package persontests;

import datesourse.DateForDataProvider;
import listeners.PersonTestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import personalization.Man;
import personalization.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Listeners(PersonTestListener.class)
public class PersonGetFullAge {

    Person person;


    @Test(dataProviderClass = DateForDataProvider.class, dataProvider ="person-data-full-age")
    public void testPersonGetFullAgePositive(int year, int month, int day){
        int fullAges = (int) ChronoUnit.YEARS.between(LocalDate.of(year, month, day), LocalDate.now());
        Person person = new Man("Jon", "White", LocalDate.of(year, month, day));
        Assert.assertEquals(person.getFullAge(), fullAges, "Method getFullAge doesn't work");
        System.out.println("Full age is " + person.getFullAge() + ", if data of birth " + person.getDateOfBirth());
    }

    @Test(dataProviderClass = DateForDataProvider.class, dataProvider ="person-data-birth-death")
    public void testPersonGetFullAgeIfPersonDied(int year, int month, int day, int yearDeath, int monthDeath, int dayDeath){
        Person person = new Man("Jon", "White", LocalDate.of(year, month, day));
        person.setDateOfDeath(LocalDate.of(yearDeath, monthDeath, dayDeath));
        int expectedAges = (int)ChronoUnit.YEARS.between(LocalDate.of(year, month, day), LocalDate.of(yearDeath, monthDeath, dayDeath));
        Assert.assertEquals(person.getFullAge(), expectedAges, "Method getFullAges doesn't work");
        System.out.println("Full ages was " + person.getFullAge()
                + " life-dates: " + person.getDateOfBirth().getYear() + ":" + person.getDateOfDeath().getYear());
    }

}

