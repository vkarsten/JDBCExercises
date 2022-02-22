package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseLister {
    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/exampledb?user=ExampleUser&password="
            );
            Statement stmt = myConn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT datname FROM pg_database");
            List<String> datNames = new ArrayList<>();

            while (result.next()) {
                datNames.add(result.getString("datname"));
            }

            System.out.println(datNames);
            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
