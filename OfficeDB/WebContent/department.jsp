<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <link href="./css/style.css" 
              rel="stylesheet" type="text/css"/> 
        <title>Add new department</title>
    </head>
    <body>
        <h1 id = "message" style="margin-left: auto; margin-right: auto; width:320px; padding-left: 70px;">Add new department</h1>
        <form method="POST" action='DepartmentController' name="frmAddDepartment">
            <input type="hidden" name="action" value="<c:out value="${param.action}" />" />
            <table class="addform">
                <tr><td>User ID :</td><td> <input type="text" readonly="readonly" name="deptId"
                                                  value="<c:out value="${param.deptId}" />" /> <br /> </td></tr>
                <tr><td>Department Name : </td><td><input
                            type="text" name="name"
                            value="<c:out value="${department.name}" />" /> <br /> </td></tr>
                <tr><td>Manager Id : </td><td><input type="text" name="managerId"
                                                     value="<c:out value="${department.managerId}" />" /> <br /></td></tr>
            </table>
            <p style="margin-left: auto; margin-right: auto; width:200px;">
                <input
                    type="submit" value="Submit" />
            </p>
        </form>
        <p style="margin-left: auto; margin-right: auto; width:200px;">
            <br />
            <a href="DepartmentController?action=listDepartment" >Show All Department Items</a>
            <br />
            <a href="index.jsp" >Index</a>
        </p>
    </body>
</html>