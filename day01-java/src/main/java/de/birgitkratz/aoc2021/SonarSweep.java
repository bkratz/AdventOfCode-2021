package de.birgitkratz.aoc2021;

import java.util.stream.IntStream;

public class SonarSweep {

    public int countIncreases(final IntStream intStream) {
        final int[] count = {0};
        intStream.reduce((a, b) -> {
                    if (b > a) {
                        count[0] = count[0] + 1;
                    }
                    return b;
                }
        );
        return count[0];
    }

    public int countThreeMeasurementIncreases(final IntStream intStream) {
        final var ints = intStream.toArray();
        final var threeMeasurementSumStream = IntStream
                .rangeClosed(0, ints.length - 3)
                .map(i -> java.util.Arrays.stream(ints, i, i + 3).sum()
                );

        return countIncreases(threeMeasurementSumStream);
    }
}
