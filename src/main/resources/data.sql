INSERT INTO apartment(area, description) VALUES
     ('apartament1', 'apartament1'),
     ('apartament2', 'apartament2');

-- INSERT INTO person(id, name) VALUES
--      (1, 'Jan Kowalski'),
--      (2, 'Marian Kowalski');

INSERT INTO booking(date_from, date_to) VALUES
     (TO_DATE('2023-01-02', 'yyyy-MM-dd'),CURRENT_DATE);