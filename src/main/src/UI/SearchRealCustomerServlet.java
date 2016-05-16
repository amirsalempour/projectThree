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
import java.util.List;

@WebServlet(name = "/searchRealCustomerServlet")
public class SearchRealCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>\n" +
                "<head lang=\"fa\" xml:lang=\"IR\"> ");
        printWriter.print("<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "}\n" +
                "</style>");
        printWriter.println("<div align=\"right\" style=\"font-family: 'Golestan nazanin', 'Golestan Traffic', 'Tahoma';background-color: antiquewhite \">");

        printWriter.println("<h2> لیست کاربران </h2>");
        try {
            RealCustomerType realCustomer = new RealCustomerType();
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String fatherName=request.getParameter("fatherName");
            String nationalCode=request.getParameter("nationalCode");
            int customerNumber= 0;
            try {
                customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
            } catch (NumberFormatException e) {
               customerNumber = 0;
            }
            String dayOfMounth = request.getParameter("dob_day");
            String mounthOfYear = request.getParameter("dob_month");
            String year = request.getParameter("dob_year");
            String birthDate = year + "/" + mounthOfYear + "/" + dayOfMounth;

            if (firstName.length()>0){
                realCustomer.setFirstName(firstName);
            }
           if (lastName.length()>0) {
               realCustomer.setLastName(lastName);
           }
            if (fatherName.length()>0) {
                realCustomer.setFatherName(fatherName);
            }
            if (nationalCode.length()>0) {
                if (ValidateRealCustomer.isNationalCodeValid(nationalCode)) {
                    realCustomer.setNationalCode(nationalCode);
                }
            }

            if (String.valueOf(customerNumber).length()>0) {
                realCustomer.setCustomerNumber(customerNumber);
            }

            List<RealCustomerType> list = RealCustomerCrud.searchRecords(realCustomer);
            if(list.size()==0) {
                printWriter.print("<tr><td> هیچ رکوردی با این مشخصات یافت نگردید </td></tr>");
            }
            printWriter.print("<table border='1' with=100%'");
            printWriter.print("<tr><td> نام </td><td> نام خانوادگی  </td><td> نام پدر </td> <td> تاریخ تولد </td> <td> کد ملی </td> <td>  شماره مشتری </th></td>  ");
            printWriter.print("</tr>");

            for (RealCustomerType realCustomerType : list) {
                printWriter.print("<tr><td>" + realCustomerType.getFirstName() + "</td><td>" + realCustomerType.getLastName() +
                        "</td><td>" + realCustomerType.getFatherName() + "</td><td>" + realCustomerType.getBirthDate() + "</td><td>" + realCustomerType.getNationalCode() +
                        "</td><td>" + realCustomerType.getCustomerNumber() + "</td><td>" +
                        "<a href='EditRealCustomer?customerNumber=" +
                        realCustomerType.getCustomerNumber() + "'> edit  </a> </td> <td> <a href='RealCustomerDelete?customerNumber=" +
                        realCustomerType.getCustomerNumber() + "' > delete</a></td></tr> ");
            }
            printWriter.println("</table> </html>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.close();

    }

}
