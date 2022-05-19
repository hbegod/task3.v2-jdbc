package org.example.controller;

import org.example.gardenplace.GardenPlace;

import java.sql.*;

public class DBinteractions {

        public static String DB_URL = "jdbc:mysql://localhost:3306/";
        public static String USER = "root";
        public static String PASS = "dnoliver123";

        static Connection connection;

        static {
                try {
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
        Statement statement = connection.createStatement();


        public void getAllPlaces() throws SQLException {
                ResultSet selectAll = statement.executeQuery("SELECT * FROM garden.maingarden");
                //тут каждое поле нужно выводить через while чтоли? пока неясно. И вообще нужно ли выводить все (по ТЗ там get по id кажется)

        }

        public static void getPlaceByID(int id) {
                String sql = "SELECT * FROM garden.maingarden WHERE id=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, id);
                        ResultSet chosenLocation = statement.executeQuery();
                        System.out.println(chosenLocation);

                        while (chosenLocation.next()) {
                                String locationName = chosenLocation.getString("location");
                                System.out.println(locationName);
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }


        public void addNewPlace(GardenPlace place) throws SQLException {
                String sql = "INSERT INTO garden.maingarden (location, carrot, potato, cabbage) VALUES (?, ?, ?, ?)";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, place.getLocation());
                        statement.setInt(2, place.getCarrot());
                        statement.setInt(3, place.getPotato());
                        statement.setInt(4, place.getCabbage());
                        statement.executeUpdate();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void updatePlace(String location, String vegetable, int vegetableAmount) throws SQLException {
                String sql = "UPDATE garden.maingarden SET ?=? WHERE location=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(3, location);
                        statement.setString(1, vegetable);
                        statement.setInt(2, vegetableAmount);
                        statement.executeUpdate();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void deletePlace(String location) throws SQLException {
                String sql = "DELETE FROM garden.maingarden WHERE location=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, location);
                        statement.executeUpdate();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }


        public DBinteractions() throws SQLException {
                System.out.println("DBinteractions - something wrong with that");
        }
}
