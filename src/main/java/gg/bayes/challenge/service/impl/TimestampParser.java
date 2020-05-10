package gg.bayes.challenge.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

@Slf4j
public class TimestampParser {
    private final Pattern pattern = Pattern.compile("(\\d\\d):(\\d\\d):(\\d\\d)\\.(\\d\\d\\d)");

    long parse(String value) throws TimestampParserException {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String seconds = matcher.group(3);
            String millis = matcher.group(4);

            return (parseLong(hours) * 60 * 60 + parseLong(minutes) * 60 + parseLong(seconds)) * 1000 + parseLong(millis);
        } else {
            throw new TimestampParserException();
        }
    }
}
