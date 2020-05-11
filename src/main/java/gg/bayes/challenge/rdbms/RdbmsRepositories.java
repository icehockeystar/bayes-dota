package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.BoughtItemEntity;
import gg.bayes.challenge.rdbms.RdbmsProtocol.KillEntity;
import gg.bayes.challenge.rdbms.RdbmsProtocol.MatchEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

class RdbmsRepositories {
    private RdbmsRepositories() {
    }

    interface MatchRepository extends CrudRepository<MatchEntity, Long> {
    }

    interface KillRepository extends CrudRepository<KillEntity, Long> {
        List<KillEntity> findAllByMatchId(Long matchId);
    }

    interface BoughtItemRepository extends CrudRepository<BoughtItemEntity, Long> {

        List<BoughtItemEntity> findAllByMatchIdAndBoughtByHeroName(Long matchId, String boughtByHeroName);
    }
}
