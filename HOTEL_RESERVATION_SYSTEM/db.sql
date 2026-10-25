DROP DATABASE IF EXISTS JAVA_hotel_reservation_system;
CREATE DATABASE IF NOT EXISTS JAVA_hotel_reservation_system;
USE JAVA_hotel_reservation_system;

CREATE TABLE rooms (
    room_id int not null,
    is_available boolean not null,
    category varchar(45) not null,
    price float not null,
    PRIMARY KEY(room_id)
);

CREATE TABLE reservations (
    reservation_id int not null auto_increment,
    room_num int not null,
    date date not null,
    full_name varchar(45) not null,
    PRIMARY KEY(reservation_id),
    FOREIGN KEY(room_num) REFERENCES rooms(room_id)
);

INSERT INTO rooms (room_id, is_available, category, price) VALUES (1, false, 'Standard', 50.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (2, true, 'Standard', 50.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (3, true, 'Standard', 50.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (4, true, 'Standard', 50.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (5, true, 'Standard', 50.00);

INSERT INTO rooms (room_id, is_available, category, price) VALUES (6, true, 'Deluxe', 100.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (7, true, 'Deluxe', 100.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (8, true, 'Deluxe', 100.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (9, true, 'Deluxe', 100.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (10, true, 'Deluxe', 100.00);

INSERT INTO rooms (room_id, is_available, category, price) VALUES (11, true, 'Suite', 300.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (12, true, 'Suite', 300.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (13, true, 'Suite', 300.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (14, true, 'Suite', 300.00);
INSERT INTO rooms (room_id, is_available, category, price) VALUES (15, true, 'Suite', 300.00);

INSERT INTO reservations (reservation_id, room_id, date, full_name) VALUES (1, 1, '2026-10-24' , 'Anna Smith');