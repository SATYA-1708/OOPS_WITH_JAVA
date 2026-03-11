package company.service;

import company.employee.Employee;
import company.exception.EmployeeNotFoundException;

import java.io.*;

public class EmployeeService {

    String fileName = "employees.txt";

    // Add employee
    public void addEmployee(Employee e) throws IOException {

        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(e.getEmpId() + "," + e.getName() + "," + e.getDepartment() + "," + e.getSalary());
        bw.newLine();

        bw.close();
    }

    // Display employees
    public void displayEmployees() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;

        while((line = br.readLine()) != null) {
            String data[] = line.split(",");
            System.out.println("ID: " + data[0] +
                    " Name: " + data[1] +
                    " Department: " + data[2] +
                    " Salary: " + data[3]);
        }

        br.close();
    }

    // Search employee
    public void searchEmployee(int empId) throws IOException, EmployeeNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        boolean found = false;

        while((line = br.readLine()) != null) {

            String data[] = line.split(",");

            if(Integer.parseInt(data[0]) == empId) {
                System.out.println("Employee Found:");
                System.out.println("ID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Department: " + data[2]);
                System.out.println("Salary: " + data[3]);
                found = true;
                break;
            }
        }

        br.close();

        if(!found) {
            throw new EmployeeNotFoundException("Employee not found with ID " + empId);
        }
    }
}