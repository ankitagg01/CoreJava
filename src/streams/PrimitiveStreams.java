package streams;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 *
 * 3 primitive streams:
 * - IntStream, specific functional interface IntSupplier
 * - LongStream, specific functional interface LongSupplier
 * - DoubleStream, specific functional interface DoubleSupplier
 * they have additional and math operations: range, rangeClosed, max, min, average, sum and summaryStatistics
 *
 */
public class PrimitiveStreams {
    public static void main(String[] args) {
        // Create IntStream
        IntStream oneToNine1 = IntStream.range(1,10);        // 1...9
        IntStream oneToNine2 = IntStream.iterate(1, x -> x + 1).limit(10);
        LongStream oneToTen = LongStream.rangeClosed(1,10); // 1...10, end value inclusive

        // Double stream
        DoubleStream fractions = DoubleStream.iterate(10, d -> d / 2);
        fractions.limit(3).forEach(System.out::println); // 10.0   5.0    2.5

        // Converting Stream<Integer> to IntStream
        List<Integer> ints = asList(1, 2, 3);
        IntStream intStream1 = ints.stream().mapToInt(x -> x);
        int sum = intStream1.sum();

        // Stream<Object> to IntStream
        Stream<String> objStream = Stream.of("lambda", "linq");
        IntStream intStream2 = objStream.mapToInt(s -> s.length());

        // Generate DoubleStream and convert to IntStream
        IntStream randomNumbers = DoubleStream.generate(Math::random).mapToInt(x -> (int)(x * 100));
        randomNumbers.limit(3).forEach(System.out::println);

        // Summarizing statics on stream (min, max, average, size and counts)
        IntSummaryStatistics stats = intStream2.summaryStatistics();
        if (stats.getCount() == 0)
            throw new RuntimeException();
        int deviation = stats.getMax() - stats.getMin();
    }
}