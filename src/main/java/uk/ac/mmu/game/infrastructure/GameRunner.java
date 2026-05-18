package uk.ac.mmu.game.infrastructure;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.domain.Board;
import uk.ac.mmu.game.domain.Dice;
import uk.ac.mmu.game.domain.rules.EndRuleStrategy;
import uk.ac.mmu.game.infrastructure.dice.FixedDiceShaker;
import uk.ac.mmu.game.infrastructure.dice.RandomDiceShaker;
import uk.ac.mmu.game.infrastructure.player.BasePlayer;
import uk.ac.mmu.game.infrastructure.rules.BounceBackEndRule;
import uk.ac.mmu.game.infrastructure.rules.IgnoreHitRule;
import uk.ac.mmu.game.infrastructure.rules.NoWormholeRule;
import uk.ac.mmu.game.infrastructure.rules.OvershootEndRule;
import uk.ac.mmu.game.infrastructure.rules.StayHitRule;
import uk.ac.mmu.game.infrastructure.rules.WormholeRule;
import uk.ac.mmu.game.usecase.Game;
import uk.ac.mmu.game.usecase.port.GameOutputPort;

import java.util.List;

@Component
public class GameRunner {
    private static final int[] RED_SMALL   = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    private static final int[] BLUE_SMALL  = {25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private static final int[] RED_LARGE   = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
    private static final int[] BLUE_LARGE  = {31,32,33,34,35,36,25,26,27,28,29,30,19,20,21,22,23,24,13,14,15,16,17,18,7,8,9,10,11,12,1,2,3,4,5,6};
    private static final int[] YELLOW_LARGE = {36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    private static final int[] GREEN_LARGE  = {6,5,4,3,2,1,12,11,10,9,8,7,18,17,16,15,14,13,24,23,22,21,20,19,30,29,28,27,26,25,36,35,34,33,32,31};

    private static final Board SMALL_BOARD = new Board(5, 5);
    private static final Board LARGE_BOARD = new Board(6, 6);

    private final GameOutputPort output;

    public GameRunner(GameOutputPort output) {
        this.output = output;
    }

    public void runAll() {
        runGame1();
        output.printBlankLine();
        runGame2();
        output.printBlankLine();
        runGame3();
        output.printBlankLine();
        runGame4();
        output.printBlankLine();
        runGame5();
        output.printBlankLine();
        runGame6();
        output.printBlankLine();
        runGame7();
    }

    private void runGame1() {
        output.printLine("=== Game 1: Basic game, 2 dice, fixed rolls ===");
        EndRuleStrategy endRule = new OvershootEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new FixedDiceShaker(new int[]{12, 10, 12})),
                SMALL_BOARD, new IgnoreHitRule(), new NoWormholeRule(), output
        ).play();
    }

    private void runGame2() {
        output.printLine("=== Game 2: Exact end (bounce back), fixed rolls ===");
        EndRuleStrategy endRule = new BounceBackEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new FixedDiceShaker(new int[]{9, 10, 10, 9, 12, 7, 2, 2})),
                SMALL_BOARD, new IgnoreHitRule(), new NoWormholeRule(), output
        ).play();
    }

    private void runGame3() {
        output.printLine("=== Game 3: Stay-hit rule, fixed rolls ===");
        EndRuleStrategy endRule = new OvershootEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new FixedDiceShaker(new int[]{12, 6, 6, 12, 5, 6})),
                SMALL_BOARD, new StayHitRule(), new NoWormholeRule(), output
        ).play();
    }

    private void runGame4() {
        output.printLine("=== Game 4: Wormhole teleport, fixed rolls ===");
        EndRuleStrategy endRule = new OvershootEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new FixedDiceShaker(new int[]{3, 2, 12, 10, 2, 3})),
                SMALL_BOARD, new IgnoreHitRule(), new WormholeRule(new int[][]{{4, 9}, {23, 19}}), output
        ).play();
    }

    private void runGame5() {
        output.printLine("=== Game 5: Single die, random rolls ===");
        EndRuleStrategy endRule = new OvershootEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new RandomDiceShaker(1)),
                SMALL_BOARD, new IgnoreHitRule(), new NoWormholeRule(), output
        ).play();
    }

    private void runGame6() {
        output.printLine("=== Game 6: Two dice random, bounce back + stay hit ===");
        EndRuleStrategy endRule = new BounceBackEndRule();
        new Game(
                List.of(new BasePlayer("Red", RED_SMALL, endRule), new BasePlayer("Blue", BLUE_SMALL, endRule)),
                new Dice(new RandomDiceShaker(2)),
                SMALL_BOARD, new StayHitRule(), new NoWormholeRule(), output
        ).play();
    }

    private void runGame7() {
        output.printLine("=== Game 7: Large board, 4 players, basic rules, fixed rolls ===");
        EndRuleStrategy endRule = new OvershootEndRule();
        new Game(
                List.of(
                        new BasePlayer("Red",    RED_LARGE,    endRule),
                        new BasePlayer("Blue",   BLUE_LARGE,   endRule),
                        new BasePlayer("Yellow", YELLOW_LARGE, endRule),
                        new BasePlayer("Green",  GREEN_LARGE,  endRule)
                ),
                new Dice(new FixedDiceShaker(new int[]{7,3,8,5,7,6,8,7,6,8,2,4,4,8,5,7,8,3,9,9,7,5,7,9})),
                LARGE_BOARD, new IgnoreHitRule(), new NoWormholeRule(), output
        ).play();
    }
}
