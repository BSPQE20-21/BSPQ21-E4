/* CREATE ''parkingmanagement' DATABASE */
CREATE SCHEMA parkingmanagement;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD '1234' */
CREATE USER '1234'@'%' IDENTIFIED BY '1234';
/* GRANT FULL ACCESS TO THE DATABASE FOR THE USER 'root' AT LOCAL SERVER*/
GRANT ALL ON parkingmanagement.* TO 'root'@'%';