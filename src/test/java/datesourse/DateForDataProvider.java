package datesourse;

import org.testng.annotations.DataProvider;
import personalization.Man;
import personalization.Woman;

import java.time.LocalDate;

    public class DateForDataProvider {
    @DataProvider(name = "person-data-null-mane-exception")
    public static Object[][] setUpPersonDataNullNameException(){
        return new Object[][] {
                {"Nikola", null, LocalDate.of(1952, 01, 28)},
                {null, "Rango", LocalDate.of(2020, 05, 18)}};
    }
    @DataProvider(name = "person-data")
    public static Object[][] setUpPersonData(){
        return new Object[][]{
                {"Nikola", "Bange", LocalDate.of(1952, 01, 28)},
                {"Juriiy", "Woo", LocalDate.of(1972, 11, 01)},
                {"Sergiiy", "Rango", LocalDate.of(2020, 05, 18)}};
    }

    @DataProvider(name = "person-data-name-exception")
    public static Object[][] setUpPersonDataNameException(){
        return new Object[][] {
                {"N", "Bange", LocalDate.of(1952, 01, 28)},
                {"Juriiy", "W", LocalDate.of(1972, 11, 01)},
                {"", "Rango", LocalDate.of(2020, 05, 18)}};
    }

    @DataProvider(name = "woman-data-create-family")
        public static Object[][] setUpForManDataCreateFamily(){
        return new Object[][] {
                {new Woman("Nina", "BLonde", LocalDate.of(1982, 1, 28)), false, true},
                {new Woman("Lara", "Kruello", LocalDate.of(1968, 9, 19)), false, true},
                {new Woman("Mirra", "Loo", LocalDate.of(1990, 10, 10)), true, false},
                {new Woman("Klara", "Rullo", LocalDate.of(1978, 5, 18)), true, false}};
    }
        @DataProvider(name = "man-data-create-family")
        public static Object[][] setUpForWomanDataCreateFamily(){
            return new Object[][]{
                    {new Man("Nik", "Bonde", LocalDate.of(1982, 1, 28)), false, true},
                    {new Man("KarLos", "Ruello", LocalDate.of(1968, 9, 19)), false, true},
                    {new Man("Moor", "Loo", LocalDate.of(1990, 10, 10)), true, false},
                    {new Man("Larry", "Kruello", LocalDate.of(1978, 5, 18)), true, false}};
        }

    @DataProvider(name = "person-data-adopt-getbirth-child")
        public static Object[][] setUpPersonDataAdoptOrBirthChild(){
            return new Object[][] {
                    {new Man("Nikola", "Bunge", LocalDate.of(2018, 01, 28)),},
                    {new Woman("Jully", "Woo", LocalDate.of(2020, 11, 01))},
                    {new Man("Serge", "Bingo", LocalDate.of(2022, 05, 18))}};
    }

    @DataProvider(name = "person-date-is-retired")
        public static Object[][] setUpManDataIsRetired(){
        return new Object[][] {
                {"Lee", "Wong", LocalDate.of(1945, 10, 19)},
                {"Lee", "Wong", LocalDate.of(1962, 10, 19)},
                {"Lee", "Wong", LocalDate.of(1975, 10, 19)},
                {"Lee", "Wong", LocalDate.of(1955, 10, 19)},};
    }
    @DataProvider(name = "person-data-full-age")
        public static Object[][] setUpYearMonthDayDataGetFullAge(){
        return new Object[][] {
                {1911, 10, 18},
                {1939, 9, 30},
                {1941, 9, 3},
                {1945, 9, 20}};
    }
    @DataProvider(name = "person-data-birth-death")
        public static Object[][] setUpYearsMonthesDaysGetFullAge(){
        return new Object[][] {
                    {1911, 10, 18, 2000, 9, 21},
                    {1920, 11, 8, 2020, 1, 18},
                    {1941, 9, 3, 2018, 1, 2}};
    }
    @DataProvider(name = "data-true-divorce")
        public static Object[][] setUpIsAgree(){
        return new Object[][]  {{true}, {false}};
    }

    @DataProvider(name ="years-birth-death")
        public static Object[][] setUpYears(){
        return new Object[][] {
                {1925, 1968},
                {1941, 2018},
                {1999, 2018}};
    }
}
