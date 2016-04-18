package dao;

/**
 *
 * @author AnaMihalceanu
 */
import model.Department;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAO {

    private Connection connection;

    public DepartmentDAO() {
        connection = DatabaseUtil.getConnection();
    }

    public void addDepartment(Department dept) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into ANA_MIHALCEANU_RO_IBM_COM.department(id, name, manager_id) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, dept.getId());
            preparedStatement.setString(2, dept.getName());
            preparedStatement.setInt(3, dept.getManagerId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ANA_MIHALCEANU_RO_IBM_COM.department where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(Department dept) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update ANA_MIHALCEANU_RO_IBM_COM.department set name = ?, manager_id = ?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, dept.getName());
            preparedStatement.setInt(2, dept.getManagerId());
            preparedStatement.setInt(3, dept.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> depts = new ArrayList<Department>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ANA_MIHALCEANU_RO_IBM_COM.department");
            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("id"));
                dept.setName(rs.getString("name"));
                dept.setManagerId(rs.getInt("manager_id"));
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depts;
    }

    public Department getDepartmentById(int deptId) {
        Department dept = new Department();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from department where id=?");
            preparedStatement.setInt(1, deptId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                dept.setId(rs.getInt("id"));
                dept.setName(rs.getString("name"));
                dept.setManagerId(rs.getInt("manager_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dept;
    }
}
