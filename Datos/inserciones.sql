COPY zona(idZona,nombre) 
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior) 
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\departamento.csv' DELIMITER ',' CSV HEADER;

COPY personal(noempleado,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo) 
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;


COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,noempleado,idquincena)
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;


COPY quincena(idquincena,inicio,fin)
FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,noempleado,idquincena)
FROM 'C:\Users\jonat\Documents\GitHub\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,noempleado)

FROM 'C:\Users\sam-y\Documents\IngenieriaSoftware\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;

FROM 'C:\Users\jonat\Documents\GitHub\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;

