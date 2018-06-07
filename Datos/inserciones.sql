COPY zona(idZona,nombre) 
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior,permisodocente,permisopaee,permisodocpaee) 
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\departamento.csv' DELIMITER ',' CSV HEADER;

COPY personal(idempleado,noempleado,activo,sexo,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo) 
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;

COPY quincena(idquincena,inicio,fin)
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,idempleado,idquincena)
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,idempleado)
FROM 'C:\Users\saidn\Desktop\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;