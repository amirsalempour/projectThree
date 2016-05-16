package UI;

import businessLogic.RealCustomerType;
import dataLayer.RealCustomerCrud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "/EditRealCustomer")
public class EditRealCustomer extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{

        response.setContentType("text/html charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter=response.getWriter();
        printWriter.println("<p align='center'> نتیجه ی ویرایش </p>");

        String customerNumber = request.getParameter("customerNumber");
        RealCustomerCrud realCustomerCrud =new RealCustomerCrud();
        try {
            RealCustomerType realCustomerType= realCustomerCrud.getRecordById(Integer.parseInt(customerNumber));

            printWriter.println("<html>\n" +
                    "<head lang=\"fa\" xml:lang=\"IR\">");
            printWriter.println(" <style>\n" +
                    "        body{\n" +
                    "            background-image: url(\"bank_note_3018.jpg\");\n" +
                    "            background-repeat: no-repeat;\n" +
                    "            background-attachment: fixed;\n" +
                    "            background-position: center;\n" +
                    "        }\n" +
                    "        table {\n" +
                    "            border-collapse: separate;\n" +
                    "            empty-cells: hide;\n" +
                    "        }\n" +
                    "    </style>");
            printWriter.println("<meta charset=\"UTF-8\">");
            printWriter.println("<body>\n" +
                    "<div align=\"right\" style=\"font-family: 'Golestan nazanin', 'Golestan Traffic', 'Tahoma';background: linear-gradient(antiquewhite,navajowhite,papayawhip) \">\n" +
                    "\n" +
                    "\n" +
                    "<table >\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "        <tr>\n" +
                    "                <td>\n");
            printWriter.println("مشخصات قابل ویرایش مشتری "+" <br>");


            printWriter.print(" <form action=\"editRealCustomerServlet\" method=\"post\" >");
            printWriter.print("\n" +" <input type=\"text\" name=\"firstName\" value='"+ realCustomerType.getFirstName()+"'>\n"+  "     : نام" +

                    " <br><br>\n" +
                    " <input type=\"text\" name=\"lastName\" value='"+realCustomerType.getLastName()+"'>\n" +
                    "                : نام خانوادگی\n" +
                    "\n" +                    "                                        <br><br>\n" +
                    "           <input type=\"text\" name=\"fatherName\" value='"+realCustomerType.getFatherName()+"' >\n" +
                    "                                        :نام پدر\n" +
                    "\n" +
                    "\n" +                    "                                        <br><br>\n" +
                    "                    <br><br>\n" +
                    "        <input type=\"text\" name=\"birthDate\" value='"+realCustomerType.getBirthDate()+"' >"+
                    " تاریخ تولد "+
                    "\n" +
                    "\n" +
                    "\n" +
                    "            </select>\n" +
                    "\n "+
                    ":تاریخ تولد"+
                    "<br><br>"+

                    "       <input type=\"text\" name=\"nationalCode\" value='"+realCustomerType.getNationalCode()+"' >"+
                    "                                        :کد ملی\n" +
                    "\n" +                    "                                        <br>\n" +
                    "     <br>\n" +
                    "      <input type=\"text\" name=\"customerNumber\" value='"+realCustomerType.getCustomerNumber()   +"'>\n" +                "شماره مشتری\n" +
                    "\n" +
                    "\n" +
                    "                                <br><br>\n" +
                    "\n" +
                    "    <input style=\" width: 5em ;height: 2em;border: brown; font-family: 'Golestan nazanin', 'Golestan Traffic', 'Tahoma'; color: blueviolet\" type=\"submit\" id=\"submit\" value=\"بروزرسانی\">\n" +
                    "   </form>");
            printWriter.println("</td></tr></table> </html>");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
