package UI;

import businessLogic.RealCustomerType;
import businessLogic.ValidateRealCustomer;
import dataLayer.RealCustomerCrud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "/InsertRealCustomerServlet")
public class InsertRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        response.setCharacterEncoding("UTF-8");
        PrintWriter printOut = response.getWriter();
        RealCustomerType realCustomer1=new RealCustomerType();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String nationalCode = request.getParameter("nationalCode");

        String birthDate;
        String dayOfMounth = request.getParameter("dob_day");
        String mounthOfYear = request.getParameter("dob_month");
        String year = request.getParameter("dob_year");
        birthDate = year + "/" + mounthOfYear + "/" + dayOfMounth;
        realCustomer1.setFirstName(firstName);
        realCustomer1.setLastName(lastName);
        realCustomer1.setFatherName(fatherName);
        realCustomer1.setNationalCode(nationalCode);
        realCustomer1.setBirthDate(birthDate);

        try {

            if (ValidateRealCustomer.checkFilledAllField(realCustomer1) == true) {
                if (ValidateRealCustomer.doesExistRecord(nationalCode) == false) {
                    if ((ValidateRealCustomer.isNationalCodeValid(nationalCode)) == true) {
                        RealCustomerType realCustomer = new RealCustomerType();
                        realCustomer.setFirstName(firstName);
                        realCustomer.setLastName(lastName);
                        realCustomer.setFatherName(fatherName);
                        realCustomer.setBirthDate(birthDate);
                        realCustomer.setNationalCode(nationalCode);

                        int status = RealCustomerCrud.recordInsert(realCustomer);

                        if (status > 0) {
                            printOut.print("<p align='center' charset=UTF-8>Record saved successfully!</p><br>");
                            printOut.print("  ش  ماره مشتری عبارت است از:"+realCustomer.getCustomerNumber()+"<br> ");
                            printOut.print("<a href='index.html'>link here</a><br>");

                        } else {
                            printOut.print("<p>Record could not be saved correctly!</p>");
                            printOut.print("<a href='index.html'>link here</a>");

                        }

                    } else if (!ValidateRealCustomer.isNationalCodeValid((nationalCode)) == true) {

                        printOut.print("<p>  this nationalcode is not valid   </p>");
                    }
                } else if (ValidateRealCustomer.isNationalCodeValid(nationalCode) == true) {
                    printOut.print("<p> this nationalcode was entered before </p>");
                }
            } else if (ValidateRealCustomer.checkFilledAllField(realCustomer1) == false) {

                printOut.print("<p>is not all fields full!</p>");
                printOut.print("<a href='index.html'>link here</a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printOut.close();

    }
}

