package _13_lambda_expressions._06_intermediate_and_terminal_operations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<String> ioNumberStream = Stream.of("I25", "I26", "I27", "I28");
        Stream<String> inNumberStream = Stream.of("N40", "N41", "I27", "N43");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);

        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());

        List<String> numbers = concatStream
                .distinct()
                .collect(Collectors.toList());

        // https://stackify.com/streams-guide-java-8/
    }
}
