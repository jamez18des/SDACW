public interface Player {
    String getName();
    int getPosition();
    boolean isHome();
    boolean isAtEnd();
    void advance(int steps);
}
