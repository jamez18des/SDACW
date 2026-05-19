package uk.ac.mmu.game.domain;
public interface Player {
    String getName();
    int getPosition();
    int peekPosition(int roll);
    void advance(int roll);
    void setPosition(int position);
    boolean isHome();
    boolean isAtEnd();
    int getHomePosition();
    int getEndPosition();
    String getTrackDescription();

}
