package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.CastSpellEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.CastSpellRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchCastSpells {
    private final CastSpellRepository castSpellRepository;

    public FetchCastSpells(CastSpellRepository castSpellRepository) {
        this.castSpellRepository = castSpellRepository;
    }


    public List<CastSpellEntity> apply(Long matchId, String heroName) {
        return castSpellRepository.findAllByMatchIdAndCastByHeroName(matchId, heroName);
    }
}
