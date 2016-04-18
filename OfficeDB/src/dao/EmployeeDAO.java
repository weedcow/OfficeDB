package dao;

/**
 *
 * @author AnaMihalceanu
 */
import model.Employee;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
        connection = DatabaseUtil.getConnection();
    }

    public void addEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into ANA_MIHALCEANU_RO_IBM_COM.employee(id, first_name,last_name, hiring_date, dept_id, sex, phone, position_id) values (?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, new java.sql.Date(employee.getHiringDate().getTime()));
            preparedStatement.setInt(5, employee.getDeptId());
            preparedStatement.setString(6, employee.getSex());
            preparedStatement.setString(7, employee.getPhone());
            preparedStatement.setInt(8, employee.getPositionId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ANA_MIHALCEANU_RO_IBM_COM.employee where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update ANA_MIHALCEANU_RO_IBM_COM.employee set first_name = ?,last_name = ?, hiring_date = ?, dept_id  = ?, sex = ?, phone = ?, position_id = ?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(employee.getHiringDate().getTime()));
            preparedStatement.setInt(4, employee.getDeptId());
            preparedStatement.setString(5, employee.getSex());
            preparedStatement.setString(6, employee.getPhone());
            preparedStatement.setInt(7, employee.getPositionId());
            preparedStatement.setInt(8, employee.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ANA_MIHALCEANU_RO_IBM_COM.employee");
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setHiringDate(rs.getDate("hiring_date"));
                employee.setPhone(rs.getString("phone"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setSex(rs.getString("sex"));
                employee.setDeptId(rs.getInt("dept_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(employees.size());
        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from ANA_MIHALCEANU_RO_IBM_COM.employee where id=?");
            preparedStatement.setInt(1, employeeId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setHiringDate(rs.getDate("hiring_date"));
                employee.setPhone(rs.getString("phone"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setSex(rs.getString("sex"));
                employee.setDeptId(rs.getInt("dept_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
