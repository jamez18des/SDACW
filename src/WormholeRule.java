import java.util.HashMap;
import java.util.Map;

public class WormholeRule implements TeleportRuleStrategy {

    private final Map<Integer, Integer> wormholes = new HashMap<>();

    public WormholeRule(int[][] pairs) {
        for (int[] pair : pairs) {
            wormholes.put(pair[0], pair[1]);
            wormholes.put(pair[1], pair[0]);
        }
    }

    @Override
    public int applyRule(int position) {
        return wormholes.getOrDefault(position, position);
    }
}