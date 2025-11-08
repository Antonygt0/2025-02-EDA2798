package model;

import java.math.BigDecimal;

public class Cards {

    private String cardNumber;
    private String type; // Debit - Credit
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal available;
    private String accountNumber; // 🔹 Propietario

    public Cards() {
    }

    public Cards(String cardNumber, String type, BigDecimal totalLimit, BigDecimal amountUsed, String accountNumber) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.available = totalLimit.subtract(amountUsed);
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }

    public BigDecimal getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(BigDecimal amountUsed) {
        this.amountUsed = amountUsed;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Cards [cardNumber=" + cardNumber + ", type=" + type + ", totalLimit=" + totalLimit
                + ", amountUsed=" + amountUsed + ", available=" + available + ", accountNumber=" + accountNumber + "]";
    }
}
