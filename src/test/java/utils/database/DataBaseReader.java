package utils.database;

import personalization.Man;
import personalization.Person;
import personalization.Woman;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {
    private final static String URL ="jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "dz-13";
    private final static String USER_PASSWORD = "dz-13";

    private final static String QUERY_SELECT = "select * from person";
    private final static String QUERY_SELECT_MAN = "select * from person where isman is true";
    private final static String QUERY_SELECT_WOMAN = "select * from person where isman is false";

    public static List<Person> getPersonsFromDataBase() {
        List<Person> persons = new ArrayList<Person>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet sqlResultSet = sqlStatement.executeQuery(QUERY_SELECT);

            while (sqlResultSet.next()){
                if(sqlResultSet.getBoolean("isman")){
                    //Date date = sqlResultSet.getDate("datebirth");
                    //LocalDate localDate = convertToLocalDate(sqlResultSet.getDate("datebirth"));
                    Person person = new Man(
                            sqlResultSet.getString("firstName"),
                            sqlResultSet.getString("lastname"),
                            convertToLocalDate(sqlResultSet.getDate("datebirth")));
                    persons.add(person);

                } else {
                    Person person = new Woman(
                            sqlResultSet.getString("firstName"),
                            sqlResultSet.getString("lastname"),
                            convertToLocalDate(sqlResultSet.getDate("datebirth")));
                    persons.add(person);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
        return persons;

    }

    public static List<Man> getManFromDataBase() {
        List<Man> persons = new ArrayList<Man>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet sqlResultSet = sqlStatement.executeQuery(QUERY_SELECT_MAN);

            while (sqlResultSet.next()) {
                Man man = new Man(
                        sqlResultSet.getString("firstName"),
                        sqlResultSet.getString("lastname"),
                        convertToLocalDate(sqlResultSet.getDate("datebirth")));
                persons.add(man);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
        return persons;
    }

    public static List<Woman> getWomanFromDataBase() {
        List<Woman> persons = new ArrayList<Woman>();
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            ResultSet sqlResultSet = sqlStatement.executeQuery(QUERY_SELECT_WOMAN);

            while (sqlResultSet.next()) {
                Woman woman = new Woman(
                        sqlResultSet.getString("firstName"),
                        sqlResultSet.getString("lastname"),
                        convertToLocalDate(sqlResultSet.getDate("datebirth")));
                persons.add(woman);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Please, check URL, USER_NAME end USER_PASSWORD, if they are correct.");
        }
        return persons;
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


}
