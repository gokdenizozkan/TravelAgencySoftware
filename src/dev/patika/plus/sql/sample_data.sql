# PROPERTIES
INSERT INTO property (id, name, of_type) VALUES (1, 'Free Parking', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (2, 'Free Wifi', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (3, 'Swimming Pool', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (4, 'Fitness Center', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (5, 'Hotel Concierge', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (6, 'Spa', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (7, 'Room Service', 'hotel_facility');
INSERT INTO property (id, name, of_type) VALUES (8, 'Tv', 'room_facility');
INSERT INTO property (id, name, of_type) VALUES (9, 'Minibar', 'room_facility');
INSERT INTO property (id, name, of_type) VALUES (10, 'Gaming Console', 'room_facility');
INSERT INTO property (id, name, of_type) VALUES (11, 'Safe', 'room_facility');
INSERT INTO property (id, name, of_type) VALUES (12, 'Projection', 'room_facility');
INSERT INTO property (id, name, of_type) VALUES (13, 'Child', 'age_classifier');
INSERT INTO property (id, name, of_type) VALUES (14, 'Adult', 'age_classifier');
INSERT INTO property (id, name, of_type) VALUES (15, 'Single', 'room_type');
INSERT INTO property (id, name, of_type) VALUES (16, 'Double', 'room_type');
INSERT INTO property (id, name, of_type) VALUES (17, 'Suite', 'room_type');
INSERT INTO property (id, name, of_type) VALUES (18, 'Ultra Full Inclusive', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (19, 'Full Inclusive', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (20, 'Room Breakfast', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (21, 'Full Board', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (22, 'Half Board', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (23, 'Room and Bed', 'board_type');
INSERT INTO property (id, name, of_type) VALUES (24, 'Alcohol Excluded Full Board', 'board_type');


# HOTEL
INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('İrlanda Kır Evi Inn', 'Hakkari', 'Çukurca', 'İrlandalılar Mah. Romancılar Sok. No 55', 'hello@kirinn.tr', '+90 412 456 9874', 5, '1,2,3,4,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Mayflower Butik Otel', 'Hakkari', 'Çukurca', 'İrlandalılar Mah. Romancılar Sok. No 43', 'best@mayflowerhotel.com.tr', '+90 412 999 9874', 5, '1,2,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Otel Mavi Ay', 'Hakkari', 'Çukurca', 'İrlandalılar Mah. Güzide Sok. No 13', 'hey@maviay.com', '+90 123 444 5566', 5, '1,2,4,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Gökyüzü Butik Otel', 'Hakkari', 'Merkez', 'Hakkı Mah. Zehir Sok. No 3', 'rezervasyon@gokyuzubutikotel.com.tr', '+90 222 333 1234', 5, '1,2,3,4,5,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Sema Hotel', 'Hakkari', 'Merkez', 'Hakkı Mah. Gök Cad. No 7', 'reserve@sema.com.tr', '+90 345 777 1234', 5, '1,2,3,4,5,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Bulut Hotel', 'Çanakkale', 'Merkez', 'Boğaz Mah. Bulut Sok. No 666', 'comfy@cloudhotel.com.tr', '+90 666 666 6666', 5, '1,2,3,4,5,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Balina Mağarası Han', 'Çanakkale', 'Merkez', 'Boğaz Mah. Deve Sok. No 1', 'rezervasyon@balinamagarasihani.com.tr', '+90 987 654 3214', 4, '1,2,3,4,5,', '18,19,22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Hamidonun Yeri', 'Çanakkale', 'Merkez', 'Kara Mah. Denizliler Sok. No 5', 'hachen@hamidonunyeri.tr', '+90 642 214 4487', 5, '1,2,3,4,5,6,', '18,22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Kuzey Sahili Butik Otel', 'Sinop', 'Merkez', 'Deniz Mah. Ali Sok. No 654', 'konak@kuzeysahili.com.tr', '+90 748 859 9612', 5, '1,2,3,4,5,', '18,21,22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Konya Öğretmenevi', 'Konya', 'Karatay', 'Şems-i Tebrizi Mh. Mazhar Babalık Sk. No:3', 'info@konyaogretmenevi.com.tr', '+90 332 351 2265', 4, '1,2,', '21,22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Hotel Minerva Pazar', 'Rize', 'Pazar', 'Prof. Dr. Mehmet Haberal Caddesi No:9, 53300 Pazar/Rize', 'info@minervapazar.com', '+90 538 470 84 35', 4, '5,1,2,', '22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Patika Hostel', 'İstanbul', 'Ümraniye', 'Patikaoğlu Mah. Kodluyoruzkızı Sok. No 7', 'hostel@patika.dev', '+90 999 987 6543', 4, '1,2,3,4,5,6,7,', '18,22,');

INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('Kodluyoruz Motel', 'Kahramanmaraş', 'Merkez', 'Motorluyoruz Mah. Kod yazamıyoruz Sok.', 'info@kodluyoruz.com', '+90 875 46 2850', 4, '1,2,3,4,5,6,7,', '18,19,20,21,22,23,24,');

# SEASON
INSERT INTO season (hotel_id, name, start, end)
VALUES (1, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (2, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (3, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (4, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (5, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (6, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (7, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (8, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (9, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (10, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (11, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (12, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

INSERT INTO season (hotel_id, name, start, end)
VALUES (13, 'One Season to Rule Them All', '2021-01-01', '2034-01-01');

# ROOM
INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (1, 'Single', 1, 10, 30, '8,9,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (2, 'Single', 1, 10, 30, '8,9,10,11,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (3, 'Single', 1, 10, 30, '8,9,10,11,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (4, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (5, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (6, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (7, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (8, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (9, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (10, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (11, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (12, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (13, 'Single', 1, 10, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (1, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (2, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (3, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (4, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (5, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (6, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (7, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (8, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (9, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (10, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (11, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (12, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (13, 'Double', 1, 1, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (1, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (2, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (3, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (4, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (5, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (6, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (7, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (8, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (9, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (10, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (11, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (12, 'Suite', 1, 2, 30, '8,9,10,11,12,');

INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities)
VALUES (13, 'Suite', 1, 2, 30, '8,9,10,11,12,');

# PRICING
INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (1, 1, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (2, 2, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (3, 3, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (4, 4, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (5, 5, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (6, 6, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (7, 7, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (8, 8, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (9, 9, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (10, 10, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (11, 11, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (12, 12, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (13, 13, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (14, 1, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (15, 2, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (16, 3, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (17, 4, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (18, 5, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (19, 6, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (20, 7, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (21, 8, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (22, 9, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (23, 10, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (24, 11, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (25, 12, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (26, 13, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (27, 1, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (28, 2, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (29, 3, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (30, 4, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (31, 5, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (32, 6, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (33, 7, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (34, 8, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (35, 9, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (36, 10, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (37, 11, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (38, 12, 22, 666, 333);

INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child)
VALUES (39, 13, 22, 666, 333);


/*
INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types)
VALUES ('ad', 'il', 'ilçe', 'adres', 'mail', 'telefon', 4, 'facility', 'board');
*/