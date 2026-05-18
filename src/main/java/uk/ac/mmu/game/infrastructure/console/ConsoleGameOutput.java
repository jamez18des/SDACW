package uk.ac.mmu.game.infrastructure.console;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.usecase.port.GameOutputPort;

@Component

public class ConsoleGameOutput implements GameOutputPort {

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void printBlankLine() {
        System.out.println();
    }}
