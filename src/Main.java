import java.util.List;

public class Main {

    private static final int[] RED_TRACK = {
            1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25
    };
    private static final int[] BLUE_TRACK = {
            25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,
            10,9,8,7,6,5,4,3,2,1
    };
    private static final int[] RED_LARGE = {
            1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36
    };
    private static final int[] BLUE_LARGE = {
            31,32,33,34,35,36,25,26,27,28,29,30,19,20,21,22,23,24,
            13,14,15,16,17,18,7,8,9,10,11,12,1,2,3,4,5,6
    };
    private static final int[] YELLOW_LARGE = {
            36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,
            18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1
    };
    private static final int[] GREEN_LARGE = {
            6,5,4,3,2,1,12,11,10,9,8,7,18,17,16,15,14,13,
            24,23,22,21,20,19,30,29,28,27,26,25,36,35,34,33,32,31
    };

    public static void main(String[] args) {

        // Game 1: Overshoot, ignore hits, no wormholes, fixed dice
        System.out.println("=== Game 1: Basic game, 2 dice, fixed rolls ===");
        EndRuleStrategy overshoot = new OvershootEndRule();
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  overshoot),
                        new BasePlayer("Blue", BLUE_TRACK, overshoot)
                ),
                new Dice(new FixedDiceShaker(new int[]{12, 10, 12})),
                new Board(5, 5),
                new IgnoreHitRule(),
                new NoWormholeRule()
        ).play();

        System.out.println();

        // Game 2: Bounce back, fixed dice
        System.out.println("=== Game 2: Exact end (bounce back), fixed rolls ===");
        EndRuleStrategy bounce = new BounceBackEndRule();
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  bounce),
                        new BasePlayer("Blue", BLUE_TRACK, bounce)
                ),
                new Dice(new FixedDiceShaker(new int[]{9, 10, 10, 9, 12, 7, 2, 2})),
                new Board(5, 5),
                new IgnoreHitRule(),
                new NoWormholeRule()
        ).play();

        System.out.println();

        // Game 3: Stay hit, fixed dice
        System.out.println("=== Game 3: Stay-hit rule, fixed rolls ===");
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  overshoot),
                        new BasePlayer("Blue", BLUE_TRACK, overshoot)
                ),
                new Dice(new FixedDiceShaker(new int[]{12, 6, 6, 12, 5, 6})),
                new Board(5, 5),
                new StayHitRule(),
                new NoWormholeRule()
        ).play();

        System.out.println();

        // Game 4: Wormhole, fixed dice
        System.out.println("=== Game 4: Wormhole teleport, fixed rolls ===");
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  overshoot),
                        new BasePlayer("Blue", BLUE_TRACK, overshoot)
                ),
                new Dice(new FixedDiceShaker(new int[]{3, 2, 12, 10, 2, 3})),
                new Board(5, 5),
                new IgnoreHitRule(),
                new WormholeRule(new int[][]{{4, 9}, {23, 19}})
        ).play();

        System.out.println();

        // Game 5: Single die, random
        System.out.println("=== Game 5: Single die, random rolls ===");
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  overshoot),
                        new BasePlayer("Blue", BLUE_TRACK, overshoot)
                ),
                new Dice(new RandomDiceShaker(1)),
                new Board(5, 5),
                new IgnoreHitRule(),
                new NoWormholeRule()
        ).play();

        System.out.println();

        // Game 6: Two dice random, bounce + stay hit
        System.out.println("=== Game 6: Two dice random, bounce back + stay hit ===");
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",  RED_TRACK,  bounce),
                        new BasePlayer("Blue", BLUE_TRACK, bounce)
                ),
                new Dice(new RandomDiceShaker(2)),
                new Board(5, 5),
                new StayHitRule(),
                new NoWormholeRule()
        ).play();

        System.out.println();

        // Game 7: 4 players, large board, fixed dice
        System.out.println("=== Game 7: Large board, 4 players, basic rules, fixed rolls ===");
        new Game(
                java.util.List.of(
                        new BasePlayer("Red",    RED_LARGE,    overshoot),
                        new BasePlayer("Blue",   BLUE_LARGE,   overshoot),
                        new BasePlayer("Yellow", YELLOW_LARGE, overshoot),
                        new BasePlayer("Green",  GREEN_LARGE,  overshoot)
                ),
                new Dice(new FixedDiceShaker(new int[]{
                        7, 3, 8, 5,
                        7, 6, 8, 7,
                        6, 8, 2, 4,
                        4, 8, 5, 7,
                        8, 3, 9, 9,
                        7, 5, 7, 9
                })),
                new Board(6, 6),
                new IgnoreHitRule(),
                new NoWormholeRule()
        ).play();
    }
}