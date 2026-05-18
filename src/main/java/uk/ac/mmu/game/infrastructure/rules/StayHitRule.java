package uk.ac.mmu.game.infrastructure.rules;
import uk.ac.mmu.game.domain.rules.HitRuleStrategy;
public class StayHitRule implements HitRuleStrategy {
    @Override
public boolean allowMove(int newPosition, int otherPlayerPosition) {
    return newPosition != otherPlayerPosition;
}
}
