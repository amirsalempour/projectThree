package dataLayer;

import businessLogic.RealCustomerType;
import businessLogic.ValidateRealCustomer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 6 on 5/4/2016.
 */
public class RealCustomerCrud {
//    public static void main(String[] args) throws IOException, SQLException {
//
//        RealCustomerCrud.getConnection();
//    }

    public static Connection getConnection() throws SQLException,IOException{
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/banking";
        final String USER = "root";
        final String PASSWORD = "root";
        Connection connection = null;

        try {

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
        return connection;
    }

    
    public static boolean doesExistTheRecord(String nationalCode) throws SQLException, IOException {
        Connection connection = RealCustomerCrud.getConnection();
        PreparedStatement statement = connection.prepareStatement("select national_code  from banking.real_customer where  national_code = ?");
        statement.setString(1, nationalCode);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.wasNull() || !resultSet.next()) {
            return false;
        } else {

            return true;
        }
    }

    
    public static int recordInsert(RealCustomerType realCustomer) throws SQLException, IOException {
        int status = 0;

        Connection connection = RealCustomerCrud.getConnection();

        if(ValidateRealCustomer.checkFilledAllField(realCustomer)==true) {
            PreparedStatement statement1=connection.prepareStatement("insert into banking.customer() values()");
            statement1.executeUpdate();

            PreparedStatement statement = connection.prepareStatement("Insert into banking.real_customer(customer_number,first_name,last_name,father_name,birthdate,national_code)  values (last_insert_id(),?,?,?,?,?)");
            statement.setString(1, realCustomer.getFirstName());
            statement.setString(2, realCustomer.getLastName());
            statement.setString(3, realCustomer.getFatherName());
            statement.setString(4, realCustomer.getBirthDate());
            statement.setString(5, realCustomer.getNationalCode());
            status = statement.executeUpdate();
            connection.close();
        }
        return status;
    }


    public static int update(RealCustomerType realCustomer) throws SQLException, IOException {
        int status = 0;
        Connection connection = RealCustomerCrud.getConnection();
        PreparedStatement statement = connection.prepareStatement("update banking.real_customer set first_name=?,last_name=?,father_name=?,birthdate=?,national_code=? where customer_number=?");
        statement.setString(1, realCustomer.getFirstName());
        statement.setString(2, realCustomer.getLastName());
        statement.setString(3, realCustomer.getFatherName());
        statement.setString(4,realCustomer.getBirthDate());
        statement.setString(5,realCustomer.getNationalCode());
        statement.setInt(6, realCustomer.getCustomerNumber());

        status = statement.executeUpdate();

        connection.close();
        return status;
    }
public  static RealCustomerType getRecordById(int customerNumber) throws IOException, SQLException {
    RealCustomerType realCustomer=new RealCustomerType();
    Connection connection=RealCustomerCrud.getConnection();
    PreparedStatement statement=connection.prepareStatement("select * from real_customer where customer_number=?");
    statement.setInt(1,customerNumber);
    ResultSet resultSet=statement.executeQuery();
    if (resultSet.next()){
        realCustomer.setFirstName(resultSet.getString(1));
        realCustomer.setLastName(resultSet.getString(2));
        realCustomer.setFatherName(resultSet.getString(3));
        realCustomer.setBirthDate(resultSet.getString(4));
        realCustomer.setNationalCode(resultSet.getString(5));
        realCustomer.setCustomerNumber(Integer.parseInt(resultSet.getString(6)));
    }
    return realCustomer;
}


    public static List<RealCustomerType> searchRecords(RealCustomerType realCustomerType) throws SQLException, IOException {
        List<RealCustomerType> list = new ArrayList<RealCustomerType>();
        Connection connection = RealCustomerCrud.getConnection();
        RealCustomerType realCustomer = new RealCustomerType();
        String checkIf=" where ( ";

        if (realCustomerType.getFirstName() != null) {
            checkIf = checkIf + " first_name = '" + realCustomerType.getFirstName() + "' and ";
        }
        if (realCustomerType.getLastName() != null) {
            checkIf = checkIf +"last_name like '"+ realCustomerType.getLastName() + "' and ";
        }
        if (realCustomerType.getNationalCode() != null) {
            checkIf = checkIf + " national_code like '" + realCustomerType.getNationalCode() + "' and ";
        }
        if (realCustomer.getCustomerNumber() != 0 ) {
            checkIf = checkIf + "customer_number=" + realCustomer.getCustomerNumber() + " and ";
        }
        if (realCustomerType.getFatherName()!= null) {
            checkIf = checkIf + "father_name='" + realCustomerType.getFatherName() + "' and ";
        }
        checkIf = checkIf + " 1=1 )";

        PreparedStatement statement = connection.prepareStatement("select * from banking.real_customer "+ checkIf );
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            RealCustomerType realCustomers = new RealCustomerType();
            realCustomers.setFirstName(resultSet.getString(1));
            realCustomers.setLastName(resultSet.getString(2));
            realCustomers.setFatherName(resultSet.getString(3));
            realCustomers.setBirthDate(resultSet.getString(4));
            realCustomers.setNationalCode(resultSet.getString(5));
            realCustomers.setCustomerNumber(resultSet.getInt(6));
            list.add(realCustomers);

        }

        connection.close();
        return list;
    }


    public static int delete(int customerNumber) throws SQLException, IOException {
        int status = 0;
        Connection connection = RealCustomerCrud.getConnection();

        PreparedStatement statement = connection.prepareStatement("delete from banking.real_customer where customer_number=?");
        statement.setInt(1, customerNumber);

        status = statement.executeUpdate();
        connection.close();
        return status;
    }
}