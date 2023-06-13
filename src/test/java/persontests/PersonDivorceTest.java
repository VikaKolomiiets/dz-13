package persontests;

import datesourse.DateForDataProvider;
import exceptions.MerriedStatusException;
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
public class PersonDivorceTest {
    Woman exWife;
    Man exHusband;

    @BeforeMethod
    public void testSetUpPartnersData() {
        exWife = new Woman("Lili", "Pugach", LocalDate.of(1972, 8, 8));
        exHusband = new Man("Choi", "Fatch", LocalDate.of(1969, 1, 11));
    }

    @AfterMethod
    public void tearDownFamily() {
        exWife = null;
        exHusband = null;
    }

    @Description("Positive test")
    @Test(dataProviderClass = DateForDataProvider.class, dataProvider = "data-true-divorce")
    public void testPersonDivorcePositive(boolean isChanged) {
        exWife.createFamily(exHusband, isChanged, !isChanged);
        exWife.divorce(isChanged, !isChanged);

        Assert.assertEquals(exWife.getStatus().name(), Status.IS_DIVORCED.name(), "Status doesn't changed to IS_DIVORCED for wife");
        Assert.assertEquals(exHusband.getStatus().name(), Status.IS_DIVORCED.name(), "Status doesn't changed to IS_DIVORCED for hudband");
        if (isChanged) {
            Assert.assertEquals(exWife.getLastName(), exWife.getBirthLastName(), "After divorce wasn't changed Last name for exWife");
        } else {
            Assert.assertEquals(exHusband.getLastName(), exHusband.getBirthLastName(), "After divorce wasn't changed Last name for exHusband");
        }
    }

    @Description("Exception test")
    @Test()
    public void testPersonDivorcePersonStatusException() {
        exWife.createFamily(exHusband, true, false);
        exWife.setStatus(Status.WIDOWED);
        Assert.assertThrows(MerriedStatusException.class, () -> exWife.divorce(true, false));
    }

    @Description("Exception test")
    @Test()
    public void testPersonDivorcePartnerStatusException() {
        exWife.createFamily(exHusband, true, false);
        exHusband.setStatus(Status.SINGLE);
        Assert.assertThrows(MerriedStatusException.class, () -> exWife.divorce(true, false));
    }

}
