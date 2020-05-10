package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.KillEntity;
import gg.bayes.challenge.rdbms.RdbmsProtocol.MatchEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.KillRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RecordKill {
    private final KillRepository killRepository;

    public RecordKill(KillRepository killRepository) {
        this.killRepository = killRepository;
    }

    public void accept(long matchId, long timestamp, String killedHero, String killedByHero) {
        KillEntity killEntity = new KillEntity();
        killEntity.setMatch(new MatchEntity(matchId));
        killEntity.setKilledHeroName(killedHero);
        killEntity.setKilledByHeroName(killedByHero);
        killEntity.setCreatedAt(Instant.ofEpochMilli(timestamp));

        killRepository.save(killEntity);
    }
}
