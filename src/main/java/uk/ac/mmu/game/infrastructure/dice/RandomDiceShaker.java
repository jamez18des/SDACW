package uk.ac.mmu.game.infrastructure.dice;
import uk.ac.mmu.game.domain.DiceShaker;
import java.util.Random;

public class RandomDiceShaker implements DiceShaker {

    private static final Random random = new Random();
    private final int numberOfDice;

    public RandomDiceShaker(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    @Override
    public int shake() {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }
}