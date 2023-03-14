INSERT INTO apartment(name, description, area, price) VALUES
     ('Luksusowy apartament w centrum miasta', 'Pięknie urządzony apartament z balkonem i widokiem na panoramę miasta', 80, 1000),
     ('Apartament nad morzem', 'Nowoczesny apartament z widokiem na morze, zaledwie kilka kroków od plaży', 50, 500),
     ('Apartament w górach', 'Przytulny apartament z kominkiem i widokiem na góry, idealny na zimowy wypoczynek', 70, 800),
     ('Apartament w kamienicy', 'Elegancki apartament z antycznymi meblami i oryginalnymi detalmi architektonicznymi', 60, 600),
     ('Apartament w luksusowej dzielnicy', 'Przestronny apartament z tarasem i widokiem na park, w pobliżu znajdują się ekskluzywne sklepy i restauracje', 100, 1200);

-- INSERT INTO person(id, name) VALUES
--      (1, 'Jan Kowalski'),
--      (2, 'Marian Kowalski');

INSERT INTO booking(date_from, date_to) VALUES
     (TO_DATE('2023-01-02', 'yyyy-MM-dd'),CURRENT_DATE);