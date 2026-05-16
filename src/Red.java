public class Red implements Player{private static final int[] TRACK = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25
};

    private int index = 0;

    @Override
    public String getName() {
        return "Red";
    }

    @Override
    public int getPosition() {
        return TRACK[index];
    }

    @Override
    public boolean isHome() {
        return index == 0;
    }

    @Override
    public boolean isAtEnd() {
        return index == TRACK.length - 1;
    }

    @Override
    public void advance(int steps) {
        index = index + steps;
        if (index >= TRACK.length) {
            index = TRACK.length - 1;
        }
    }
}
