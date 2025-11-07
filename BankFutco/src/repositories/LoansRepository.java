package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import model.Loans;

public class LoansRepository {

    private final Map<String, Loans> loansStorage = new HashMap<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {
    loansStorage.put("ACC001_" + LocalDate.now().minusDays(10),
    new Loans("ACC001", LocalDate.now().minusDays(10), "Hipotecario",
              new BigDecimal("2000000"),  
              BigDecimal.ZERO,             
              new BigDecimal("2000000"))); 

loansStorage.put("ACC002_" + LocalDate.now().minusDays(9),
    new Loans("ACC002", LocalDate.now().minusDays(9), "Personal",
              new BigDecimal("800000"),
              BigDecimal.ZERO,
              new BigDecimal("800000")));

loansStorage.put("ACC003_" + LocalDate.now().minusDays(8),
    new Loans("ACC003", LocalDate.now().minusDays(8), "Educativo",
              new BigDecimal("1200000"),
              BigDecimal.ZERO,
              new BigDecimal("1200000")));

loansStorage.put("ACC004_" + LocalDate.now().minusDays(7),
    new Loans("ACC004", LocalDate.now().minusDays(7), "Vehicular",
              new BigDecimal("1500000"),
              BigDecimal.ZERO,
              new BigDecimal("1500000")));

loansStorage.put("ACC005_" + LocalDate.now().minusDays(6),
    new Loans("ACC005", LocalDate.now().minusDays(6), "Personal",
              new BigDecimal("900000"),
              BigDecimal.ZERO,
              new BigDecimal("900000")));

loansStorage.put("ACC006_" + LocalDate.now().minusDays(5),
    new Loans("ACC006", LocalDate.now().minusDays(5), "Hipotecario",
              new BigDecimal("2500000"),
              BigDecimal.ZERO,
              new BigDecimal("2500000")));

loansStorage.put("ACC007_" + LocalDate.now().minusDays(4),
    new Loans("ACC007", LocalDate.now().minusDays(4), "Educativo",
              new BigDecimal("600000"),
              BigDecimal.ZERO,
              new BigDecimal("600000")));

loansStorage.put("ACC008_" + LocalDate.now().minusDays(3),
    new Loans("ACC008", LocalDate.now().minusDays(3), "Vehicular",
              new BigDecimal("1800000"),
              BigDecimal.ZERO,
              new BigDecimal("1800000")));

loansStorage.put("ACC009_" + LocalDate.now().minusDays(2),
    new Loans("ACC009", LocalDate.now().minusDays(2), "Personal",
              new BigDecimal("700000"),
              BigDecimal.ZERO,
              new BigDecimal("700000")));

loansStorage.put("ACC010_" + LocalDate.now().minusDays(1),
    new Loans("ACC010", LocalDate.now().minusDays(1), "Hipotecario",
              new BigDecimal("2500000"),
              BigDecimal.ZERO,
              new BigDecimal("2500000")));


}

    public Loans save(String accountNumber, Loans loan) {
        String id = generateId(accountNumber, loan);
        loansStorage.put(id, loan);
        return loan;
    }

   public Optional<Loans> findById(String id) {
    return loansStorage.values().stream()
            .filter(loan -> loan.getAccountNumber().equalsIgnoreCase(id))
            .findFirst();
}



    public List<Loans> findAll() {
        return new ArrayList<>(loansStorage.values());
    }

        public List<Loans> findByAccountNumber(String accountNumber) {
    List<Loans> results = new ArrayList<>();
    for (Map.Entry<String, Loans> entry : loansStorage.entrySet()) {
        if (entry.getKey().startsWith(accountNumber + "_")) { 
            results.add(entry.getValue());
        }
    }
    return results;
}


    public boolean deleteById(String id) {
        return loansStorage.remove(id) != null;
    }


    private String generateId(String accountNumber, Loans loan) {
        String accPart = accountNumber != null ? accountNumber : "no-account";
        String datePart = loan.getDate() != null ? loan.getDate().toString() : "no-date";
        return accPart + "_" + datePart + "_" + UUID.randomUUID();
    }

    
    public Optional<Loans> findLatestByAccount(String accountNumber) {
        return findByAccountNumber(accountNumber).stream()
                .sorted(Comparator.comparing(Loans::getDate))
                .reduce((first, second) -> second);
    }


}
