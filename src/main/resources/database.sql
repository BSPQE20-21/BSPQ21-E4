
/* CREATE ''parkingmanagement' DATABASE */
CREATE SCHEMA parkingmanagement;
/* CREATE THE USER 'root' AT LOCAL SERVER WITH PASSWORD '1234' */
CREATE USER  if not exists 'root'@'%' IDENTIFIED BY '1234';
/* GRANT FULL ACCESS TO THE DATABASE FOR THE USER 'root' AT LOCAL SERVER*/
GRANT ALL ON parkingmanagement.* TO 'root'@'%';