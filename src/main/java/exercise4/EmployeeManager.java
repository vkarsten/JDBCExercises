package exercise4;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    public static void main(String[] args) {
        // Getting a connection to the database and creating the employee table if it doesn't exist
        DatabaseConnection db = new DatabaseConnection();

        // Checking if the table was created
        System.out.println(db.checkTable());

        // Adding six records of employees
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee("Adam", "Falon", "adam.falon@dci.com", null));
        newEmployees.add(new Employee("Mary", "Gold", "mary.gold@dci.com", null));
        newEmployees.add(new Employee("Adam", "Currie", "adam.currie@dci.com", null));
        newEmployees.add(new Employee("Mary", "Jhonson", "mary.jhonson@dci.com", null));
        newEmployees.add(new Employee("Prasad", "Ritesh", "prasad.ritesh@dci.com", null));
        newEmployees.add(new Employee("Bryan", "Jhonson", "bryan.Jhonson@dci.com", null));
        db.addEmployees(newEmployees);

        // Making sure that the database contains six records
        System.out.println(db.countEmployees());
    }
}
