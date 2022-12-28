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

INSERT INTO donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id)
VALUES ('Kielce', 'Parking przy Castoramie', now() + interval 2 day, now() + interval 2 day, 1, 'Wrzosowa', '25-200', 1),
       ('Kielce', 'Parking przy Lidlu', now() + interval 4 day, now() + interval 4 day, 2, 'Popiełuszki', '25-200', 2),
       ('Warszawa', 'Parking przy metrze Wilanowska', now() + interval 6 day, now() + interval 6 day, 3, 'Płocka', '00-100', 3),
       ('Warszawa', 'Parking przy metrze Płocka ', now() + interval 8 day, now() + interval 2 day, 4, 'Wilanowska', '00-200', 4)
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