package dec22;

import framework.AOCParent;

import java.util.*;

public class Dec22 extends AOCParent {



    @Override
    public void part1() {
        List<SecretNumber> input = InputLoader.loadSecretNumbers();
        final long NUM_CYCLES = 2000L;

        long solution = input.stream().mapToLong(secretNumber -> {
            for (int i = 0; i < NUM_CYCLES; i++) {
                secretNumber.nextValue();
            }
            return secretNumber.getValue();
        }).sum();

        printSolution(solution);
    }

    @Override
    public void part2() {
        List<SecretNumber> input = InputLoader.loadSecretNumbers();
        final int NUM_CYCLES = 2000;

        Map<PriceDeltas, Long> masterDeltaPriceMap = new HashMap<>();

        for (SecretNumber secretNumber : input) {
            Map<PriceDeltas, Long> deltaPriceMap = new HashMap<>();
            List<Integer> deltas = new ArrayList<>();
            List<Integer> priceAfterDeltas = new ArrayList<>();

            // Compute price and delta after each cycle
            for (int i = 0; i < NUM_CYCLES; i++) {
                int currentPrice = secretNumber.getCurrentPrice();
                secretNumber.nextValue();
                int nextPrice = secretNumber.getCurrentPrice();
                int delta = nextPrice - currentPrice;

                deltas.add(delta);
                priceAfterDeltas.add(nextPrice);
            }

            // Compute delta pattern to price at end
            for (int i = 0; i <= NUM_CYCLES - 4; i++) {
                PriceDeltas deltaPattern = new PriceDeltas(
                        deltas.get(i),
                        deltas.get(i + 1),
                        deltas.get(i + 2),
                        deltas.get(i + 3)
                );


                // We've already seen this pattern for this buyer
                if (deltaPriceMap.containsKey(deltaPattern)) {
                    continue;
                }
                deltaPriceMap.put(deltaPattern, Long.valueOf(priceAfterDeltas.get(i+3)));
            }

            for (Map.Entry<PriceDeltas, Long> entry : deltaPriceMap.entrySet()) {
                masterDeltaPriceMap.putIfAbsent(entry.getKey(), 0L);
                masterDeltaPriceMap.put(entry.getKey(), masterDeltaPriceMap.get(entry.getKey()) + entry.getValue());
            }

        }


        long maxValue = 0;
        PriceDeltas maxPattern = null;
        for (Map.Entry<PriceDeltas, Long> entry : masterDeltaPriceMap.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxPattern = entry.getKey();
            }
        }

        printSolution("Pattern: " + maxPattern + ", bananas: " + maxValue);

    }
}
