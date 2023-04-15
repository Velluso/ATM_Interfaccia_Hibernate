create database atm;
use atm;

create table conto (
                       id int not null auto_increment primary key,
                       nome varchar(30),
                       cognome varchar(40),
                       saldo double,
                       pin int(4));

insert into conto (nome, cognome, saldo, pin)
values
    ("Marco", "Velluso", 20000.00, 1111)


