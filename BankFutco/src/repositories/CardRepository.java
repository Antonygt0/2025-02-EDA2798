package repositories;

import java.math.BigDecimal;
import java.util.*;
import model.Cards;

public class CardRepository {

    private final List<Cards> storage = new ArrayList<>();

    public CardRepository() {
        initData();
    }
    
    private void initData() {
        storage.add(new Cards("CARD001", "Credit", new BigDecimal("5000000"), new BigDecimal("1200000"), "ACC001"));
        storage.add(new Cards("CARD002", "Debit", new BigDecimal("3000000"), new BigDecimal("500000"), "ACC002"));
        storage.add(new Cards("CARD003", "Credit", new BigDecimal("8000000"), new BigDecimal("3000000"), "ACC003"));
        storage.add(new Cards("CARD004", "Debit", new BigDecimal("1500000"), new BigDecimal("200000"), "ACC004"));
        storage.add(new Cards("CARD005", "Credit", new BigDecimal("10000000"), new BigDecimal("2500000"), "ACC005"));
        storage.add(new Cards("CARD006", "Debit", new BigDecimal("2500000"), new BigDecimal("1000000"), "ACC006"));
        storage.add(new Cards("CARD007", "Credit", new BigDecimal("4000000"), new BigDecimal("500000"), "ACC007"));
        storage.add(new Cards("CARD008", "Credit", new BigDecimal("9000000"), new BigDecimal("4500000"), "ACC008"));
        storage.add(new Cards("CARD009", "Debit", new BigDecimal("2000000"), new BigDecimal("100000"), "ACC009"));
        storage.add(new Cards("CARD010", "Credit", new BigDecimal("7000000"), new BigDecimal("2300000"), "ACC010"));
    }

    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
    }

    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return storage.stream().filter(c -> cardNumber.equals(c.getCardNumber())).findFirst();
    }

    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String cardNumber) {
        return findById(cardNumber).map(storage::remove).orElse(false);
    }

    public boolean existsById(String cardNumber) {
        return storage.stream().anyMatch(c -> cardNumber != null && cardNumber.equals(c.getCardNumber()));
    }
}
