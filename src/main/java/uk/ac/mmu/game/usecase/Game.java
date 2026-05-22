package uk.ac.mmu.game.usecase;

import uk.ac.mmu.game.domain.Board;
import uk.ac.mmu.game.domain.Dice;
import uk.ac.mmu.game.domain.Player;
import uk.ac.mmu.game.domain.rules.HitRuleStrategy;
import uk.ac.mmu.game.domain.rules.TeleportRuleStrategy;
import uk.ac.mmu.game.usecase.port.GameOutputPort;

import java.util.List;

public class Game {

    private final List<Player> players;
    private final Dice dice;
    private final Board board;
    private final HitRuleStrategy hitRule;
    private final TeleportRuleStrategy teleportRule;
    private final GameOutputPort output;

    public Game(List<Player> players, Dice dice, Board board,
                HitRuleStrategy hitRule, TeleportRuleStrategy teleportRule, GameOutputPort output) {
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.hitRule = hitRule;
        this.teleportRule = teleportRule;
        this.output = output;
    }
    // Prints board info and player tracks before the game starts
    public void play() {
        output.printLine("Board: rows=" + board.getRows() + " columns=" + board.getColumns());
        output.printLine("Players: " + players.size());
        output.printBlankLine();
        for (Player p : players) {
            output.printLine(p.getName() + " " + p.getTrackDescription());
        }
        output.printBlankLine();
        output.printLine("Game State: Ready -> InPlay");
        output.printBlankLine();

        int[] turnCounts = new int[players.size()];
        int totalTurns = 0;
        // Main game loop — continues until a player reaches the end
        outer:
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                Player current = players.get(i);
                turnCounts[i]++;
                totalTurns++;
                // Roll dice and calculate where the player would land
                int roll = dice.roll();
                String from = formatPosition(current.getPosition(), current.getHomePosition(), current.getEndPosition());
                int peek = current.peekPosition(roll);

                output.printLine(current.getName() + " turn " + turnCounts[i] + " rolls " + roll);
                output.printLine(current.getName() + " moves from " + from + " to " + formatPosition(peek, current.getHomePosition(), current.getEndPosition()));

                // Check if landing position is occupied by another player
                Player hitTarget = findHit(current, peek);
                // Apply hit rule — either block move or allow it
                if (hitTarget != null && peek != current.getEndPosition()) {
                    output.printLine(current.getName() + " hit " + hitTarget.getName() + " at position Position " + peek);
                    if (hitRule.allowMove(peek, hitTarget.getPosition())) {
                        current.advance(roll);
                    } else {
                        output.printLine(current.getName() + " moves from " + peek + " to " + from);
                    }
                } else {
                    current.advance(roll);
                }
                // Check for wormhole teleportation after moving

                if (!current.isAtEnd()) {
                    int teleported = teleportRule.applyRule(current.getPosition());
                    if (teleported != current.getPosition()) {
                        output.printLine(current.getName() + " is teleported. " + current.getName() + " moves from " + current.getPosition() + " to " + teleported);
                        current.setPosition(teleported);
                    }
                }
                // Check if the current player has won
                if (current.isAtEnd()) {
                    output.printLine(current.getName() + " wins in " + turnCounts[i] + " turns. Total turns: " + totalTurns + ".");
                    output.printLine("Game State: InPlay -> GameOver");
                    break outer;
                }
            }
        }
    }

    private Player findHit(Player current, int peekPos) {
        for (Player other : players) {
            if (other != current && other.getPosition() == peekPos) {
                return other;
            }
        }
        return null;
    }

    private String formatPosition(int pos, int home, int end) {
        if (pos == home) return "Home (Position " + pos + ")";
        if (pos == end) return "End (Position " + pos + ")";
        return String.valueOf(pos);
    }
}