INSERT INTO roles (id, name)
VALUES (null, 'ROLE_USER'),
       (null, 'ROLE_ADMIN')
;

INSERT INTO users (username, email, password, enabled, name, surname)
VALUES ('user', 'user@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1, 'Jan', 'Kowalski'),
       ('admin', 'admin@mr.pl', '$2a$10$vATBTwreQmUc4NmmQCjr8.q3hyIYNx582a5ukVflpx0tOdoL7EvUq', 1, 'Anna', 'Kowalska')
;

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2)
;

INSERT INTO categories (name)
VALUES ('ubrania'),
       ('książki'),
       ('zabawki'),
       ('artykuły szkolne'),
       ('gry'),
       ('sprzęt'),
       ('inne')
;

INSERT INTO institutions (name, description)
VALUES ('Dbam o Zdrowie', 'Pomoc dzieciom z ubogich rodzin'),
       ('Dla dzieci', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej'),
       ('A kogo', 'Pomoc w wybudzaniu dzieci ze śpiączki'),
       ('Bez domu', 'Pomoc dla osób nie posiadających miejsca zamieszkania')
;

INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number)
VALUES ('Kielce', 'Parking przy Castoramie', now() + interval 2 day, now() + interval 2 day, 1, 'Wrzosowa', '25-200', 1, '+48 111 111 111'),
       ('Kielce', 'Parking przy Lidlu', now() + interval 4 day, now() + interval 4 day, 2, 'Popiełuszki', '25-200', 2, '+48 111 111 222'),
       ('Warszawa', 'Parking przy metrze Wilanowska', now() + interval 6 day, now() + interval 6 day, 3, 'Płocka', '00-100', 3, '+48 111 111 333'),
       ('Warszawa', 'Parking przy metrze Płocka ', now() + interval 8 day, now() + interval 2 day, 4, 'Wilanowska', '00-200', 4, '+48 111 111 444')
;

INSERT INTO donations_categories
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 3),
       (3, 4),
       (4, 4),
       (4, 5)
;