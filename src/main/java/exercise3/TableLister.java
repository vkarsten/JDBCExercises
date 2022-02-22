package exercise3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableLister {
    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/exampledb?user=ExampleUser&password="
            );

            DatabaseMetaData metaData = myConn.getMetaData();
            String[] table = {"TABLE"};
            ResultSet result = metaData.getTables(null, null, null, table);
            List<String> tableNames = new ArrayList<>();

            while (result.next()) {
                tableNames.add(result.getString("Table_NAME"));
            }

            System.out.println(tableNames);
            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
