package uk.ac.mmu.game.domain;
public interface Player {
    String getName();
    int getPosition();
    boolean isHome();
    boolean isAtEnd();
    void advance(int steps);
}
