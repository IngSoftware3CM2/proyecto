COPY zona(idZona,nombre) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior,permisodocente,permisopaee,permisodocpaee) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\departamento.csv' DELIMITER ',' CSV HEADER;

COPY horarioactual(idhorario) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\horarioactual.csv' DELIMITER ',' CSV HEADER;

COPY dia(iddia,horaentrada,horasalida,nombre,idhorario) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\dia.csv' DELIMITER ',' CSV HEADER;

COPY personal(idempleado,noempleado,activo,sexo,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo,idhorario) 
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;

COPY quincena(idquincena,inicio,fin,quincenareportada,quincenaenqueseraprocesada,fechalimite,fechalimpersonal)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,idempleado,idquincena)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;

COPY periodoinhabil(idperiodo,inicio,fin,descripcion,aplicadocente,aplicapaee,justificacionarchivo)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\periodoinhabil.csv' DELIMITER ',' CSV HEADER;