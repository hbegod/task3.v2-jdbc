package org.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //это urlfixed, но можно использовать и обычный: "jdbc:mysql://localhost:3306/garden" (не уверен, нужно ли явно у4казывать 3306 тут)
        String url = "jdbc:mysql://localhost:3306/garden?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true\" +\n" +
                "                    \"&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "dnoliver123";

        Connection connection;
        try{
            //Driver driver = new com.mysql.cj.jdbc.Driver();
            //DriverManager.registerDriver(driver);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            if (!connection.isClosed()) {
                System.out.println("Connection success!");
            }
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");

            System.out.println(ex);





            //авто кетчи при фиксе ошибок компиляции, пока пусть висят, потом пофиксить
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}