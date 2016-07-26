create database bdMiRutina
use bdMiRutina

/*Creacion de tablas*/
create table Musculo(
idMusculo int IDENTITY(1,1) primary key NOT NULL,
nombreMusculo varchar(50),
estado int);

create table Rutina(
idRutina int IDENTITY(1,1) primary key NOT NULL,
fecha varchar(10),
estado int);

create table Ejercicio(
idEjercicio int IDENTITY(1,1) primary key NOT NULL,
nombreEjercicio varchar(50),
detalleEjercicio varchar(200),
idMusculo int foreign key references Musculo(idMusculo),
estado int );

create table Repeticion(
idRepeticion int IDENTITY(1,1) primary key NOT NULL,
idEjercicio int foreign key references Ejercicio(idEjercicio),
peso int,
repeticiones int ,
tiempoDescanso int,
tiempoEjercicio int,
umedida int, 
estado int);

create table Rutina_Repeticion(
idRutina int foreign key references Rutina(idRutina),
idRepeticion int foreign key references Repeticion(idRepeticion));

/*Insert respectivos*/

/*Musculos*/
insert into Musculo (nombreMusculo,estado) values('Bicep',1);
insert into Musculo (nombreMusculo,estado) values('Tricep',1);
insert into Musculo (nombreMusculo,estado) values('Abdomen',1);
insert into Musculo (nombreMusculo,estado) values('Piernas',1);
insert into Musculo (nombreMusculo,estado) values('Hombros',1);
insert into Musculo (nombreMusculo,estado) values('Espalda',1);
insert into Musculo (nombreMusculo,estado) values('Pectorales',1);

/*Ejercicios*/
/*Bicep*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('reverse wrist curl','',1,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('standing biceps curl','',1,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('zottman curl','',1,1);
/*Tricep*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('extension vertical alternada de los brazos','',2,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('extension alternada de los antebrazos','',2,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('extension de tricep en polea','',2,1);
/*Abdomen*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('clam','',3,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('bumbbell slide bend','',3,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('cable kneeling crunch','',3,1);
/*Piernas*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('squats on the shoulders','',4,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('walk on the elliptical','',4,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('pacing with dumbbells','',4,1);
/*Hombros*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('cable upright row','',5,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('elevacion lateral de mancuernas','',5,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('elevacion trasera de mancuerna','',5,1);
/*Espalda*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('pull over con polea alta','',6,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('remo horizontal con barra','',6,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('polea trasnuca','',6,1);
/*Pectorales*/
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('levantamiento de mancuerna en inclinacion','',7,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('press de banca con inclinacion','',7,1);
insert into Ejercicio (nombreEjercicio, detalleEjercicio, idMusculo, estado) values('apertura de mancuernas con inclinacion','',7,1);

/*repeticiones*/
/*bicep*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(1,10,10,30,12,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(2,15,12,25,12,1,1);
/*tricep*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(4,5,12,45,15,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(5,7,13,40,12,1,1);
/*abdomen*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(7,0,12,35,15,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(8,0,15,20,17,1,1);
/*piernas*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(10,30,15,50,18,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(11,35,20,40,16,1,1);
/*hombros*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(13,5,10,25,12,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(14,7,12,20,11,1,1);
/*espalda*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(16,25,12,30,14,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(17,20,15,22,17,1,1);
/*Pectoral*/
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(19,20,10,25,13,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(19,20,10,30,15,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(19,20,10,31,16,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(20,10,10,17,13,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(20,10,10,19,15,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(20,10,10,21,21,1,1);
insert into Repeticion (idEjercicio, peso, repeticiones, tiempoDescanso, tiempoEjercicio, umedida, estado) values(20,10,10,23,18,1,1);

/*Rutina*/
insert into Rutina (fecha, estado) values('17/06/2016',1);
insert into Rutina (fecha, estado) values('18/06/2016',1);
insert into Rutina (fecha, estado) values('19/06/2016',1);

insert into Rutina_Repeticion (idRutina, idRepeticion) values(1,1);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(1,2);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(1,3);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(1,4);

insert into Rutina_Repeticion (idRutina, idRepeticion) values(2,5);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(2,6);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(2,7);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(2,8);

insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,9);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,10);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,11);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,12);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,13);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,14);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,15);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,16);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,17);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,18);
insert into Rutina_Repeticion (idRutina, idRepeticion) values(3,19);



/*Select's*/
select * from Musculo;
select * from Ejercicio;
select * from Repeticion;
select * from Rutina;
select * from Rutina_Repeticion;

/*Delete's*/
delete from Musculo;
delete from Ejercicio;
delete from Repeticion;
delete from Rutina;
delete from Rutina_Repeticion;

/*Drop table's*/
drop table Rutina_Repeticion;
drop table Rutina;
drop table Repeticion;
drop table Ejercicio;
drop table Musculo;

sp_help repeticion
sp_help rutina
sp_help Rutina_Repeticion


/*Consultas importantes*/

/*Ultimos musculos trabajados*/
select distinct (m.nombreMusculo)
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016'

/*Ultimos ejercicios realizados*/
select m.nombreMusculo as 'Musculo', count(*) as 'Ejercicios realizados'
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016'
group by m.nombreMusculo

/*Detalles de ejercicios realizados en un musculo en especial y su n° de series*/
select e.nombreEjercicio, count(*) as 'Series realizadas'
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016' and m.nombreMusculo = 'Pectorales'
group by e.nombreEjercicio

/*Detalles de las series realizadas en un ejericio*/
select e.nombreEjercicio as 'Ejercicio', re.peso, re.repeticiones, re.tiempoDescanso, re.tiempoEjercicio 
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016' and m.nombreMusculo = 'Pectorales' and e.nombreEjercicio = 'press de banca con inclinacion'


/*Detalles de l promedio de un ejercicio*/
select avg(re.peso), avg(re.repeticiones), avg(re.tiempoDescanso), avg(re.tiempoEjercicio) 
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016' and m.nombreMusculo = 'Pectorales' and e.nombreEjercicio = 'press de banca con inclinacion'

/*Detalles de l promedio de un ejercicio*/
select sum(re.peso), sum(re.repeticiones), sum(re.tiempoDescanso), sum(re.tiempoEjercicio) 
from rutina r
inner join Rutina_Repeticion rp on rp.idRutina = r.idRutina
inner join Repeticion re on re.idRepeticion = rp.idRepeticion
inner join Ejercicio e on e.idEjercicio = re.idEjercicio
inner join Musculo m on e.idMusculo = m.idMusculo
where r.fecha ='19/06/2016' and m.nombreMusculo = 'Pectorales' and e.nombreEjercicio = 'press de banca con inclinacion'