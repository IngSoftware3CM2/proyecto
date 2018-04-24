COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior) 
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/departamento.csv' DELIMITER ',' CSV HEADER;

COPY personal(noempleado,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo) 
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/personal.csv' DELIMITER ','CSV HEADER;

COPY quincena(idquincena,inicio,fin)
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,noempleado,idquincena)
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,noempleado)
FROM '/home/abhera/Documentos/PROYECTOING/proyecto/Datos/asistencia.csv' DELIMITER ',' CSV HEADER;
