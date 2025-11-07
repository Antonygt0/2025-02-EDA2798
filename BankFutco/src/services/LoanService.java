package services;

import java.util.*;
import model.Loans;
import repositories.LoanRepository;

public class LoanService implements ILoansService {

    private final LoanRepository loansRepository;

    public LoanService() {
        this(new LoanRepository());
    }

    public LoanService(LoanRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Override
    public Loans save(Loans loan) {
        return loansRepository.save(loan);
    }

    @Override
    public Optional<Loans> findById(String id) {
        return loansRepository.findById(id);
    }

    @Override
    public List<Loans> findAll() {
        return loansRepository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return loansRepository.deleteById(id);
    }
}

