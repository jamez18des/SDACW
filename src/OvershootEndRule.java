public class OvershootEndRule implements EndRuleStrategy{

    @Override
    public int applyRule(int currentIndex, int steps, int endIndex) {
        int newIndex = currentIndex + steps;
        return newIndex >= endIndex ? endIndex : newIndex;
    }
}
