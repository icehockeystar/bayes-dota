package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.BoughtItemEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.BoughtItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchItems {

    private final BoughtItemRepository boughtItemRepository;

    public FetchItems(BoughtItemRepository boughtItemRepository) {
        this.boughtItemRepository = boughtItemRepository;
    }

    public List<BoughtItemEntity> apply(Long matchId, String heroName) {
        return boughtItemRepository.findAllByMatchIdAndBoughtByHeroName(matchId, heroName);
    }
}
