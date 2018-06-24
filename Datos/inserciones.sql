COPY zona(idZona,nombre) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\zona.csv' DELIMITER ',' CSV HEADER;

COPY unidadmedica(idUnidad,Nombre,idZona) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\unidadmedica.csv' DELIMITER ',' CSV HEADER;

COPY jefesuperior(idsuperior,nombre,apellidopaterno,apellidomaterno,jefe) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\jefesuperior.csv' DELIMITER ',' CSV HEADER;

COPY departamento(iddepartamento,nombre,idsuperior,permisodocente,permisopaee,permisodocpaee) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\departamento.csv' DELIMITER ',' CSV HEADER;

COPY horarioactual(idhorario) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\horarioactual.csv' DELIMITER ',' CSV HEADER;

COPY dia(iddia,horaentrada,horasalida,nombre,idhorario) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\dia.csv' DELIMITER ',' CSV HEADER;

COPY personal(idempleado,noempleado,habierto,activo,sexo,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo,idhorario) 
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;

COPY login(correo,passwordhash,idempleado)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\login.csv' DELIMITER ','CSV HEADER; 

COPY quincena(idquincena,inicio,fin,quincenareportada,quincenaenqueseraprocesada,fechalimite,fechalimpersonal)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,idempleado,idquincena,horasfaltantes)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,idempleado)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;

COPY periodoinhabil(idperiodo,inicio,fin,descripcion,aplicadocente,aplicapaee,justificacionarchivo)
<<<<<<< HEAD
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\periodoinhabil.csv' DELIMITER ',' CSV HEADER;

COPY tiemposuplgenerado(idtiemposuplgenerado,fecharegistro,horas,usado,idempleado)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\tiemposuplgenerado.csv' DELIMITER ',' CSV HEADER;

COPY motivo(idmotivo,descripcion)
FROM 'C:\Users\HeadHunters\Desktop\Documents\GitHub\proyecto\Datos\motivo.csv' DELIMITER ',' CSV HEADER;
=======
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\periodoinhabil.csv' DELIMITER ',' CSV HEADER;

COPY tiemposuplgenerado(idtiemposuplgenerado,fecharegistro,horas,usado,idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\tiemposuplgenerado.csv' DELIMITER ',' CSV HEADER;

COPY motivo(idmotivo,descripcion)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\motivo.csv' DELIMITER ',' CSV HEADER;
>>>>>>> 56c9de6e434af94866c633feabb89796d3c6440c
