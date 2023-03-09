CREATE TABLE Usuario (
id bigint,
username varchar(50) not null,
password varchar(50),
name varchar(100),
email varchar(100) not null,
CONSTRAINT Usuario_pk PRIMARY KEY (id)
);