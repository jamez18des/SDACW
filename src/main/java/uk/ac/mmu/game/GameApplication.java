package uk.ac.mmu.game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.mmu.game.infrastructure.GameRunner;
@SpringBootApplication
public class GameApplication implements CommandLineRunner{private final GameRunner gameRunner;

    public GameApplication(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) {
        gameRunner.runAll();
    }

}
