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

create table pricing
(
    room_id       int null,
    season_id     int null,
    board_type_id int null,
    price_adult   int null,
    price_child   int null
);

create table property
(
    id      int auto_increment
        primary key,
    name    varchar(255)                                                                          null,
    of_type enum ('hotel_facility', 'room_facility', 'board_type', 'room_type', 'age_classifier') null
);

create table reservation
(
    id                   int auto_increment
        primary key,
    hotel_id             int          not null,
    room_id              int          not null,
    board_type_id        int          not null,
    start_date           varchar(255) not null,
    end_date             varchar(255) not null,
    adult_guest_count    int          not null,
    child_guest_count    int          not null,
    total_price          int          not null,
    contact_name         varchar(255) not null,
    contact_phone_number varchar(255) not null,
    contact_email        varchar(255) not null
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
    facilities varchar(255) null
);

create index room_hotel_id_fk
    on room (hotel_id);

create table room_availability
(
    room_id int          not null,
    amount  int          null,
    date    varchar(255) null
);

create table season
(
    id       int auto_increment
        primary key,
    hotel_id int          not null,
    start    varchar(255) null,
    end      varchar(255) null,
    name     varchar(255) null
);

create index season_hotel_id_fk
    on season (hotel_id);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(255)               not null,
    password varchar(255)               not null,
    of_type  enum ('admin', 'employee') null
);

