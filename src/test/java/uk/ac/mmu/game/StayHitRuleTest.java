package uk.ac.mmu.game;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.infrastructure.rules.StayHitRule;
import static org.junit.jupiter.api.Assertions.*;

public class StayHitRuleTest
{
    private final StayHitRule rule = new StayHitRule();

    @Test
    void moveIsBlockedWhenHittingAnotherPlayer() {
        // both on position 13 — move should be blocked
        boolean result = rule.allowMove(13, 13);
        assertFalse(result);
    }

    @Test
    void moveIsAllowedWhenNoHit() {
        // different positions — no hit, move allowed
        boolean result = rule.allowMove(13, 7);
        assertTrue(result);
    }
}
