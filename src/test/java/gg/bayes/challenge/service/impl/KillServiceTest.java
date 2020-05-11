package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.RecordKill;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class KillServiceTest {
    private static final long MATCH_ID = 1;
    private static final long TIMESTAMP = 1000;

    @Mock
    RecordKill recordKill;

    @InjectMocks
    KillService killService;

    @Test
    void shouldParseAndRecordKill() {
        killService.parseAndRecord(MATCH_ID, TIMESTAMP, "[00:11:17.489] npc_dota_hero_snapfire is killed by npc_dota_hero_mars");

        then(recordKill).should().accept(MATCH_ID, TIMESTAMP, "snapfire", "mars");
    }
}