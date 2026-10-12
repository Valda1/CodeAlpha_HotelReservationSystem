rooms.sql
CREATE TABLE rooms (
    room_id int not null,
    isAvailable boolean not null,
    category varchar(45) not null,
    price int not null,
    PRIMARY KEY(room_id)
);