package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.FetchItems;
import gg.bayes.challenge.rdbms.RdbmsProtocol.BoughtItemEntity;
import gg.bayes.challenge.rdbms.RecordBoughtItem;
import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class BoughtItemService {
    private static final Pattern BOUGHT_ITEM_PATTERN = Pattern.compile("npc_dota_hero_(\\w+) buys item item_(\\w+)");

    private final RecordBoughtItem recordBoughtItem;
    private final FetchItems fetchItems;

    public BoughtItemService(RecordBoughtItem recordBoughtItem, FetchItems fetchItems) {
        this.recordBoughtItem = recordBoughtItem;
        this.fetchItems = fetchItems;
    }

    public void parseAndRecord(long matchId, long timestamp, String line) {
        Matcher matcher = BOUGHT_ITEM_PATTERN.matcher(line);

        if (matcher.find()) {
            String boughtByHero = matcher.group(1);
            String boughtItem = matcher.group(2);

            recordBoughtItem.accept(matchId, timestamp, boughtByHero, boughtItem);
        }
    }

    public List<HeroItems> fetchItems(Long matchId, String heroName) {
        List<BoughtItemEntity> boughtItems = fetchItems.apply(matchId, heroName);

        return boughtItems.stream().map(this::asHeroItems).collect(Collectors.toList());
    }

    private HeroItems asHeroItems(BoughtItemEntity boughtItemEntity) {
        HeroItems heroItems = new HeroItems();
        heroItems.setItem(boughtItemEntity.getItemName());
        heroItems.setTimestamp(boughtItemEntity.getCreatedAt().toEpochMilli());
        return heroItems;
    }
}
