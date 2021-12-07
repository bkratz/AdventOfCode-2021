package de.birgitkratz.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryDiagnosticTest {

    BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
    Stream<String> testInput =
            Stream.of("00100",
                    "11110",
                    "10110",
                    "10111",
                    "10101",
                    "01111",
                    "00111",
                    "11100",
                    "10000",
                    "11001",
                    "00010",
                    "01010");
    @Test
    void calculateGammaRate() {
        assertThat(binaryDiagnostic.calculateGammeRateBinaryString(testInput)).isEqualTo("10110");
    }

    @Test
    void calculatePowerConsumption() {
        assertThat(binaryDiagnostic.calculatePowerConsumption(testInput)).isEqualTo(198);
    }

    @Test
    void calculateLifeSupportRating() {
        assertThat(binaryDiagnostic.calculateLifeSupportRating(testInput)).isEqualTo(230);
    }

    @Test
    void sumAtIndex() {
        final var ints = List.of(new int[] {0, 0, 1, 0, 0},
                new int[] {1, 1, 1, 1, 0},
                new int[] {1, 0, 1, 1, 0}
        );
        assertThat(binaryDiagnostic.sumAtIndex(ints, 0)).isEqualTo(2);
        assertThat(binaryDiagnostic.sumAtIndex(ints, 1)).isEqualTo(1);
    }

    @Test
    void findOxygenGeneratorRating() {
        assertThat(binaryDiagnostic.findOxygenGeneratorRatingBinaryString(testInput.toList())).isEqualTo("10111");
    }

    @Test
    void findCO2ScrubberRating() {
        assertThat(binaryDiagnostic.findCO2ScrubberRatingBinaryString(testInput.toList())).isEqualTo("01010");
    }

    @Test
    void calculateGammaRatePuzzleInput() throws URISyntaxException, IOException {
        assertThat(binaryDiagnostic.calculateGammeRateBinaryString(parsePuzzleInput())).isEqualTo("101000000110");
    }
    @Test
    void calculatePowerConsumptionPuzzleInput() throws URISyntaxException, IOException {
        assertThat(binaryDiagnostic.calculatePowerConsumption(parsePuzzleInput())).isEqualTo(3923414);
    }
    @Test
    void calculateLifeSupportRatingPuzzleInput() throws URISyntaxException, IOException {
        assertThat(binaryDiagnostic.calculateLifeSupportRating(parsePuzzleInput())).isEqualTo(5852595);
    }

    private Stream<String> parsePuzzleInput() throws URISyntaxException, IOException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource("puzzle_input.txt").toURI()));
    }

}