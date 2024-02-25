DROP TABLE IF EXISTS profesori;

CREATE TABLE profesori (
  id INT NOT NULL AUTO_INCREMENT,
  nume VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS materii;

CREATE TABLE materii (
  id INT NOT NULL AUTO_INCREMENT,
  nume VARCHAR(45) NOT NULL,
  id_profesor INT DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT materie_to_prof FOREIGN KEY (id_profesor) REFERENCES profesori(id)
);
CREATE INDEX materie_to_prof_idx ON materii(id_profesor);

DROP TABLE IF EXISTS adrese_studenti;

CREATE TABLE adrese_studenti (
  id INT NOT NULL AUTO_INCREMENT,
  strada VARCHAR(45) NOT NULL,
  numar VARCHAR(45) NOT NULL,
  localitate VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS studenti;

CREATE TABLE studenti (
  id INT NOT NULL AUTO_INCREMENT,
  prenume VARCHAR(45) NOT NULL,
  nume VARCHAR(45) NOT NULL,
  CNP VARCHAR(45) NOT NULL,
  id_adresa INT DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT student_to_adresa FOREIGN KEY (id_adresa) REFERENCES adrese_studenti(id)
);
CREATE INDEX student_to_adresa_idx ON studenti(id_adresa);

DROP TABLE IF EXISTS studenti_to_materii;

CREATE TABLE studenti_to_materii (
  id INT NOT NULL AUTO_INCREMENT,
  id_student INT NOT NULL,
  id_materie INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT materie_fk FOREIGN KEY (id_materie) REFERENCES materii(id),
  CONSTRAINT student_fk FOREIGN KEY (id_student) REFERENCES studenti(id)
);
CREATE INDEX student_fk_idx ON studenti_to_materii(id_student);
CREATE INDEX materie_fk_idx ON studenti_to_materii(id_materie);

CREATE TABLE note (
    id INT NOT NULL AUTO_INCREMENT,
    nota INT NOT NULL,
    data DATE,
    id_student INT NOT NULL,
    id_materie INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT student_fk_note FOREIGN KEY (id_student) REFERENCES studenti(id),
    CONSTRAINT materie_fk_note FOREIGN KEY (id_materie) REFERENCES materii(id)
);
CREATE INDEX student_fk_note_idx ON note(id_student);
CREATE INDEX materie_fk_note_idx ON note(id_materie);

-- It's actually possible to perform the exact same command you wrote without any modification,
-- provided you just add to your jdbc url the MySQL mode.
-- Example URL like this: jdbc:h2:mem:;mode=mysql