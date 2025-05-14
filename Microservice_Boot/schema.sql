create table IF NOT EXISTS USER(
                     ID int not null AUTO_INCREMENT,
                     NAME varchar(255) not null,
                     EMAIL varchar(255) not null unique,
                     PASSWORD varchar(255) not null,
                     PRIMARY KEY ( ID )
  );