package uk.ac.mmu.game.infrastructure.rules;
import uk.ac.mmu.game.domain.rules.EndRuleStrategy;
public class OvershootEndRule implements EndRuleStrategy{

    @Override
    public int applyRule(int currentIndex, int steps, int endIndex) {
        int newIndex = currentIndex + steps;
        return newIndex >= endIndex ? endIndex : newIndex;
    }
}
