package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.CastSpellEntity;
import gg.bayes.challenge.rdbms.RdbmsProtocol.MatchEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.CastSpellRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RecordCastSpell {
    private final CastSpellRepository castSpellRepository;

    public RecordCastSpell(CastSpellRepository castSpellRepository) {
        this.castSpellRepository = castSpellRepository;
    }

    public void accept(long matchId, long timestamp, String heroName, String castName, int castLevel) {
        CastSpellEntity castSpellEntity = new CastSpellEntity();
        castSpellEntity.setMatch(new MatchEntity(matchId));
        castSpellEntity.setCastByHeroName(heroName);
        castSpellEntity.setCastName(castName);
        castSpellEntity.setCastLevel(castLevel);
        castSpellEntity.setCreatedAt(Instant.ofEpochMilli(timestamp));

        castSpellRepository.save(castSpellEntity);
    }
}
