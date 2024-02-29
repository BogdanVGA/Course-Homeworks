DROP TABLE IF EXISTS teams;

CREATE TABLE teams (
    id INT NOT NULL AUTO_INCREMENT,
    abbreviation VARCHAR(8),
    city VARCHAR(64),
    conference VARCHAR(64),
    division VARCHAR(64),
    full_name VARCHAR(64),
    name VARCHAR(64),
    source_id INT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS players;

CREATE TABLE players (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    play_pos VARCHAR(8),
    height VARCHAR(64),
    weight VARCHAR(64),
    jersey_number VARCHAR(64),
    college VARCHAR(64),
    country VARCHAR(64),
    team_id INT,
    PRIMARY KEY (id),
    CONSTRAINT player_to_team FOREIGN KEY (team_id) REFERENCES teams(id)
);
CREATE INDEX player_to_team_idx ON players(team_id);
