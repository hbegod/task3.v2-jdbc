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


        public static void getAllPlaces() throws SQLException {
                String sql = "SELECT * FROM garden.maingarden";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        ResultSet selectAll = statement.executeQuery(sql);
                        System.out.println("------------AllPlaces:-----------");


                        while (selectAll.next()) {
                                String location = selectAll.getString("location");
                                int carrot = selectAll.getInt("carrot");
                                int potato = selectAll.getInt("potato");
                                int cabbage = selectAll.getInt("cabbage");

                                GardenPlace chosenPlace = new GardenPlace();
                                chosenPlace.setLocation(location);
                                chosenPlace.setCarrot(carrot);
                                chosenPlace.setPotato(potato);
                                chosenPlace.setCabbage(cabbage);

                                System.out.println(chosenPlace.getLocation());
                                System.out.println(chosenPlace.getCarrot());
                                System.out.println(chosenPlace.getPotato());
                                System.out.println(chosenPlace.getCabbage());
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                }

        }

        public static void getPlaceByID(int id) {
                String sql = "SELECT * FROM garden.maingarden WHERE id=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, id);
                        ResultSet chosenLocation = statement.executeQuery();

                        while (chosenLocation.next()) {
                                String location = chosenLocation.getString("location");
                                int carrot = chosenLocation.getInt("carrot");
                                int potato = chosenLocation.getInt("potato");
                                int cabbage = chosenLocation.getInt("cabbage");

                                GardenPlace chosenPlace = new GardenPlace();
                                chosenPlace.setLocation(location);
                                chosenPlace.setCarrot(carrot);
                                chosenPlace.setPotato(potato);
                                chosenPlace.setCabbage(cabbage);

                                System.out.println("-------PlaceByID:-----------");
                                System.out.println(chosenPlace.getLocation());
                                System.out.println(chosenPlace.getCarrot());
                                System.out.println(chosenPlace.getPotato());
                                System.out.println(chosenPlace.getCabbage());
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }


        public static void addNewPlace(GardenPlace place) throws SQLException {
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

        public static void updatePlace(int id, int carrots, int potatoes, int cabbage) throws SQLException {
                String sql = "UPDATE garden.maingarden SET carrot=?, potato=?, cabbage=? WHERE id=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(4, id);
                        statement.setInt(1, carrots);
                        statement.setInt(2, potatoes);
                        statement.setInt(3, cabbage);
                        statement.executeUpdate();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public static void deletePlace(int id) throws SQLException {
                String sql = "DELETE FROM garden.maingarden WHERE id=?";

                try (final PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, id);
                        statement.executeUpdate();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }


        public DBinteractions() throws SQLException {
                System.out.println("DBinteractions - something wrong with that");
        }
}
