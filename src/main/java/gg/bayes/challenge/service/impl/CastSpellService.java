package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.FetchCastSpells;
import gg.bayes.challenge.rdbms.RdbmsProtocol.CastSpellEntity;
import gg.bayes.challenge.rdbms.RecordCastSpell;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Component
public class CastSpellService {
    private static final Pattern CAST_SPELL_PATTERN =
            Pattern.compile("npc_dota_hero_(\\w+) casts ability (\\w+) \\(lvl (\\d+)\\) on");
    private final RecordCastSpell recordCastSpell;
    private final FetchCastSpells fetchCastSpells;

    public CastSpellService(RecordCastSpell recordCastSpell, FetchCastSpells fetchCastSpells) {
        this.recordCastSpell = recordCastSpell;
        this.fetchCastSpells = fetchCastSpells;
    }

    public void parseAndRecord(long matchId, long timestamp, String line) {
        Matcher matcher = CAST_SPELL_PATTERN.matcher(line);

        if (matcher.find()) {
            String heroName = matcher.group(1);
            String castName = matcher.group(2);
            String castLevel = matcher.group(3);

            recordCastSpell.accept(matchId, timestamp, heroName, castName, parseInt(castLevel));
        }
    }

    public List<HeroSpells> fetchSpells(Long matchId, String heroName) {
        Map<String, List<CastSpellEntity>> spellsGroupedByName = fetchCastSpells.apply(matchId, heroName).stream()
                .collect(groupingBy(CastSpellEntity::getCastName));

        return spellsGroupedByName.keySet().stream().map(spellName -> asHeroSpells(spellsGroupedByName, spellName))
                .collect(toList());
    }

    private HeroSpells asHeroSpells(Map<String, List<CastSpellEntity>> spellsGroupedByName, String spellName) {
        HeroSpells heroSpells = new HeroSpells();
        heroSpells.setSpell(spellName);
        heroSpells.setCasts(spellsGroupedByName.get(spellName).size());
        return heroSpells;
    }
}
