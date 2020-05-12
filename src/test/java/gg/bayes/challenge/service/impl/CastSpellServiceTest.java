package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rdbms.RecordCastSpell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CastSpellServiceTest {
    private static final long MATCH_ID = 1;
    private static final long TIMESTAMP = 1000;

    @Mock
    RecordCastSpell recordCastSpell;

    @InjectMocks
    CastSpellService castSpellService;

    @Test
    void shouldParseAndRecordCastSpell() {
        castSpellService.parseAndRecord(MATCH_ID, TIMESTAMP,
                "[00:21:11.244] npc_dota_hero_abyssal_underlord casts ability abyssal_underlord_firestorm (lvl 4) on dota_unknown\n");

        then(recordCastSpell).should().accept(MATCH_ID, TIMESTAMP, "abyssal_underlord", "abyssal_underlord_firestorm", 4);
    }
}