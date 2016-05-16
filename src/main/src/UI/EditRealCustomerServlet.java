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

@WebServlet(name = "/editRealCustomerServlet")
public class EditRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printOut=response.getWriter();
        printOut.println("<h2 align center >  ویرایش  </h2>");
        int status=0;
        RealCustomerCrud realCustomerCrud =new RealCustomerCrud();
        try {

            String birthDate=request.getParameter("birthDate");
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String fatherName=request.getParameter("fatherName");
            String nationalCode=request.getParameter("nationalCode");


            RealCustomerType realCustomer= new RealCustomerType();
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setNationalCode(nationalCode);
            realCustomer.setBirthDate(birthDate);
          realCustomer.setCustomerNumber(Integer.parseInt(request.getParameter("customerNumber")));

            if(ValidateRealCustomer.checkFilledAllField(realCustomer)==true) {
                if (ValidateRealCustomer.isNationalCodeValid(nationalCode)) {
                    status = realCustomerCrud.update(realCustomer);
                    if (status > 0) {
                        printOut.print("<h2>  رکورد مورد نظر با موفقیت ویرایش گردید   </h2>");
                    } else {
                        printOut.print("<h2>    رکورد ویرایش نگردید    </h2>");
                    }
                } else{
                    printOut.println("<p>  کد ملی وارد شده اشتباه می باشد... کد ملی شامل حروف نمی باشد. </p> ");
                    printOut.println("<a href='realCustomer.html'> ویرایش  </a>");
                }
            }else if(ValidateRealCustomer.checkFilledAllField(realCustomer)!=true){
                printOut.println("<p>  لطفا همه فیلد ها را پر کنید  </p>");
                printOut.println("<a href='realCustomer.html'> ویرایش  </a>");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        printOut.close();
        response.sendRedirect("EditRealCustomer");

    }


    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{


}
}
