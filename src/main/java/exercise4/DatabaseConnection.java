package exercise4;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DatabaseConnection {
    Connection dbCon;

    public DatabaseConnection() {
        this.dbCon = this.getConnection("jdbc:postgresql://localhost/ems", "ExampleUser", "");

        try {
            Statement createStmt = dbCon.createStatement();
            createStmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees(" +
                    "fname varchar(30) NOT NULL, " +
                    "lname varchar(30) NOT NULL, " +
                    "email varchar(50) UNIQUE NOT NULL, " +
                    "contact numeric(15, 0));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(String database, String user, String password) {
        try {
            return DriverManager.getConnection(database, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> checkTable() {
        List<String> columnNames = new ArrayList<>();

        try {
            Statement stmt = dbCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees;");
            ResultSetMetaData rsMeta = rs.getMetaData();
            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                columnNames.add(rsMeta.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnNames;
    }

    public void addEmployees(List<Employee> employees) {
        try {
            PreparedStatement pstmt = dbCon.prepareStatement(
                    "INSERT INTO employees VALUES(?,?,?,?)");
            PreparedStatement pstmtNoContact = dbCon.prepareStatement("INSERT INTO employees (fname, lname, email) " +
                    "VALUES(?,?,?)");

            for (Employee employee : employees) {
                if (employee.getContact() == null) {
                    pstmtNoContact.setString(1, employee.getFirstName());
                    pstmtNoContact.setString(2, employee.getLastName());
                    pstmtNoContact.setString(3, employee.getEmail());
                    pstmtNoContact.addBatch();
                } else {
                    pstmt.setString(1, employee.getFirstName());
                    pstmt.setString(2, employee.getLastName());
                    pstmt.setString(3, employee.getEmail());
                    pstmt.setInt(4, employee.getContact());
                    pstmt.addBatch();
                    }
            }

            pstmtNoContact.executeBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countEmployees() {
        ResultSet rs = this.getAllEmployees();
        List<String> numOfRecords = new ArrayList<>();

        try {
            while (rs.next()) {
                numOfRecords.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numOfRecords.size();
    }

    public ResultSet getAllEmployees() {
        try {
            Statement stmt = dbCon.createStatement();
            return stmt.executeQuery("SELECT * FROM employees;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
