public class StayHitRule implements HitRuleStrategy {
    @Override
public boolean allowMove(int newPosition, int otherPlayerPosition) {
    return newPosition != otherPlayerPosition;
}
}
