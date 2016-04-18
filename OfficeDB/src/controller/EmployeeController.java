package controller;

/**
 *
 * @author AnaMihalceanu
 */
import dao.EmployeeDAO;
import model.Employee;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/employee.jsp";
    private static final String LIST_USER = "/listEmployee.jsp";
    private final EmployeeDAO dao;

    public EmployeeController() {
        super();
        dao = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int empId = Integer.parseInt(request.getParameter("empId"));
            dao.deleteEmployee(empId);
            forward = LIST_USER;
            request.setAttribute("employees", dao.getAllEmployees());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int empId = Integer.parseInt(request.getParameter("empId"));
            Employee employee = dao.getEmployeeById(empId);
            request.setAttribute("employee", employee);
        } else if (action.equalsIgnoreCase("listEmployee")){
            forward = LIST_USER;
            request.setAttribute("employees", dao.getAllEmployees());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        try {
            Date hd = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("hiringDate"));
            employee.setHiringDate(hd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setPhone(request.getParameter("phone"));
        employee.setSex(request.getParameter("sex"));
        employee.setDeptId(Integer.parseInt(request.getParameter("deptId")));
        employee.setPositionId(Integer.parseInt(request.getParameter("positionId")));
        String empId = request.getParameter("empId");
        employee.setId(Integer.parseInt(empId));
        if(request.getParameter("action").toString().equals("insert"))
        {
            dao.addEmployee(employee);
        }
        else
        {
            dao.updateEmployee(employee);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("employees", dao.getAllEmployees());
        view.forward(request, response);
    }
}
