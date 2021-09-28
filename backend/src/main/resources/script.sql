create table user (
   id INT NOT NULL,
   PRIMARY KEY (id)
);

create table artist (
   id INT NOT NULL auto_increment,
   name VARCHAR(30) default NULL,
   PRIMARY KEY (id)
);

create table favorite (
   user_id INT NOT NULL,
   artist_id INT NOT NULL,
   PRIMARY KEY (user_id,artist_id)
);

INSERT INTO user VALUES (57956);
INSERT INTO user VALUES (44778);
INSERT INTO user VALUES (82887);