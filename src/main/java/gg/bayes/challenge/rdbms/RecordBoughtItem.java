package gg.bayes.challenge.rdbms;

import gg.bayes.challenge.rdbms.RdbmsProtocol.BoughtItemEntity;
import gg.bayes.challenge.rdbms.RdbmsProtocol.MatchEntity;
import gg.bayes.challenge.rdbms.RdbmsRepositories.BoughtItemRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RecordBoughtItem {
    private final BoughtItemRepository boughtItemRepository;

    public RecordBoughtItem(BoughtItemRepository boughtItemRepository) {
        this.boughtItemRepository = boughtItemRepository;
    }


    public void accept(long matchId, long timestamp, String boughtByHero, String item) {
        BoughtItemEntity boughtItemEntity = new BoughtItemEntity();
        boughtItemEntity.setMatch(new MatchEntity(matchId));
        boughtItemEntity.setCreatedAt(Instant.ofEpochMilli(timestamp));
        boughtItemEntity.setBoughtByHeroName(boughtByHero);
        boughtItemEntity.setItemName(item);

        boughtItemRepository.save(boughtItemEntity);
    }
}
