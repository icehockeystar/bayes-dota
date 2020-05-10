package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.RecordMatch;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {
    private final TimestampParser timestampParser = new TimestampParser();
    private final RecordMatch recordMatch;
    private final KillService killService;

    @Autowired
    public MatchServiceImpl(RecordMatch recordMatch, KillService killService) {
        this.recordMatch = recordMatch;
        this.killService = killService;
    }

    @Override
    public Long ingestMatch(String payload) {
        long matchId = recordMatch.get();
        Stream.of(payload.split("\\[")).forEach(line -> ingestLogLine(matchId, line));

        return matchId;
    }

    private void ingestLogLine(long matchId, String line) {
        try {
            long timestamp = parseTimestamp(line);
            killService.parseAndRecord(matchId, timestamp, line);
        } catch (TimestampParserException e) {
            log.info("Ignoring line '{}'", line);
        }

    }

    private long parseTimestamp(String line) throws TimestampParserException {
        int timestampEndIndex = line.indexOf(']');

        if (timestampEndIndex != -1) {
            String timestampValue = line.substring(0, timestampEndIndex);
            return timestampParser.parse(timestampValue);
        } else {
            log.warn("Can't find ] symbol in the string '{}'", line);
            throw new TimestampParserException();
        }
    }
}
