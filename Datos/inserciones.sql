COPY zona(idZona,nombre) 
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 

FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior) 
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/departamento.csv' DELIMITER ',' CSV HEADER;

COPY personal(noempleado,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo) 
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/personal.csv' DELIMITER ','CSV HEADER;

COPY quincena(idquincena,inicio,fin)
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,noempleado,idquincena)
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,noempleado)
FROM '/home/josericardol/IntellijProjects/proyecto/proyecto/Datos/asistencia.csv' DELIMITER ',' CSV HEADER;

