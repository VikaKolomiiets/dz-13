package utils.database;

import personalization.Man;
import personalization.Person;
import personalization.Woman;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseWriter {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "dz-13";
    private final static String USER_PASSWORD = "dz-13";
    private final static String INSERT_PERSON = "INSERT INTO public.person (firstname, lastname, datebirth, isman) VALUES(?, ?, ?, ?)";
    private final static String UPDATE_PERSON_SEX = "update person set isMan = ? where id = ?";
    private final static String UPDATE_PERSON_DATE_BIRTH = "update person set dateBirth = ? where id = ?";

    private final static String DELETE_PERSON_BY_ID = "delete from person where id = ?";

    public static void deletePersonByIdIntoDataBase(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSON_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
    }

    public static void updatePersonSexIntoDataBase(int id, boolean isMan) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSON_SEX);
            preparedStatement.setInt(2, id);
            preparedStatement.setBoolean(1, isMan);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
    }

    public static void updatePersonDateBirthIntoDataBase(int id, LocalDate dateBirth) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSON_DATE_BIRTH);
            preparedStatement.setInt(2, id);
            preparedStatement.setDate(1, convertToDateViaSqlDate(dateBirth));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
    }

    public static void setPersonIntoDataBase(String firstName, String lastName, LocalDate dateBirth, boolean isMan) {

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

    public static void main(String[] args) {
        //setPersonIntoDataBase("DDDD", "lastName", LocalDate.of(1955,05,05), false);
        //updatePersonSexIntoDataBase(9, true);
        //updatePersonDateBirthIntoDataBase(10, LocalDate.of(1939,12,31));
        deletePersonByIdIntoDataBase(10);
        System.out.println("Hello!");
    }
}


