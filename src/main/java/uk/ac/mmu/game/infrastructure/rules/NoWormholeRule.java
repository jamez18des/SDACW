package uk.ac.mmu.game.infrastructure.rules;

import uk.ac.mmu.game.domain.rules.TeleportRuleStrategy;

public class NoWormholeRule implements TeleportRuleStrategy {

    @Override
    public int applyRule(int position) {
        return position;
    }
}
