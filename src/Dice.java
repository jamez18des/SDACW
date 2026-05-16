public class Dice {

    private final DiceShaker shaker;

    public Dice(DiceShaker shaker) {
        this.shaker = shaker;
    }

    public int roll() {
        return shaker.shake();
    }

}
