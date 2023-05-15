package com.mycompany.web.controller;

import javax.el.ELProcessor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ellina Gorby
 * The class implements the logic of the calculator
 * The attribute Calcview is a string that is displayed on the calculator's display.
 * 
 * 
 */
public class CalculatorController {
    
    private String calcview;

    public String getCalcview() {
        return calcview;
    }
    /**
     * The method takes a mathematical expression as input and returns its evaluated result as a string. 
     * @param expression 
     * @return  result of expression (if the expression is successfully evaluated) error-message
     */
    
    private String calcExpression(String expression) {
        ELProcessor processor = new ELProcessor();
        try {
            Object result = processor.eval(expression);
            return result.toString();
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }
    }

    /**
     *
     * Calculates the result of a mathematical expression entered by the user.
     *
     * @param request - servlet request containing user input
     *
     * the method puts the current expression entered by the user or the result 
     * of the expression if it can be evaluated in the calсview attribute
     * 
     */
    public void calculator(HttpServletRequest request) {
        HttpSession session = request.getSession();
        calcview = (String) session.getAttribute("calcview");

        String storedOperation = (String) session.getAttribute("storedOperation");
        String storedNumber = (String) session.getAttribute("storedNumber");
        String storedDot = (String) session.getAttribute("storedDot");

        if (calcview == null) {
            calcview = "";
        }
        String numberString = request.getParameter("number");
        String operation = request.getParameter("operation");

        if (operation != null) {
            if (storedOperation != null) {
                if (calcview.substring(calcview.length() - 1) == storedOperation) {
                    session.setAttribute(storedOperation, operation);
                    calcview = calcview.replace(storedOperation, operation);
                } else {
                    storedNumber = calcExpression(calcview);
                    session.setAttribute("storedDot", null);
                    if (operation.equals("=")) {
                        calcview = storedNumber;
                        session.setAttribute("storedOperation", null);
                        if (storedNumber.contains(".")) {
                            session.setAttribute("storedDot", ".");
                        }

                    } else {
                        calcview = storedNumber + operation;

                        session.setAttribute("storedOperation", operation);
                    }
                }
            } else {
                if (!calcview.equals("") && !operation.equals("=")) {
                    storedNumber = calcview;
                    calcview = calcview + operation;
                    session.setAttribute("storedOperation", operation);
                }
            }
            session.setAttribute("storedNumber", storedNumber);
        }
        if (numberString != null) {

            if (!numberString.equals(".")) {
                calcview = calcview + numberString;
            } else if (numberString.equals(".") && !calcview.equals("") && storedDot == null) {
                calcview = calcview + numberString;
                session.setAttribute("storedDot", numberString);
            }
        }

        String clear = request.getParameter("clear");
        if (clear != null) {
            calcview = "";
            session.setAttribute("storedOperation", null);
            session.setAttribute("storedNumber", null);
            session.setAttribute("storedDot", null);
        }
        session.setAttribute("calcview", calcview);
    }

}
