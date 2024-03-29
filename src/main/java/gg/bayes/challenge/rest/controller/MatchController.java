package gg.bayes.challenge.rest.controller;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import gg.bayes.challenge.service.impl.BoughtItemService;
import gg.bayes.challenge.service.impl.CastSpellService;
import gg.bayes.challenge.service.impl.KillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;
    private final KillService killService;
    private final BoughtItemService boughtItemService;
    private final CastSpellService castSpellService;

    @Autowired
    public MatchController(MatchService matchService, KillService killService, BoughtItemService boughtItemService,
                           CastSpellService castSpellService) {
        this.matchService = matchService;
        this.killService = killService;
        this.boughtItemService = boughtItemService;
        this.castSpellService = castSpellService;
    }

    @PostMapping(consumes = "text/plain")
    public ResponseEntity<Long> ingestMatch(@RequestBody @NotNull @NotBlank String payload) {
        final Long matchId = matchService.ingestMatch(payload);
        return ResponseEntity.ok(matchId);
    }

    @GetMapping("{matchId}")
    public ResponseEntity<List<HeroKills>> getMatch(@PathVariable("matchId") Long matchId) {
        List<HeroKills> heroKills = killService.fetchHeroKills(matchId);

        if (heroKills.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(heroKills);
    }

    @GetMapping("{matchId}/{heroName}/items")
    public ResponseEntity<List<HeroItems>> getItems(@PathVariable("matchId") Long matchId,
                                                    @PathVariable("heroName") String heroName) {
        List<HeroItems> heroItems = boughtItemService.fetchItems(matchId, heroName);

        if (heroItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(heroItems);
    }

    @GetMapping("{matchId}/{heroName}/spells")
    public ResponseEntity<List<HeroSpells>> getSpells(@PathVariable("matchId") Long matchId,
                                                      @PathVariable("heroName") String heroName) {
        List<HeroSpells> heroSpells = castSpellService.fetchSpells(matchId, heroName);

        if (heroSpells.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(heroSpells);
    }

    @GetMapping("{matchId}/{heroName}/damage")
    public ResponseEntity<List<HeroDamage>> getDamage(@PathVariable("matchId") Long matchId,
                                                      @PathVariable("heroName") String heroName) {
        // TODO use match service to retrieve stats
        throw new NotImplementedException("should be implemented by the applicant");
    }
}
