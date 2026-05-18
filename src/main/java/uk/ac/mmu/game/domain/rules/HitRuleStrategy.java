package uk.ac.mmu.game.domain.rules;
public interface HitRuleStrategy {

    boolean allowMove(int newPosition, int otherPlayerPosition);

}
