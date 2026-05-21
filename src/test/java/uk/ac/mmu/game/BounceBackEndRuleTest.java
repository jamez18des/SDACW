package uk.ac.mmu.game;
import org.junit.jupiter.api.Test;
import uk.ac.mmu.game.infrastructure.rules.BounceBackEndRule;
import static
        org.junit.jupiter.api.Assertions.*;

public class BounceBackEndRuleTest {

    private final BounceBackEndRule rule = new BounceBackEndRule();

    @Test
    void playerLandsExactlyOnEnd() {
        // index 20, roll 4, endIndex 24 — lands exactly, should win
        int result = rule.applyRule(20, 4, 24);
        assertEquals(24, result);
    }

    @Test
    void playerOvershootsBounces() {
        // index 23, roll 5, endIndex 24 — overshoots by 4, bounces back 4 from end
        int result = rule.applyRule(23, 5, 24);
        assertEquals(20, result);
    }

    @Test
    void playerDoesNotReachEnd() {
        // index 5, roll 3, endIndex 24 — normal move
        int result = rule.applyRule(5, 3, 24);
        assertEquals(8, result);
    }


}
