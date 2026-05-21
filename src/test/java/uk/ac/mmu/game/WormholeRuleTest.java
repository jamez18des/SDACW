package uk.ac.mmu.game;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.infrastructure.rules.WormholeRule;
import static org.junit.jupiter.api.Assertions.*;

public class WormholeRuleTest {
    private final WormholeRule rule = new WormholeRule(new int[][]{{4, 9}, {23, 19}});

    @Test
    void playerIsTelepotedForward() {
        // landing on 4 should teleport to 9
        int result = rule.applyRule(4);
        assertEquals(9, result);
    }

    @Test
    void wormholeIsBidirectional() {
        // landing on 9 should teleport back to 4
        int result = rule.applyRule(9);
        assertEquals(4, result);
    }

    @Test
    void noWormholeAtPosition() {
        // landing on 10 — no wormhole, stays at 10
        int result = rule.applyRule(10);
        assertEquals(10, result);
    }
}
