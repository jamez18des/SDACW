public class Blue implements Player {

    private static final int[] TRACK = {
            25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11,
            10, 9, 8, 7, 6, 5, 4, 3, 2, 1
    };

    private int index = 0;
    private final EndRuleStrategy endRule;

    public Blue(EndRuleStrategy endRule) {
        this.endRule = endRule;
    }

    @Override
    public String getName() { return "Blue"; }

    @Override
    public int getPosition() { return TRACK[index]; }

    @Override
    public boolean isHome() { return index == 0; }

    @Override
    public boolean isAtEnd() { return index == TRACK.length - 1; }

    public int getHomePosition() { return TRACK[0]; }

    public int getEndPosition() { return TRACK[TRACK.length - 1]; }

    public int peekPosition(int steps) {
        int newIndex = endRule.applyRule(index, steps, TRACK.length - 1);
        return TRACK[newIndex];
    }

    @Override
    public void advance(int steps) {
        index = endRule.applyRule(index, steps, TRACK.length - 1);
    }

    public void setPosition(int boardPosition) {
        for (int i = 0; i < TRACK.length; i++) {
            if (TRACK[i] == boardPosition) {
                index = i;
                return;
            }
        }
    }
}