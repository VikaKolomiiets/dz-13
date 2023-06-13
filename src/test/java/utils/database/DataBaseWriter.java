package utils.database;

import personalization.Man;
import personalization.Person;
import personalization.Woman;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseWriter {
    private final static String URL ="jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "dz-13";
    private final static String USER_PASSWORD = "dz-13";
    private final static String INSERT_PERSON = "INSERT INTO public.person (firstname, lastname, datebirth, isman) VALUES(?, ?, ?, ?)";


    public static void setPersonIntoDataBase(String firstName, String lastName, LocalDate dateBirth, boolean isMan){

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, convertToDateViaSqlDate(dateBirth));
            preparedStatement.setBoolean(4, isMan);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
    }
    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static void main(String[] args){
        setPersonIntoDataBase("First", "Last", LocalDate.of(1921, 01, 01), false);
        System.out.println("Hello!");
    }
}


