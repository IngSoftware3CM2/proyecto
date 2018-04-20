DROP TABLE IF EXISTS public.asistencia CASCADE;
DROP TABLE IF EXISTS public.jefesuperior CASCADE;
DROP TABLE IF EXISTS public.departamento CASCADE;
DROP TABLE IF EXISTS public.personal CASCADE;
DROP TABLE IF EXISTS public.justificante CASCADE;
DROP TABLE IF EXISTS public.licpaternidad CASCADE;
DROP TABLE IF EXISTS public.incidencia CASCADE;
DROP TABLE IF EXISTS public.permisoeconomico CASCADE;
DROP TABLE IF EXISTS public.quincena CASCADE;

CREATE TABLE public.quincena (
    idQuincena serial NOT NULL,
    inicioQuincena date NOT NULL,
    finQuincena date NOT NULL,
    CONSTRAINT quincena_pk PRIMARY KEY (idQuincena)
);

CREATE TABLE public.jefesuperior (
    idSuperior integer  NOT NULL,
    nombre varchar (60) NOT NULL,
    jefe integer,
    apellidoPaterno varchar  (30) NOT NULL,
    apellidoMaterno varchar (30) NOT NULL,
    CONSTRAINT jefesuperior_pk PRIMARY KEY (idSuperior)
);

ALTER TABLE public.jefesuperior
    ADD CONSTRAINT jefesuperior_jefe_fk FOREIGN KEY (jefe)
        REFERENCES public.jefesuperior (idSuperior)
        ON UPDATE CASCADE
        ON DELETE CASCADE;

CREATE TABLE public.departamento (
    idDepartamento smallint NOT NULL,
    nombre varchar (60) NOT NULL,
    idSuperior integer NOT NULL,
    CONSTRAINT departamento_pk PRIMARY KEY (idDepartamento),
    CONSTRAINT jefesuperior_fk FOREIGN KEY (idSuperior)
        REFERENCES public.jefesuperior (idSuperior)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.personal (
    noEmpleado integer NOT NULL,
    noTarjeta integer UNIQUE NOT NULL,
    nombre varchar (60) NOT NULL,
    apellidoPaterno varchar (30) NOT NULL,
    apellidoMaterno varchar (30) NOT NULL,
    tipo character (2) NOT NULL,
    diaseconomicos smallint NOT NULL,
    idDepartamento smallint NOT NULL,
    CONSTRAINT personal_pk PRIMARY KEY (noEmpleado),
    CONSTRAINT departamento_fk FOREIGN KEY (idDepartamento)
        REFERENCES public.departamento (idDepartamento)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.justificante (
    idJustificante serial NOT NULL,
    noEmpleado integer NOT NULL,
    CONSTRAINT justificante_pk PRIMARY KEY (idJustificante),
    CONSTRAINT personal_fk FOREIGN KEY (noEmpleado)
        REFERENCES public.personal (noEmpleado)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.licpaternidad (
    idJustificante integer NOT NULL,
    justificacion text NOT NULL,
    registroLicencia varchar NOT NULL,
    actaNacimiento varchar NOT NULL,
    actaMatrimonio varchar NOT NULL,
    constanciaCurso varchar NOT NULL,
    comprobanteIngresos varchar NOT NULL,
    copiaIdentificacion varchar NOT NULL,
    CONSTRAINT licpaternidad_pk PRIMARY KEY (idJustificante),
    CONSTRAINT licpaternidad_idjustificante_fk FOREIGN KEY (idJustificante)
        REFERENCES public.justificante (idJustificante)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.permisoeconomico (
    idJustificante integer NOT NULL,
    CONSTRAINT permisoeconomico_pk PRIMARY KEY (idJustificante),
    CONSTRAINT permisoeconomico_idjustificante_fk FOREIGN KEY (idJustificante)
        REFERENCES public.justificante (idJustificante)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.incidencia (
    idIncidencia serial NOT NULL,
    fechaRegistro date NOT NULL,
    tipo character (2) NOT NULL,
    noEmpleado integer NOT NULL,
    idJustificante integer,
    idQuincena integer NOT NULL,
    CONSTRAINT incidencia_pk PRIMARY KEY (idIncidencia),
    CONSTRAINT justificante_fk FOREIGN KEY (idJustificante)
        REFERENCES public.justificante (idJustificante)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT quincena_fk FOREIGN KEY (idQuincena)
        REFERENCES public.quincena (idQuincena)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT personal_fk FOREIGN KEY (noEmpleado)
        REFERENCES public.personal (noEmpleado)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.asistencia (
    	idAsistencia integer	 NOT NULL,
    	fechaRegistro date NOT NULL,
    	horaEntrada time without time zone NOT NULL,
    	horaSalida time without time zone NOT NULL,
    	noEmpleado integer NOT NULL,
        CONSTRAINT asistencia_pk PRIMARY KEY (idAsistencia),
        CONSTRAINT personal_fk FOREIGN KEY (noEmpleado)
            REFERENCES public.personal (noEmpleado)
            ON UPDATE CASCADE
            ON DELETE CASCADE
);
