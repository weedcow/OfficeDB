<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Show All Departments</title>
        <link href="./css/style.css" 
              rel="stylesheet" type="text/css"/> 
    </head>
    <body>
        <h1 id = "message" style="margin-left: auto; margin-right: auto; width:800px;" >Show All Departments</h1>
        <table border=1 class="list">
            <thead>
                <tr>
                    <th>Department Id</th>
                    <th>Name</th>
                    <th>Manager Id</th>
                    <th colspan=2>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${depts}" var="department">
                    <tr>
                        <td><c:out value="${department.id}" /></td>
                        <td><c:out value="${department.name}" /></td>
                        <td><c:out value="${department.managerId}" /></td>
                        <td><a href="DepartmentController?action=edit&deptId=<c:out value="${department.id}"/>">Update</a></td>
                        <td><a href="DepartmentController?action=delete&deptId=<c:out value="${department.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p style="margin-left: auto; margin-right: auto; width:800px;"><a href="DepartmentController?action=insert&deptId=<c:out value="${fn:length(depts)*2}"/>">Add Department</a>
            <br />
            <a href="index.jsp" >Index</a>
        </p>
    </body>
</html>