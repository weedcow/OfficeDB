<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Show All Employees</title>
        <link href="./css/style.css" 
              rel="stylesheet" type="text/css"/> 
    </head>
    <body>
        <h1 id = "message" style="margin-left: auto; margin-right: auto; width:800px;">Show All Employees</h1>
        <fmt:setBundle basename="lang" var="lang"/>
        <fmt:setLocale value="en"/>
        <table border=1 class="list">
            <thead>
                <tr>
                    <th><fmt:message key="list.empId"  bundle="${lang}"/></th>
                    <th><fmt:message key="list.firstName"  bundle="${lang}"/></th>
                    <th><fmt:message key="list.lastName" bundle="${lang}" /></th>
                    <th><fmt:message key="list.hiringDate" bundle="${lang}" /></th>
                    <th><fmt:message key="list.phone"  bundle="${lang}"/></th>
                    <th><fmt:message key="list.sex"  bundle="${lang}"/></th>
                    <th><fmt:message key="list.deptId"  bundle="${lang}"/></th>
                    <th><fmt:message key="list.pos"  bundle="${lang}"/></th>
                    <th colspan=2><fmt:message key="list.action"  bundle="${lang}"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td><c:out value="${employee.id}" /></td>
                        <td><c:out value="${employee.firstName}" /></td>
                        <td><c:out value="${employee.lastName}" /></td>
                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${employee.hiringDate}" /></td>
                        <td><c:out value="${employee.phone}" /></td>
                        <td><c:out value="${employee.sex}" /></td>
                        <td><c:out value="${employee.deptId}" /></td>
                        <td><c:out value="${employee.positionId}" /></td>
                        <td><a href="EmployeeController?action=edit&empId=<c:out value="${employee.id}"/>">Update</a></td>
                        <td><a href="EmployeeController?action=delete&empId=<c:out value="${employee.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p style="margin-left: auto; margin-right: auto; width:800px;"><a href="EmployeeController?action=insert&empId=<c:out value="${fn:length(employees)*2}"/>">Add Employee</a>
            <br />
            <a href="index.jsp" >Index</a>
        </p>
    </body>
</html>