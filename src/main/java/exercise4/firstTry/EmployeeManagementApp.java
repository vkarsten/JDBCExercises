package exercise4.firstTry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeManagementApp {
    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/ems?user=ExampleUser&password="
            );

            // Create the table for storing the employees
            Statement createStmt = myConn.createStatement();
            createStmt.executeUpdate("CREATE TABLE employees(" +
                    "fname varchar(30) NOT NULL, " +
                    "lname varchar(30) NOT NULL, " +
                    "email varchar(50) UNIQUE NOT NULL, " +
                    "contact numeric(15, 0));");

            // Check if the table was created correctly
            ResultSet createTable = createStmt.executeQuery("SELECT * FROM employees;");
            ResultSetMetaData createTableMeta = createTable.getMetaData();
            for (int i = 1; i <= createTableMeta.getColumnCount(); i++) {
                System.out.println(createTableMeta.getColumnName(i));
            }

            // Inserting employees into the table
            // Prepare insert statement
            PreparedStatement pstmt = myConn.prepareStatement("INSERT INTO employees (fname, lname, email) VALUES(?,?,?)");
            String[] firstNames = new String[] {"Adam", "Mary", "Adam", "Bryan", "Prasad", "Mary"};
            String[] lastNames = new String[] {"Falon", "Gold", "Currie", "Jhonson", "Ritesh", "Jhonson"};
            String[] emails = new String[] {"adam.falon@dci.com", "mary.gold@dci.com", "adam.currie@dci.com", "bryan.Jhonson@dci.com",
                    "prasad.ritesh@dci.com", "mary.jhonson@dci.com"};

            // create an insert query for every employee and add each of them to the batch
            for (int i = 0; i < firstNames.length; i++) {
                pstmt.setString(1, firstNames[i]);
                pstmt.setString(2, lastNames[i]);
                pstmt.setString(3, emails[i]);
                pstmt.addBatch();
            }
            // execute all inserts
            pstmt.executeBatch();

            // check amount of inserted records
            Statement checkInsert = myConn.createStatement();
            ResultSet inserted = checkInsert.executeQuery("SELECT * FROM employees;");

            List<String> numOfRecords = new ArrayList<>();

            while (inserted.next()) {
                numOfRecords.add(inserted.getString(1));
            }
            System.out.println(numOfRecords.size());

            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
