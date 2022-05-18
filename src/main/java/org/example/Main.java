package org.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/garden";
        String username = "root";
        String password = "dnoliver123";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            //statement.execute("INSERT INTO gardentable (location, carrot, potato, cabbage) VALUES ('birds', 12, 4, 3);");
            //statement.executeUpdate("UPDATE gardentable SET carrot=15 WHERE location='rabbits'");
            ResultSet selectAll = statement.executeQuery("SELECT * FROM gardentable");

            while (selectAll.next()) {
                String location = selectAll.getString("location");
                System.out.println(location);
            }

//            statement.addBatch("INSERT INTO gardentable (location, carrot, potato, cabbage) VALUES ('house', 42, 223, 27)");
//            statement.addBatch("INSERT INTO gardentable (location, carrot, potato, cabbage) VALUES ('garden bed 1', 12, 45, 8)");
//            statement.addBatch("INSERT INTO gardentable (location, carrot, potato, cabbage) VALUES ('garden bed 2', 10, 37, 6)");
//            statement.executeBatch();
//            statement.clearBatch();


            if (!connection.isClosed()) {
                System.out.println("Connection success!");
            }

            connection.close();

            if (connection.isClosed()) {
                System.out.println("Connection close!");
            }
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
       }
    }
}