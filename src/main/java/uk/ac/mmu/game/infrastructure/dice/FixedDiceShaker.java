package uk.ac.mmu.game.infrastructure.dice;
import uk.ac.mmu.game.domain.DiceShaker;
public class FixedDiceShaker implements DiceShaker{
    private final int[] rolls;
    private int index = 0;

    public FixedDiceShaker(int[] rolls) {
        this.rolls = rolls;
    }

    @Override
    public int shake() {
        return rolls[index++];
    }
}
