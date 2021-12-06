package de.birgitkratz.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SonarSweepTest {

    SonarSweep sonarSweep = new SonarSweep();

    @Test
    void countIncreases() {
        final IntStream intStream = IntStream.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        assertThat(sonarSweep.countIncreases(intStream)).isEqualTo(7);
    }

    @Test
    void countIncreasesForPuzzleInput() throws IOException, URISyntaxException {
        assertThat(sonarSweep.countIncreases(parsePuzzleInput())).isEqualTo(1451);
//        System.out.println(sonarSweep.countIncreases(parsePuzzleInput()));
    }

    @Test
    void countThreeMeasurementIncreases() {
        final IntStream intStream = IntStream.of(607, 618, 618, 617, 647, 716, 769, 792);
        assertThat(sonarSweep.countThreeMeasurementIncreases(intStream)).isEqualTo(5);
    }

    @Test
    void countThreeMeasurementIncreasesForPuzzleInput() throws IOException, URISyntaxException {
        assertThat(sonarSweep.countThreeMeasurementIncreases(parsePuzzleInput())).isEqualTo(1395);
//        System.out.println(sonarSweep.countThreeMeasurementIncreases(parsePuzzleInput()));
    }

    private IntStream parsePuzzleInput() throws IOException, URISyntaxException {
        final var lines = Files.lines(Paths.get(getClass().getClassLoader().getResource("puzzle_input.txt").toURI()));
        return lines.mapToInt(Integer::valueOf);
    }
}