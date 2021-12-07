package de.birgitkratz.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiveTest {

    Dive dive = new Dive();
    Stream<String> inputStream = Stream.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");

    @Test
    void parseMovementCommands() {
        final var inputStream = Stream.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");

        assertThat(dive.parseMoveCommands(inputStream)).containsExactly(
                new MoveCommand("forward", 5),
                new MoveCommand("down", 5),
                new MoveCommand("forward", 8),
                new MoveCommand("up", 3),
                new MoveCommand("down", 8),
                new MoveCommand("forward", 2)
        );
    }

    @Test
    void applyForwardMovement_part1() {
        final var movement = new MoveCommand("forward", 5);
        final var startPosition = new Position(0, 0, 0);
        assertThat(dive.applyMovement_part1(movement, startPosition)).isEqualTo(new Position(5, 0, 0));
    }

    @Test
    void applyDownMovement_part1() {
        final var movement = new MoveCommand("down", 5);
        final var startPosition = new Position(0, 0, 0);
        assertThat(dive.applyMovement_part1(movement, startPosition)).isEqualTo(new Position(0, 5, 0));
    }

    @Test
    void applyUpMovement_part1() {
        final var movement = new MoveCommand("up", 5);
        final var startPosition = new Position(0, 10, 0);
        assertThat(dive.applyMovement_part1(movement, startPosition)).isEqualTo(new Position(0, 5, 0));
    }

    @Test
    void calculateFinalPosition_part1() {
        assertThat(dive.calculatPosition_part1(inputStream)).isEqualTo(new Position(15, 10, 0));
    }

    @Test
    void calculateFinalPositionForPuzzleInput_part1() throws URISyntaxException, IOException {
        final var puzzleInput = parsePuzzleInput();
        final var expected = new Position(2053, 1033, 0);
        assertThat(dive.calculatPosition_part1(puzzleInput)).isEqualTo(expected);
        System.out.println(expected.horizontal() * expected.depth());
    }


    @Test
    void applyForwardMovement_part2() {
        final var movement = new MoveCommand("forward", 5);
        final var startPosition = new Position(0, 0, 2);
        assertThat(dive.applyMovement_part2(movement, startPosition)).isEqualTo(new Position(5, 10, 2));
    }

    @Test
    void applyDownMovement_part2() {
        final var movement = new MoveCommand("down", 5);
        final var startPosition = new Position(0, 0, 0);
        assertThat(dive.applyMovement_part2(movement, startPosition)).isEqualTo(new Position(0, 0, 5));
    }

    @Test
    void applyUpMovement_part2() {
        final var movement = new MoveCommand("up", 5);
        final var startPosition = new Position(0, 0, 10);
        assertThat(dive.applyMovement_part2(movement, startPosition)).isEqualTo(new Position(0, 0, 5));
    }

    @Test
    void calculateFinalPosition_part2() {
        assertThat(dive.calculatPosition_part2(inputStream))
                .hasFieldOrPropertyWithValue("horizontal", 15)
                .hasFieldOrPropertyWithValue("depth", 60);
    }

    @Test
    void calculateFinalPositionForPuzzleInput_part2() throws URISyntaxException, IOException {
        final var puzzleInput = parsePuzzleInput();
        final var expected = new Position(2053, 1041589, 1033);
        assertThat(dive.calculatPosition_part2(puzzleInput)).isEqualTo(expected);
        System.out.println(expected.horizontal() * expected.depth());
    }

    private Stream<String> parsePuzzleInput() throws URISyntaxException, IOException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource("puzzle_input.txt").toURI()));
    }
}