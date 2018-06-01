COPY zona(idZona,nombre) 
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior,permisodocente,permisopaee,permisodocpaee) 
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\departamento.csv' DELIMITER ',' CSV HEADER;

COPY personal(idempleado,noempleado,activo,sexo,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo) 
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;

COPY quincena(idquincena,inicio,fin,quincenareportada,quincenaenqueseraprocesada,habil)
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,noempleado,idquincena)
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,noempleado)
FROM 'C:\Users\jonat\Documents\Auxiliar\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;


