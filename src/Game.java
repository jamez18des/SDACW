public class Game {
    private final Player red;
    private final Player blue;
    private final Dice dice;
    private final Board board;

    public Game(Dice dice) {
        this.red = new Red();
        this.blue = new Blue();
        this.dice = dice;
        this.board = new Board(5, 5);
    }

    public void play() {
        System.out.println("Board: rows=" + board.getRows() + " columns=" + board.getColumns());
        System.out.println("Players: Red and Blue");
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
            String redFrom = red.isHome() ? "Home (Position " + red.getPosition() + ")" : String.valueOf(red.getPosition());
            red.advance(redRoll);
            String redTo = red.isAtEnd() ? "End (Position " + red.getPosition() + ")" : String.valueOf(red.getPosition());
            System.out.println("Red turn " + redTurns + " rolls " + redRoll);
            System.out.println("Red moves from " + redFrom + " to " + redTo);

            if (red.isAtEnd()) {
                System.out.println("Red wins in " + redTurns + " turns. Total turns: " + totalTurns + ".");
                System.out.println("Game State: InPlay -> GameOver");
                return;
            }

            // Blue's turn
            blueTurns++;
            totalTurns++;
            int blueRoll = dice.roll();
            String blueFrom = blue.isHome() ? "Home (Position " + blue.getPosition() + ")" : String.valueOf(blue.getPosition());
            blue.advance(blueRoll);
            String blueTo = blue.isAtEnd() ? "End (Position " + blue.getPosition() + ")" : String.valueOf(blue.getPosition());
            System.out.println("Blue turn " + blueTurns + " rolls " + blueRoll);
            System.out.println("Blue moves from " + blueFrom + " to " + blueTo);

            if (blue.isAtEnd()) {
                System.out.println("Blue wins in " + blueTurns + " turns. Total turns: " + totalTurns + ".");
                System.out.println("Game State: InPlay -> GameOver");
                return;
            }
        }
    }
}
