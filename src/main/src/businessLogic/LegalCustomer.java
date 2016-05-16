package businessLogic;

import java.util.Date;

/**
 * Created by Dotin school 6 on 5/4/2016.
 */
public class LegalCustomer extends Customer {
    private String companyName;
    private Date submitTime;
    private String economicCode;
//    private int customerNumber;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

//    public int getCustomerNumber() {
//        return customerNumber;
//    }
//
//    public void setCustomerNumber(int customerNumber) {
//        this.customerNumber = customerNumber;
//    }
}
