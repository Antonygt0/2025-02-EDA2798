package services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Balance;
import repositories.BalanceRepository;

public class BalanceService implements IBalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService() {
        this(new BalanceRepository());
    }

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Optional<Balance> findById(String id) {
        return balanceRepository.findById(id);
    }

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return balanceRepository.deleteById(id);
    }

    public List<Balance> findByAccount(String accountNumber) {
        return balanceRepository.findByAccount(accountNumber);
    }

    public Optional<BigDecimal> calculateCurrentBalance(String accountNumber) {
        return balanceRepository.findLatestByAccount(accountNumber)
                .map(Balance::getClosingBalance);
    }

    public Balance registerTransaction(String accountNumber, BigDecimal cashIn, BigDecimal cashOut, String description) {
        Optional<Balance> latestOpt = balanceRepository.findLatestByAccount(accountNumber);
        BigDecimal previousBalance = latestOpt.map(Balance::getClosingBalance).orElse(BigDecimal.ZERO);

        BigDecimal newBalance = previousBalance.add(cashIn).subtract(cashOut);

        Balance newEntry = new Balance();
        newEntry.setAccountNumber(accountNumber);
        newEntry.setCashIn(cashIn);
        newEntry.setCashOut(cashOut);
        newEntry.setClosingBalance(newBalance);
        newEntry.setDate(java.time.LocalDate.now());
        newEntry.setDescription(description);

        return save(newEntry);
    }

    public Balance createnewBalance(String accountNumber) {
    Balance balance = new Balance(
        accountNumber,
        BigDecimal.ZERO,
        BigDecimal.ZERO,
        BigDecimal.ZERO,
        LocalDate.now(),
        "Cuenta nueva sin movimientos"
    );
    return save(balance);
}
}
