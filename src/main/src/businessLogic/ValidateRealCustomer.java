package businessLogic;
import dataLayer.RealCustomerCrud;
import java.io.IOException;
import java.sql.SQLException;

public class ValidateRealCustomer {

    public static boolean doesExistRecord(String nationalCode) throws SQLException, IOException {
        return RealCustomerCrud.doesExistTheRecord(nationalCode);

    }
//String firstName, String lastName, String fatherName, String birthDate, String nationalCode

    public static boolean checkFilledAllField(RealCustomerType realCustomerType) {
        boolean status = false;
        if (realCustomerType.getFirstName().trim() != "" && realCustomerType.getLastName() != "" && realCustomerType.getFatherName().trim() != "" && realCustomerType.getBirthDate() != "" && realCustomerType.getNationalCode().trim() != "") {
        status=true;
        }
        return status;
    }

    public static boolean isNationalCodeValid(String nationalCode) {
        boolean status = false;
        String digit = "\\d+";
        if (nationalCode.matches(digit)) {
           status=true;
        }
        return status;
    }


}
