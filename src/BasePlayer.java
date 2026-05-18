import java.util.Arrays;

public class BasePlayer implements Player {

    private static final int HOME_INDEX = 0;

    private final String name;
    private final int[] track;
    private final int endIndex;
    private final EndRuleStrategy endRule;
    private int currentIndex = 0;

    public BasePlayer(String name, int[] track, EndRuleStrategy endRule) {
        this.name = name;
        this.track = track;
        this.endIndex = track.length - 1;
        this.endRule = endRule;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getPosition() { return track[currentIndex]; }

    @Override
    public boolean isHome() { return currentIndex == HOME_INDEX; }

    @Override
    public boolean isAtEnd() { return currentIndex == endIndex; }

    public int getHomePosition() { return track[HOME_INDEX]; }

    public int getEndPosition() { return track[endIndex]; }

    public int peekPosition(int steps) {
        int newIndex = endRule.applyRule(currentIndex, steps, endIndex);
        return track[newIndex];
    }

    @Override
    public void advance(int steps) {
        currentIndex = endRule.applyRule(currentIndex, steps, endIndex);
    }

    public void setPosition(int boardPosition) {
        for (int i = 0; i < track.length; i++) {
            if (track[i] == boardPosition) {
                currentIndex = i;
                return;
            }
        }
    }

    public String getTrackDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Home (Position ").append(track[HOME_INDEX]).append(")");
        for (int i = 1; i < endIndex; i++) {
            sb.append(", ").append(track[i]);
        }
        sb.append(", End (Position ").append(track[endIndex]).append(")");
        return sb.toString();
    }
}
