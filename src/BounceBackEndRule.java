public class BounceBackEndRule implements  EndRuleStrategy{

    @Override
    public int applyRule(int currentIndex, int steps, int endIndex) {
        int newIndex = currentIndex + steps;
        if (newIndex > endIndex) {
            int overshoot = newIndex - endIndex;
            return endIndex - overshoot;
        }
        return newIndex;
    }

}
