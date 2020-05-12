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

    @Data
    @Entity
    @Table(name = "bought_item")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoughtItemEntity {
        @Id
        @GeneratedValue
        private long id;

        @ManyToOne
        @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
        private MatchEntity match;

        @Column(name = "bought_by_hero_name", nullable = false)
        private String boughtByHeroName;

        @Column(name = "item_name", nullable = false)
        private String itemName;

        @Column(name = "created_at", nullable = false)
        private Instant createdAt;
    }

    @Data
    @Entity
    @Table(name = "cast_spell")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CastSpellEntity {
        @Id
        @GeneratedValue
        private long id;

        @ManyToOne
        @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
        private MatchEntity match;

        @Column(name = "cast_by_hero_name", nullable = false)
        private String castByHeroName;

        @Column(name = "cast_name", nullable = false)
        private String castName;

        @Column(name = "cast_level", nullable = false)
        private int castLevel;

        @Column(name = "created_at", nullable = false)
        private Instant createdAt;
    }
}
