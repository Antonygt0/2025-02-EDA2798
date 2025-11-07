package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Balance;

public class BalanceRepository {

    private final Map<String, Balance> balanceStorage = new HashMap<>();

    public BalanceRepository() {
        initData();
    }
    private void initData() {
        balanceStorage.put("ACC001_" + LocalDate.now().minusDays(10),
                new Balance("ACC001", new BigDecimal("500000"), BigDecimal.ZERO, new BigDecimal("500000"),
                        LocalDate.now().minusDays(10), "Depósito inicial"));
        balanceStorage.put("ACC002_" + LocalDate.now().minusDays(9),
                new Balance("ACC002", new BigDecimal("250000"), BigDecimal.ZERO, new BigDecimal("250000"),
                        LocalDate.now().minusDays(9), "Depósito inicial"));
        balanceStorage.put("ACC003_" + LocalDate.now().minusDays(8),
                new Balance("ACC003", new BigDecimal("700000"), BigDecimal.ZERO, new BigDecimal("700000"),
                        LocalDate.now().minusDays(8), "Depósito inicial"));
        balanceStorage.put("ACC004_" + LocalDate.now().minusDays(7),
                new Balance("ACC004", new BigDecimal("300000"), new BigDecimal("50000"), new BigDecimal("250000"),
                        LocalDate.now().minusDays(7), "Retiro en cajero"));
        balanceStorage.put("ACC005_" + LocalDate.now().minusDays(6),
                new Balance("ACC005", new BigDecimal("400000"), BigDecimal.ZERO, new BigDecimal("400000"),
                        LocalDate.now().minusDays(6), "Depósito inicial"));
        balanceStorage.put("ACC006_" + LocalDate.now().minusDays(5),
                new Balance("ACC006", new BigDecimal("600000"), new BigDecimal("100000"), new BigDecimal("500000"),
                        LocalDate.now().minusDays(5), "Pago de servicios"));
        balanceStorage.put("ACC007_" + LocalDate.now().minusDays(4),
                new Balance("ACC007", new BigDecimal("800000"), BigDecimal.ZERO, new BigDecimal("800000"),
                        LocalDate.now().minusDays(4), "Depósito inicial"));
        balanceStorage.put("ACC008_" + LocalDate.now().minusDays(3),
                new Balance("ACC008", BigDecimal.ZERO, new BigDecimal("200000"), new BigDecimal("300000"),
                        LocalDate.now().minusDays(3), "Transferencia enviada"));
        balanceStorage.put("ACC009_" + LocalDate.now().minusDays(2),
                new Balance("ACC009", new BigDecimal("1000000"), BigDecimal.ZERO, new BigDecimal("1000000"),
                        LocalDate.now().minusDays(2), "Depósito empresarial"));
        balanceStorage.put("ACC010_" + LocalDate.now().minusDays(1),
                new Balance("ACC010", BigDecimal.ZERO, new BigDecimal("150000"), new BigDecimal("350000"),
                        LocalDate.now().minusDays(1), "Pago de préstamo"));
    }

    public Balance save(Balance balance) {
        String id = generateId(balance);
        balanceStorage.put(id, balance);
        return balance;
    }

    public Optional<Balance> findById(String id) {
        return Optional.ofNullable(balanceStorage.get(id));
    }

    public List<Balance> findAll() {
        return new ArrayList<>(balanceStorage.values());
    }

    public boolean deleteById(String id) {
        return balanceStorage.remove(id) != null;
    }

    private String generateId(Balance balance) {
        String accPart = balance.getAccountNumber() != null ? balance.getAccountNumber() : "no-account";
        String datePart = balance.getDate() != null ? balance.getDate().toString() : "no-date";
        return accPart + "_" + datePart + "_" + UUID.randomUUID();
    }

    public List<Balance> findByAccount(String accountNumber) {
        List<Balance> results = new ArrayList<>();
        for (Balance b : balanceStorage.values()) {
            if (b.getAccountNumber() != null && b.getAccountNumber().equals(accountNumber)) {
                results.add(b);
            }
        }
        return results;
    }

    public Optional<Balance> findLatestByAccount(String accountNumber) {
        return findByAccount(accountNumber).stream()
                .sorted(Comparator.comparing(Balance::getDate))
                .reduce((first, second) -> second);
    }

}
