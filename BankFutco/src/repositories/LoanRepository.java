package repositories;

import java.util.*;
import model.Loans;

public class LoanRepository {

    private final Map<String, Loans> loansStorage = new HashMap<>();

    public LoanRepository() {
        initData();
    }

    private void initData() {
        // Datos iniciales opcionales
    }

    public Loans save(Loans loan) {
        loansStorage.put(loan.getDate().toString(), loan);
        return loan;
    }

    public Optional<Loans> findById(String id) {
        return Optional.ofNullable(loansStorage.get(id));
    }

    public List<Loans> findAll() {
        return new ArrayList<>(loansStorage.values());
    }

    public boolean deleteById(String id) {
        return loansStorage.remove(id) != null;
    }
}

