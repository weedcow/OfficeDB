/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.DepartmentDAO;
import model.Department;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author AnaMihalceanu
 */
public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSERT_OR_EDIT = "/department.jsp";
	private static final String LIST_DEPT = "/listDepartment.jsp";
	private final DepartmentDAO dao;

	public DepartmentController() {
		super();
		dao = new DepartmentDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int deptId = Integer.parseInt(request.getParameter("deptId"));
			dao.deleteDepartment(deptId);
			forward = LIST_DEPT;
			request.setAttribute("depts", dao.getAllDepartments());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int deptId = Integer.parseInt(request.getParameter("deptId"));
			Department dept = dao.getDepartmentById(deptId);
			request.setAttribute("department", dept);
		} else if (action.equalsIgnoreCase("listDepartment")) {
			forward = LIST_DEPT;
			request.setAttribute("depts", dao.getAllDepartments());
		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Department dept = new Department();
		dept.setName(request.getParameter("name"));
		dept.setManagerId(Integer.parseInt(request.getParameter("managerId")));
		String deptId = request.getParameter("deptId");
		dept.setId(Integer.parseInt(deptId));
		if (request.getParameter("action").toString().equals("insert")) {
			dao.addDepartment(dept);
		} else {
			dao.updateDepartment(dept);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_DEPT);
		request.setAttribute("depts", dao.getAllDepartments());
		view.forward(request, response);
	}
}
