package gg.bayes.challenge.rdbms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

public class RdbmsProtocol {
    private RdbmsProtocol() {
    }

    @Data
    @Entity
    @Table(name = "match")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchEntity {
        @Id
        @GeneratedValue
        private long id;
    }

    @Data
    @Entity
    @Table(name = "kill")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KillEntity {
        @Id
        @GeneratedValue
        private long id;

        @ManyToOne
        @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
        private MatchEntity match;

        @Column(name = "killed_hero_name", nullable = false)
        private String killedHeroName;

        @Column(name = "killed_by_hero_name", nullable = false)
        private String killedByHeroName;

        @Column(name = "created_at", nullable = false)
        private Instant createdAt;
    }
}
