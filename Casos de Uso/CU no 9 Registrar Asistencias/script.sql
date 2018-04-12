-- Database: proyecto_CI

-- DROP DATABASE "proyecto_CI";

CREATE DATABASE "proyecto_CI"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

    DROP TABLE IF EXISTS public.asistencia;
    DROP TABLE IF EXISTS public.personal;

    CREATE TABLE public.personal (
    	noEmpleado varchar NOT NULL,
    	noTarjeta varchar NOT NULL,
    	nombre varchar NOT NULL,
    	apellido varchar NOT NULL,
    	CONSTRAINT personal_pk PRIMARY KEY (noEmpleado)
    );

    --RECOMENDED DATE INPUT FORMAT ACORDING TO ISO 8601:
    --yyyy-mm-dd i.e 1999-01-08 = January 8th of 1999

    --TIME INPUT FORMAT ACORDING TO ISO 8601:
    --hh:mm for 24 hour format
    --OR hh:mm AM/PM form 12 hour format, input hour must

    CREATE TABLE public.asistencia (
    	idAsistencia serial	NOT NULL,
    	fechaRegistro date NOT NULL,
    	horaEntrada time without time zone NOT NULL,
    	horaSalida time without time zone NOT NULL,
    	noEmpleado varchar NOT NULL,
        CONSTRAINT asistencia_pk PRIMARY KEY (idAsistencia),
        CONSTRAINT personal_pk FOREIGN KEY (noEmpleado)
            REFERENCES public.personal (noEmpleado)
            ON UPDATE CASCADE
            ON DELETE CASCADE
    );
