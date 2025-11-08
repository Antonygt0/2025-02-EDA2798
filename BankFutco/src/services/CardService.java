package services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import model.Cards;
import repositories.CardRepository;

public class CardService implements ICardService {

    private final CardRepository cardRepository;

    public CardService() {
        this(new CardRepository());
    }

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Cards save(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public Optional<Cards> findById(String cardNumber) {
        return cardRepository.findById(cardNumber);
    }

    @Override
    public List<Cards> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public boolean deleteById(String cardNumber) {
        return cardRepository.deleteById(cardNumber);
    }

    public Optional<Cards> makeTransaction(String cardNumber, BigDecimal amount, boolean isPayment) {
        Optional<Cards> cardOpt = cardRepository.findById(cardNumber);
        if (cardOpt.isEmpty()) return Optional.empty();

        Cards card = cardOpt.get();
        if (isPayment) {
            card.setAmountUsed(card.getAmountUsed().subtract(amount));
        } else {
            if (card.getAvailable().compareTo(amount) < 0) {
                System.out.println("Fondos insuficientes en la tarjeta.");
                return Optional.of(card);
            }
            card.setAmountUsed(card.getAmountUsed().add(amount));
        }

        card.setAvailable(card.getTotalLimit().subtract(card.getAmountUsed()));
        cardRepository.save(card);
        return Optional.of(card);
    }
}
