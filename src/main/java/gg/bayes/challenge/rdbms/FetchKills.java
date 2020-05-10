package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.KillEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

import static gg.bayes.challenge.rdbms.RdbmsRepositories.KillRepository;

@Component
public class FetchKills implements Function<Long, List<KillEntity>> {
    private final KillRepository killRepository;

    public FetchKills(KillRepository killRepository) {
        this.killRepository = killRepository;
    }

    public List<KillEntity> apply(Long matchId) {
        return killRepository.findAllByMatchId(matchId);
    }
}
