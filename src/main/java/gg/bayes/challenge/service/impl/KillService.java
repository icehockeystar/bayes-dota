package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.FetchKills;
import gg.bayes.challenge.rdbms.RdbmsProtocol.KillEntity;
import gg.bayes.challenge.rdbms.RecordKill;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Component
public class KillService {
    private static final Pattern KILL_PATTERN = Pattern.compile("npc_dota_hero_(\\w+) is killed by npc_dota_hero_(\\w+)");

    private final RecordKill recordKill;
    private final FetchKills fetchKills;

    public KillService(RecordKill recordKill, FetchKills fetchKills) {
        this.recordKill = recordKill;
        this.fetchKills = fetchKills;
    }

    public void parseAndRecord(long matchId, long timestamp, String line) {
        Matcher matcher = KILL_PATTERN.matcher(line);

        if (matcher.find()) {
            String killedHero = matcher.group(1);
            String killedByHero = matcher.group(2);

            recordKill.accept(matchId, timestamp, killedHero, killedByHero);
        }
    }

    public List<HeroKills> fetchHeroKills(long matchId) {
        Map<String, List<KillEntity>> groupedHeros = fetchKills.apply(matchId).stream()
                .collect(groupingBy(KillEntity::getKilledByHeroName));

        return groupedHeros.keySet().stream()
                .map(heroName -> asHeroKills(groupedHeros, heroName)).collect(toList());
    }

    private HeroKills asHeroKills(Map<String, List<KillEntity>> groupedHeros, String heroName) {
        HeroKills heroKills = new HeroKills();
        heroKills.setHero(heroName);
        heroKills.setKills(groupedHeros.get(heroName).size());
        return heroKills;
    }
}
