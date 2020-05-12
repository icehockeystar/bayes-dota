create table match
(
    id bigint auto_increment,

    primary key(id)
);

create table kill
(
    id bigint auto_increment,
    match_id bigint not null,
    killed_hero_name varchar(256) not null,
    killed_by_hero_name varchar(256) not null,
    created_at timestamp not null,

    primary key(id),
    foreign key(match_id) references match(id)
);

create table bought_item
(
    id bigint auto_increment,
    match_id bigint not null,
    bought_by_hero_name varchar(256) not null,
    item_name varchar(256) not null,
    created_at timestamp not null,

    primary key(id),
    foreign key(match_id) references match(id)
);

create table cast_spell
(
    id bigint auto_increment,
    match_id bigint not null,
    cast_by_hero_name varchar(256) not null,
    cast_name varchar(256) not null,
    cast_level int not null,
    created_at timestamp not null
);