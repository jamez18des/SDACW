import java.util.List;

public class Game {

    private final List<BasePlayer> players;
    private final Dice dice;
    private final Board board;
    private final HitRuleStrategy hitRule;
    private final TeleportRuleStrategy teleportRule;

    public Game(List<BasePlayer> players, Dice dice, Board board,
                HitRuleStrategy hitRule, TeleportRuleStrategy teleportRule) {
        this.players = players;
        this.dice = dice;
        this.board = board;
        this.hitRule = hitRule;
        this.teleportRule = teleportRule;
    }

    public void play() {
        System.out.println("Board: rows=" + board.getRows()
                + " columns=" + board.getColumns());
        System.out.println("Players: " + players.size());
        System.out.println();
        for (BasePlayer p : players) {
            System.out.println(p.getName() + " " + p.getTrackDescription());
        }
        System.out.println();
        System.out.println("Game State: Ready -> InPlay");
        System.out.println();

        int[] turnCounts = new int[players.size()];
        int totalTurns = 0;

        outer:
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                BasePlayer current = players.get(i);
                turnCounts[i]++;
                totalTurns++;

                int roll = dice.roll();
                String from = formatPosition(current.getPosition(),
                        current.getHomePosition(), current.getEndPosition());
                int peek = current.peekPosition(roll);

                System.out.println(current.getName() + " turn "
                        + turnCounts[i] + " rolls " + roll);
                System.out.println(current.getName() + " moves from " + from
                        + " to " + formatPosition(peek,
                        current.getHomePosition(), current.getEndPosition()));

                BasePlayer hitTarget = findHit(current, peek);

                if (hitTarget != null && peek != current.getEndPosition()) {
                    System.out.println(current.getName() + " hit "
                            + hitTarget.getName()
                            + " at position Position " + peek);
                    if (hitRule.allowMove(peek, hitTarget.getPosition())) {
                        current.advance(roll);
                    } else {
                        System.out.println(current.getName() + " moves from "
                                + peek + " to " + from);
                    }
                } else {
                    current.advance(roll);
                }

                if (!current.isAtEnd()) {
                    int teleported = teleportRule.applyRule(current.getPosition());
                    if (teleported != current.getPosition()) {
                        System.out.println(current.getName()
                                + " is teleported. " + current.getName()
                                + " moves from " + current.getPosition()
                                + " to " + teleported);
                        current.setPosition(teleported);
                    }
                }

                if (current.isAtEnd()) {
                    System.out.println(current.getName() + " wins in "
                            + turnCounts[i] + " turns. Total turns: "
                            + totalTurns + ".");
                    System.out.println("Game State: InPlay -> GameOver");
                    break outer;
                }
            }
        }
    }

    private BasePlayer findHit(BasePlayer current, int peekPos) {
        for (BasePlayer other : players) {
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