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

COPY personal(idempleado,noempleado,habierto,activo,sexo,nombre,apellidopaterno,apellidomaterno,notarjeta,iddepartamento,tipo,idhorario)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\personal.csv' DELIMITER ','CSV HEADER;

COPY login(correo,passwordhash,idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\login.csv' DELIMITER ','CSV HEADER; 

COPY quincena(idquincena,inicio,fin,quincenareportada,quincenaenqueseraprocesada,fechalimite,fechalimpersonal)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\quincena.csv' DELIMITER ',' CSV HEADER;

COPY justificante(idjustificante, estado, fecha, tipo, idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\justificante.csv' DELIMITER ',' CSV HEADER;

COPY incidencia(idincidencia,fecharegistro,tipo,idjustificante,idempleado,idquincena,horasfaltantes)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\incidencia.csv' DELIMITER ',' CSV HEADER;

COPY asistencia(idasistencia,fecharegistro,horaentrada,horasalida,idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\asistencia.csv' DELIMITER ',' CSV HEADER;

COPY periodoinhabil(idperiodo,inicio,fin,descripcion,aplicadocente,aplicapaee,justificacionarchivo)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\periodoinhabil.csv' DELIMITER ',' CSV HEADER;

COPY tiemposuplgenerado(idtiemposuplgenerado,fecharegistro,horas,usado,idempleado)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\tiemposuplgenerado.csv' DELIMITER ',' CSV HEADER;

COPY motivo(idmotivo,descripcion)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\motivo.csv' DELIMITER ',' CSV HEADER;



COPY tipoa(id, fechafin, folio, fechainicio, licenciaarchivo, tipo, idjustificante, idunidad)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\tipoa.csv' DELIMITER ',' CSV HEADER;

COPY cambiohorario(id, fecha, horaentrada, horasalida, justificacion, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\cambiohorario.csv' DELIMITER ',' CSV HEADER;

COPY licpaternidad(id, actamatrimonio, actanacimiento, comprobanteingresos, constanciacurso, justificacion, registrolicencia, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\licpaternidad.csv' DELIMITER ',' CSV HEADER;

COPY permisoeconomico(id, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\permisoeconomico.csv' DELIMITER ',' CSV HEADER;

COPY constanciatiempo(id, licenciaarchivo, segfecha, tipo, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\constanciatiempo.csv' DELIMITER ',' CSV HEADER;

COPY tiemposuplementario(id, fecha, tiempocubrir, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\tiemposuplementario.csv' DELIMITER ',' CSV HEADER;

COPY omisionentrsal(id, justificacion, tipo, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\omisionentrsal.csv' DELIMITER ',' CSV HEADER;

COPY retardo(id, justificacion, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\retardo.csv' DELIMITER ',' CSV HEADER;

COPY comisionoficial(id, fechafin, fechainicio, invitacionarchivo, idjustificante)
FROM 'C:\Users\jonat\Documents\GitHub\iso\proyecto\Datos\comisionoficial.csv' DELIMITER ',' CSV HEADER;


