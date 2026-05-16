import java.util.Random;
public class RandomDiceShaker implements  DiceShaker{

    private static final Random random = new Random();

    @Override
    public int shake() {
        return random.nextInt(6) + 1
                + random.nextInt(6) + 1; // two dice summed;
    }
}
