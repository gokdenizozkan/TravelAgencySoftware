create table hotel
(
    id           int auto_increment
        primary key,
    name         varchar(255) not null,
    province     varchar(55)  null,
    state        varchar(55)  null,
    address      text         not null,
    email        varchar(255) not null,
    phone_number varchar(100) not null,
    stars        int          null,
    facilities   varchar(255) null,
    board_types  varchar(255) null
);

create table property
(
    id      int auto_increment
        primary key,
    name    varchar(255)                                           null,
    of_type enum ('hotel_facility', 'room_facility', 'board_type') null
);

create table season
(
    id       int auto_increment
        primary key,
    hotel_id int          not null,
    start    varchar(255) null,
    end      varchar(255) null,
    constraint season_hotel_id_fk
        foreign key (hotel_id) references hotel (id)
);

create table room
(
    id         int auto_increment
        primary key,
    hotel_id   int          null,
    of_type    varchar(255) null,
    beds       int          null,
    stock      int          null,
    size       int          null,
    facilities varchar(255) null,
    season_id  int          null,
    price      int          null,
    constraint room_hotel_id_fk
        foreign key (hotel_id) references hotel (id),
    constraint room_season_id_fk
        foreign key (season_id) references season (id)
);


