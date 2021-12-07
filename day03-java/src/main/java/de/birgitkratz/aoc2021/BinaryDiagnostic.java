package de.birgitkratz.aoc2021;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BinaryDiagnostic {

    public String calculateGammeRateBinaryString(final Stream<String> input) {
        final var integerArrayList = input.map(this::parseBinaryStringToArayOfInteger).toList();

        return IntStream.range(0, integerArrayList.get(0).length)
                .mapToObj(i -> {
                    final var sum = sumAtIndex(integerArrayList, i);
                    if (sum >= integerArrayList.size() / 2) {
                        return "1";
                    }
                    return "0";
                })
                .collect(Collectors.joining());
    }

    private int[] parseBinaryStringToArayOfInteger(final String s) {
        final var parsedInteger = Integer.parseInt(s, 2);
        return IntStream.range(0, s.length())
                .map(i -> s.length() - i - 1)
                .map(i -> (parsedInteger >> i) & 1)
                .toArray();
    }

    int sumAtIndex(final List<int[]> arrays, final int i) {
        return arrays.stream()
                .mapToInt(v -> v[i]).sum();
    }

    public int calculatePowerConsumption(final Stream<String> input) {
        final var gammaRateBinaryString = calculateGammeRateBinaryString(input);
        final var gammaRate = Integer.parseInt(gammaRateBinaryString, 2);

        // find value to XOR gammaRate with
        int pow = (int)(Math.pow(2, gammaRateBinaryString.length()) -1);

        // XOR
        final int epsilonRate = gammaRate ^ pow;

        return gammaRate * epsilonRate;
    }
}
