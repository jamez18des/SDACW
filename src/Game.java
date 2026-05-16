public class Game {

    private final Red red;
    private final Blue blue;
    private final Dice dice;
    private final Board board;
    private final HitRuleStrategy hitRule;
    private final TeleportRuleStrategy teleportRule;

    public Game(Dice dice, EndRuleStrategy endRule,
                HitRuleStrategy hitRule, TeleportRuleStrategy teleportRule) {
        this.red = new Red(endRule);
        this.blue = new Blue(endRule);
        this.dice = dice;
        this.board = new Board(5, 5);
        this.hitRule = hitRule;
        this.teleportRule = teleportRule;
    }

    public void play() {
        System.out.println("Board: rows=" + board.getRows()
                + " columns=" + board.getColumns());
        System.out.println("Players: 2");
        System.out.println();
        System.out.println("Red " + getTrack(new int[]{
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
                16,17,18,19,20,21,22,23,24,25}));
        System.out.println("Blue " + getTrack(new int[]{
                25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,
                10,9,8,7,6,5,4,3,2,1}));
        System.out.println();
        System.out.println("Game State: Ready -> InPlay");
        System.out.println();

        int redTurns = 0;
        int blueTurns = 0;
        int totalTurns = 0;

        while (true) {

            // Red's turn
            redTurns++;
            totalTurns++;
            int redRoll = dice.roll();
            String redFrom = formatPosition(red.getPosition(), red.getHomePosition(), red.getEndPosition());
            int redPeek = red.peekPosition(redRoll);
            System.out.println("Red turn " + redTurns + " rolls " + redRoll);
            System.out.println("Red moves from " + redFrom + " to "
                    + formatPosition(redPeek, red.getHomePosition(), red.getEndPosition()));

            if (redPeek == blue.getPosition()) {
                System.out.println("Red hit Blue at position Position " + redPeek);
                if (hitRule.allowMove(redPeek, blue.getPosition())) {
                    red.advance(redRoll);
                } else {
                    System.out.println("Red moves from " + redPeek + " to " + redFrom);
                }
            } else {
                red.advance(redRoll);
            }

            if (!red.isAtEnd()) {
                int teleported = teleportRule.applyRule(red.getPosition());
                if (teleported != red.getPosition()) {
                    System.out.println("Red is teleported. Red moves from "
                            + red.getPosition() + " to " + teleported);
                    // set red position - add setPosition to Red too
                }
            }

            if (red.isAtEnd()) {
                System.out.println("Red wins in " + redTurns
                        + " turns. Total turns: " + totalTurns + ".");
                System.out.println("Game State: InPlay -> GameOver");
                return;
            }

            // Blue's turn
            blueTurns++;
            totalTurns++;
            int blueRoll = dice.roll();
            String blueFrom = formatPosition(blue.getPosition(), blue.getHomePosition(), blue.getEndPosition());
            int bluePeek = blue.peekPosition(blueRoll);
            System.out.println("Blue turn " + blueTurns + " rolls " + blueRoll);
            System.out.println("Blue moves from " + blueFrom + " to "
                    + formatPosition(bluePeek, blue.getHomePosition(), blue.getEndPosition()));

            if (bluePeek == red.getPosition()) {
                System.out.println("Blue hit Red at position Position " + bluePeek);
                if (hitRule.allowMove(bluePeek, red.getPosition())) {
                    blue.advance(blueRoll);
                } else {
                    System.out.println("Blue moves from " + bluePeek + " to " + blueFrom);
                }
            } else {
                blue.advance(blueRoll);
            }

            if (!blue.isAtEnd()) {
                int teleported = teleportRule.applyRule(blue.getPosition());
                if (teleported != blue.getPosition()) {
                    System.out.println("Blue is teleported. Blue moves from "
                            + blue.getPosition() + " to " + teleported);
                    blue.setPosition(teleported);
                }
            }

            if (blue.isAtEnd()) {
                System.out.println("Blue wins in " + blueTurns
                        + " turns. Total turns: " + totalTurns + ".");
                System.out.println("Game State: InPlay -> GameOver");
                return;
            }
        }
    }

    private String formatPosition(int pos, int home, int end) {
        if (pos == home) return "Home (Position " + pos + ")";
        if (pos == end) return "End (Position " + pos + ")";
        return String.valueOf(pos);
    }

    private String getTrack(int[] positions) {
        StringBuilder sb = new StringBuilder();
        sb.append("Home (Position ").append(positions[0]).append(")");
        for (int i = 1; i < positions.length - 1; i++) {
            sb.append(", ").append(positions[i]);
        }
        sb.append(", End (Position ").append(positions[positions.length - 1]).append(")");
        return sb.toString();
    }
}