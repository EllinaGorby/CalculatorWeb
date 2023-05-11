<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : calculatur, Servlet learning task
    Created on : 21.04.2023, 09:44:01
    Author     : Ellina
--%>
<jsp:useBean id="calculatorController" class="com.mycompany.web.controller.CalculatorController" scope="request"/>
<%
    calculatorController.calculator(request);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Calculator</title>
    </head>
    <body>
        <link rel="stylesheet" href="css/style.css" >
            <h1>Rechner</h1>
            <form method='post'>

            <%-- 
                calculator view 
            --%>
            
            <div class='grid-calcview-container'>
                <input type='text' name='calcview' value='${calculatorController.calcview}' readonly ><br>
            </div>

            <div class='grid-buttons-container'>
            <input type='submit' name='number' value='7' >
            <input type='submit' name='number' value='8' >
            <input type='submit' name='number' value='9' >
            <input type='submit' name='operation' value='-' >
            <input type='submit' name='number' value='4' >
            <input type='submit' name='number' value='5' >
            <input type='submit' name='number' value='6' >
            <input type='submit' name='operation' value='/' >
            <input type='submit' name='number' value='1' >
            <input type='submit' name='number' value='2' >
            <input type='submit' name='number' value='3' >
            <input type='submit' name='operation' value='*' >
            <input type='submit' name='number' value='0' >
            <input type='submit' name='number' value='.' >
            <input type='submit' name='operation' value='=' >
            <input type='submit' name='operation' value='+' >
            </div>

            <div class='grid-clear-container'>
            <input type='submit' name='clear' value='C' >
            </div>
            </form>


            </body>
            </html>
        
    </body>
</html>
