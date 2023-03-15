INSERT INTO apartment(name, description, area, price) VALUES
     ('Luksusowy apartament w centrum miasta', 'Pięknie urządzony apartament z balkonem i widokiem na panoramę miasta', 80, 1000),
     ('Apartament nad morzem', 'Nowoczesny apartament z widokiem na morze, zaledwie kilka kroków od plaży', 50, 500),
     ('Apartament w górach', 'Przytulny apartament z kominkiem i widokiem na góry, idealny na zimowy wypoczynek', 70, 800),
     ('Apartament w kamienicy', 'Elegancki apartament z antycznymi meblami i oryginalnymi detalmi architektonicznymi', 60, 600),
     ('Apartament w luksusowej dzielnicy', 'Przestronny apartament z tarasem i widokiem na park, w pobliżu znajdują się ekskluzywne sklepy i restauracje', 100, 1200);

INSERT INTO person(id, name) VALUES
     (1, 'Jan Kowalski'),
     (2, 'Anna Wojcik'),
     (3, 'Agnieszka Mazur'),
     (4, 'Piotr Krajewski'),
     (5, 'Joanna Tomaszewska'),
     (6, 'Robert Szymanski'),
     (7, 'Katarzyna Nowak'),
     (8, 'Janusz Duda'),
     (9, 'Andrzej Kaczmarek'),
     (10, 'Marian Kowalski');

INSERT INTO booking(date_from, date_to, cost, landlord_id, tenant_id, apartment_id) VALUES
     (TO_DATE('2023-01-01', 'yyyy-MM-dd'),TO_DATE('2023-01-05', 'yyyy-MM-dd'), 4000, 1, 2, 1),
     (TO_DATE('2023-01-06', 'yyyy-MM-dd'),TO_DATE('2023-01-10', 'yyyy-MM-dd'), 3000, 1, 3, 1),
     (TO_DATE('2023-01-01', 'yyyy-MM-dd'),TO_DATE('2023-01-10', 'yyyy-MM-dd'), 4500, 4, 1, 2),
     (TO_DATE('2023-02-01', 'yyyy-MM-dd'),TO_DATE('2023-02-10', 'yyyy-MM-dd'), 4500, 4, 7, 2),
     (TO_DATE('2023-05-01', 'yyyy-MM-dd'),TO_DATE('2023-05-10', 'yyyy-MM-dd'), 7200, 2, 8, 3),
     (TO_DATE('2023-01-01', 'yyyy-MM-dd'),TO_DATE('2023-01-10', 'yyyy-MM-dd'), 6000, 7, 10, 4),
     (TO_DATE('2023-02-01', 'yyyy-MM-dd'),TO_DATE('2023-02-10', 'yyyy-MM-dd'), 6000, 7, 9, 4),
     (TO_DATE('2023-02-11', 'yyyy-MM-dd'),TO_DATE('2023-02-20', 'yyyy-MM-dd'), 5400, 7, 1, 4),
     (TO_DATE('2023-01-01', 'yyyy-MM-dd'),TO_DATE('2023-01-05', 'yyyy-MM-dd'), 4800, 10, 2, 5),
     (TO_DATE('2023-01-15', 'yyyy-MM-dd'),TO_DATE('2023-01-21', 'yyyy-MM-dd'), 6000, 10, 4, 5);