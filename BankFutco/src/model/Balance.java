package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
    private String accountNumber;
    private LocalDate date;
    private String description;
    private BigDecimal cashIn;
    private BigDecimal cashOut;
    private BigDecimal closingBalance;

    public Balance() {
    }

    public Balance(String accountNumber, BigDecimal cashIn, BigDecimal cashOut, BigDecimal closingBalance, LocalDate date, String description) {
        this.accountNumber = accountNumber;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
        this.closingBalance = closingBalance;
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCashIn() {
        return cashIn;
    }

    public void setCashIn(BigDecimal cashIn) {
        this.cashIn = cashIn;
    }

    public BigDecimal getCashOut() {
        return cashOut;
    }

    public void setCashOut(BigDecimal cashOut) {
        this.cashOut = cashOut;
    }

    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Balance{");
        sb.append("date=").append(date);
        sb.append(", description=").append(description);
        sb.append(", cashIn=").append(cashIn);
        sb.append(", cashOut=").append(cashOut);
        sb.append(", closingBalance=").append(closingBalance);
        sb.append('}');
        return sb.toString();
    }


}
