package UI;

import dataLayer.RealCustomerCrud;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Dotin school 6 on 5/7/2016.
 */
@WebServlet(name = "/RealCustomerDelete")
public class DeleteRealCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter=response.getWriter();
        String customerNumber = request.getParameter("customerNumber");
        RealCustomerCrud realCustomerCrud =new RealCustomerCrud();

        try {
            int id=Integer.parseInt(customerNumber);

            realCustomerCrud.delete(id);

                printWriter.println("<h2>");
                printWriter.print("  رکورد مورد نظر با موفقیت حذف گردید");
                printWriter.print("</h2>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        printWriter.close();
//        response.sendRedirect("http://localhost:8081/index.html");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{

    }


}
