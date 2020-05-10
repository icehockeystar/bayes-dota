package gg.bayes.challenge.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TimestampParserTest {
    private final TimestampParser timestampParser = new TimestampParser();

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("00:00:04.999", 4999),
                Arguments.of("00:10:48.230", 648230),
                Arguments.of("00:26:29.500", 1589500),
                Arguments.of("04:56:31.267", 17791267));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void shouldParseToTimestamp(String value, int expectedTimestamp) throws TimestampParserException {
        assertThat(timestampParser.parse(value)).isEqualTo(expectedTimestamp);
    }
}