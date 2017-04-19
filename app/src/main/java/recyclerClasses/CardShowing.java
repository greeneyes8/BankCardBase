package recyclerClasses;

import java.io.Serializable;

/**
 * Created by ab.mohammadi on 2/5/2017.
 */
public class CardShowing implements Serializable{

    private Long bankId;
    private Long personId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    private Long cardId;
    private int bankLogoImageId;
    private int bankCardImageId;

    private String cardNumber;
    private String personName;
    private String cvv2;
    private String expDate;
    private String accountNumber;
    private String branchCode;
    private double currentBalance;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public int getBankLogoImageId() {
        return bankLogoImageId;
    }

    public void setBankLogoImageId(int bankLogoImageId) {
        this.bankLogoImageId = bankLogoImageId;
    }

    public int getBankCardImageId() {
        return bankCardImageId;
    }

    public void setBankCardImageId(int bankCardImageId) {
        this.bankCardImageId = bankCardImageId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
