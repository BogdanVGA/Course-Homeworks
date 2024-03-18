INSERT INTO profesori(nume)
VALUES
    ('Fometescu'),
    ('Pavel'),
    ('Pop'),
    ('Georgescu');

INSERT INTO materii(nume, id_profesor)
VALUES ('Matematica 1',3),
        ('Matematica 2',3),
        ('Filosofie',1),
        ('Programare',4),
        ('Fizica',2);

INSERT INTO adrese_studenti(strada, numar, localitate)
VALUES
    ('Iuliu Maniu','15','Bucuresti'),
    ('Unirii','23','Focsani'),
    ('Garii','23B','Bucuresti'),
    ('Unirii','23','Focsani'),
    ('Fabricii','22','Iasi'),
    ('Fabricii','1','Iasi');

INSERT INTO studenti(prenume, nume, cnp, id_adresa)
VALUES
    ('George','Georgescu','1234', 1),
    ('Mirel','Vartan','1234', 2),
    ('Codrut','Popescu','1234', 3),
    ('Marcel','Pavel','1234', 4),
    ('Virgil','Iantu','1234', 5),
    ('Ionel','Popescu','1234', 6);

INSERT INTO studenti_to_materii(id_student, id_materie)
VALUES
    (2,1),
    (6,1),
    (6,5);

INSERT INTO note(nota, data, id_student, id_materie)
VALUES
    (9, '2023-11-04', 2, 1),
    (7, '2023-11-12', 6, 1),
    (10, '2023-12-04', 6, 5);

INSERT INTO users(email, password, role)
VALUES
    ('user@email.com', '$2a$10$mgcoWMKNLCNOSIzBMpwCqO3n1KIVUCJ6DRZbnOmqXnu58XKDA6d4i', 'USER'),
    ('admin@email.com', '$2a$10$qjCec9/UWTvJfy/bw7yx.uQbOyk5FQ2v1M5SQohRAcyhBkHsL9pza', 'ADMIN'),
    ('staff@email.com', '$2a$12$8lDje.fnRkb04CaU6.kg5.U4L2sqnvmV.qtcZHspXyo3NU9lLuXia', 'STAFF');
-- user password: userpass
-- admin password: adminpass
-- staff password: staffpass
-- inside the table passwords are stored in Bcrypt encrypted hash