package org.example;

import org.example.controller.DBinteractions;
import org.example.gardenplace.GardenPlace;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class CreateDB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "dnoliver123";

    public static void createGardenTable(Statement statement) throws SQLException {
        String sql = "CREATE TABLE `garden`.`maingarden` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `location` VARCHAR(45) NOT NULL,\n" +
                "  `carrot` INT NULL,\n" +
                "  `potato` INT NULL,\n" +
                "  `cabbage` INT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";
        statement.executeUpdate(sql);
        System.out.println("Database created successfully...");

        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('house', 42, 223, 27)");
        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('garden bed 1', 12, 45, 8)");
        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('garden bed 2', 10, 37, 6)");
        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('garden bed 3', 14, 56, 11)");
        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('rabbits', 10, 5, 4)");
        statement.execute("INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES ('rats', 7, 26, 5)");

    }

    static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});

        return resultSet.next();
    }

    public static void main(String[] args) {
        // Open a connection
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
        ) {

            if (!tableExists(connection, "maingarden")) {
                createGardenTable(statement);
            }

            //?????????????? ?????? ????????????
            testingMethod();



            if (!connection.isClosed()) {
                System.out.println("Connection success!");
            }
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Connection close!");
            }

       } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testingMethod() throws SQLException {
        GardenPlace newPlace = new GardenPlace();
        newPlace.setLocation("birds");
        newPlace.setCarrot(11);
        newPlace.setPotato(21);
        newPlace.setCabbage(8);

        DBinteractions.getAllPlaces();
        DBinteractions.getPlaceByID(5);
        DBinteractions.updatePlace(1, 55, 299, 33);
        //DBinteractions.addNewPlace(newPlace);//?????? ????????????????, ???????????????????????? ?????????? ???? ?????????????????? ???????????? birds
        //DBinteractions.deletePlace(7);
    }

}
