package uk.ac.mmu.game.domain;
import uk.ac.mmu.game.domain.DiceShaker;
public class Dice {

    private final DiceShaker shaker;

    public Dice(DiceShaker shaker) {
        this.shaker = shaker;
    }

    public int roll() {
        return shaker.shake();
    }

}
