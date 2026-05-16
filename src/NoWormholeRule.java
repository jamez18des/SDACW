public class NoWormholeRule implements TeleportRuleStrategy {

    @Override
    public int applyRule(int position) {
        return position;
    }
}
