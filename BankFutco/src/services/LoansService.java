package services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.Loans;
import repositories.LoansRepository;

public class LoansService {

    private final LoansRepository loansRepository;
    private final BalanceService balanceService; 

    public LoansService() {
        this.loansRepository = new LoansRepository();
        this.balanceService = new BalanceService(); 
    }

    public LoansService(LoansRepository loansRepository, BalanceService balanceService) {
        this.loansRepository = loansRepository;
        this.balanceService = balanceService;
    }

   

    public Loans save(Loans loan) {
        return loansRepository.save(loan.getAccountNumber(), loan);
    }

    public Optional<Loans> findById(String id) {
        return loansRepository.findById(id);
    }

    public List<Loans> findAll() {
        return loansRepository.findAll();
    }

    public boolean deleteById(String id) {
        return loansRepository.deleteById(id);
    }

   
    public List<Loans> findByAccountNumber(String accountNumber) {
        return loansRepository.findByAccountNumber(accountNumber);
    }

    public Optional<Loans> findLatestByAccount(String accountNumber) {
        return loansRepository.findLatestByAccount(accountNumber);
    }

    public Loans createLoan(String accountNumber, String type, BigDecimal totalLoan) {
        Loans loan = new Loans(
                accountNumber,
                LocalDate.now(),
                type,
                BigDecimal.ZERO,
                totalLoan,
                totalLoan
        );
        return save(loan);
    }

    
    public Optional<Loans> makePayment(String accountNumber, BigDecimal amount) {
        Optional<Loans> loanOpt = findLatestByAccount(accountNumber);

        if (loanOpt.isEmpty()) return Optional.empty();

        Loans loan = loanOpt.get();

       
        BigDecimal currentBalance = balanceService.calculateCurrentBalance(accountNumber)
                .orElse(BigDecimal.ZERO);

        if (amount.compareTo(currentBalance) > 0) {

            return Optional.empty();
        }

       
        balanceService.registerTransaction(accountNumber, BigDecimal.ZERO, amount, "Pago de préstamo");

        
        BigDecimal newOutstanding = loan.getOutstandingAmt().subtract(amount);
        loan.setOutstandingAmt(newOutstanding);

        return Optional.of(loan);
    }

    public Optional<BigDecimal> calculateOutstanding(String accountNumber) {
        return findLatestByAccount(accountNumber)
                .map(Loans::getOutstandingAmt);
    }

    public Loans createEmptyLoanRecord(String accountNumber) {
        Loans loan = new Loans(
                accountNumber,
                LocalDate.now(),
                "Sin préstamo",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
        return save(loan);
    }
}
