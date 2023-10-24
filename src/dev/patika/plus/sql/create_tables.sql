create table hotel
(
    id              int auto_increment,
    name            varchar(255) not null,
    province        varchar(55)  null,
    state           varchar(55)  null,
    address         text         not null,
    email           varchar(255) not null,
    phone_number    varchar(100) not null,
    stars           int          null,
    facilities      varchar(255) null,
    board_types     varchar(255) null,
    all_rooms       varchar(255) null,
    available_rooms varchar(255) null,
    constraint hotel_pk
        primary key (id)
);

create table room
(
    id         int auto_increment
        primary key,
    room_type  varchar(255) null,
    beds       int          null,
    size       int          null,
    facilities varchar(255) null
);

create table season
(
    hotel_id                   int          not null,
    start                      timestamp    null,
    end                        timestamp    null,
    adult_board_type_prices varchar(255) null,
    child_board_type_prices varchar(255) null,
    constraint season_pk
        primary key (hotel_id),
    constraint season_hotel_id_fk
        foreign key (hotel_id) references hotel (id)
);
