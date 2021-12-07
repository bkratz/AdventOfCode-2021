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
                    if (integerArrayList.size() - sum < sum) {
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

    public String findOxygenGeneratorRatingBinaryString(List<String> input) {
        String filterString = "";

        int index = 0;
        while (input.size() > 1) {
            final var integerArrayList =
                    input.stream().map(this::parseBinaryStringToArayOfInteger).toList();
            final var sum = sumAtIndex(integerArrayList, index);
            if (integerArrayList.size()-sum <= sum) {
                filterString += "1";
            } else {
                filterString += "0";
            }
            final String finalFilterString = filterString;
            input = input.stream().filter(i -> i.startsWith(finalFilterString)).toList();
            index = index + 1;
        }
        return input.get(0);
    }

    public String findCO2ScrubberRatingBinaryString(List<String> input) {
        String filterString = "";

        int index = 0;
        while (input.size() > 1) {
            final var integerArrayList =
                    input.stream().map(this::parseBinaryStringToArayOfInteger).toList();
            final var sum = sumAtIndex(integerArrayList, index);
            if (integerArrayList.size()-sum <= sum) {
                filterString += "0";
            } else {
                filterString += "1";
            }
            final String finalFilterString = filterString;
            input = input.stream().filter(i -> i.startsWith(finalFilterString)).toList();
            index = index + 1;
        }
        return input.get(0);
    }

    public int calculateLifeSupportRating(final Stream<String> input) {
        final var inputList = input.toList();
        final var oxygenGeneratorRatingString = findOxygenGeneratorRatingBinaryString(inputList);
        final var co2ScrubberRatingBinaryString = findCO2ScrubberRatingBinaryString(inputList);

        final var oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingString, 2);
        final var co2ScrubberRating = Integer.parseInt(co2ScrubberRatingBinaryString, 2);

        return oxygenGeneratorRating * co2ScrubberRating;
    }
}
