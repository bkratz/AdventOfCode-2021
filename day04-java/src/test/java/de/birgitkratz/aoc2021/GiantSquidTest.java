package de.birgitkratz.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiantSquidTest {

    GiantSquid giantSquid;
    String testInput = """
            7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                        
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
                        
             3 15  0  2 22
             9 18 13 17  5
            19  8  7 25 23
            20 11 10 24  4
            14 21 16 12  6
                        
            14 21 17 24  4
            10 16 15  9 19
            18  8 23 26 20
            22 11 13  6  5
             2  0 12  3  7""";


    @Test
    void calculateFinalScoreOdFirstWinningBoard() {
        final var split = testInput.split("\n");
        final var inputStream = Arrays.stream(split);

        giantSquid = new GiantSquid(inputStream);

        var finalScore = giantSquid.calculateFinalScoreOfFirstWinningBoard();

        assertThat(finalScore).isEqualTo(4512);
    }

    @Test
    void calculateFinalScoreOfLastWinningBoard() {
        final var split = testInput.split("\n");
        final var inputStream = Arrays.stream(split);

        giantSquid = new GiantSquid(inputStream);
        var finalScore = giantSquid.calculateFinalScoreOfLastWinningBoard();

        assertThat(finalScore).isEqualTo(1924);
    }

    @Test
    void calculateFinalScoreOfFirstWinningBoardPuzzleInput() throws URISyntaxException, IOException {
        giantSquid = new GiantSquid(parsePuzzleInput());
        var finalScore = giantSquid.calculateFinalScoreOfFirstWinningBoard();

        assertThat(finalScore).isEqualTo(10680);
    }

    @Test
    void calculateFinalScoreOfLastWinningBoardPuzzleInput() throws URISyntaxException, IOException {
        giantSquid = new GiantSquid(parsePuzzleInput());
        var finalScore = giantSquid.calculateFinalScoreOfLastWinningBoard();

        assertThat(finalScore).isEqualTo(31892);
    }

    private Stream<String> parsePuzzleInput() throws URISyntaxException, IOException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource("puzzle_input.txt").toURI()));
    }
}