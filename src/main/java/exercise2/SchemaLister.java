package exercise2;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SchemaLister {
    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/exampledb?user=exampleUser&password="
            );
            Statement stmt = myConn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT schema_name FROM information_schema.schemata");

            List<String> schemaNames = new ArrayList<>();

            while (result.next()) {
                schemaNames.add(result.getString("schema_name"));
            }

            System.out.println(schemaNames);
            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
