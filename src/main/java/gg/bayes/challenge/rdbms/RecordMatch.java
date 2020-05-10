package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.MatchEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.MatchRepository;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RecordMatch implements Supplier<Long> {
    private final MatchRepository matchRepository;

    public RecordMatch(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Long get() {
        MatchEntity matchEntity = matchRepository.save(new MatchEntity());
        return matchEntity.getId();
    }
}
