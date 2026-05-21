package uk.ac.mmu.game;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.infrastructure.rules.IgnoreHitRule;
import static org.junit.jupiter.api.Assertions.*;
public class IgnoreHitRuleTest {
    private final IgnoreHitRule rule = new IgnoreHitRule();

    @Test
    void moveIsAlwaysAllowedEvenWhenHitting() {
        // both on position 13 — hit is ignored, move allowed
        boolean result = rule.allowMove(13, 13);
        assertTrue(result);
    }

    @Test
    void moveIsAllowedWhenNoHit() {
        // different positions — move allowed
        boolean result = rule.allowMove(13, 7);
        assertTrue(result);
    }
}
