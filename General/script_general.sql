DROP TABLE IF EXISTS public.asistencia CASCADE;
DROP TABLE IF EXISTS public.jefesuperior CASCADE;
DROP TABLE IF EXISTS public.departamento CASCADE;
DROP TABLE IF EXISTS public.personal CASCADE;
DROP TABLE IF EXISTS public.justificante CASCADE;
DROP TABLE IF EXISTS public.licpaternidad CASCADE;
DROP TABLE IF EXISTS public.incidencia CASCADE;
DROP TABLE IF EXISTS public.permisoeconomico CASCADE;
DROP TABLE IF EXISTS public.quincena CASCADE;
DROP TABLE IF EXISTS public.login CASCADE;
DROP TABLE IF EXISTS public.zona CASCADE;
DROP TABLE IF EXISTS public.unidadmedica CASCADE;
DROP TABLE IF EXISTS public.tipoa CASCADE;
DROP TABLE IF EXISTS public.cambiohorario CASCADE;
DROP TABLE IF EXISTS public.horarioactual CASCADE;
DROP TABLE IF EXISTS public.dia CASCADE;

/* 1/5/18 Jonth REVISAR SI ESTA TABLA PUEDE FUSIONARSE CON Personal, pues es uno a uno la relacion, aunque podría generar errores en el mapeo 
para desarrollo si tenemos que cada empleado tiene un horario único, sino asi esta bien*/
CREATE TABLE public.horarioactual (
    	idHorario integer	 NOT NULL,		
        CONSTRAINT horario_pk PRIMARY KEY (idHorario)
		
);

CREATE TABLE public.dia (
		idDia integer not NULL,
		nombre CHARACTER(3),									/*LUN MAR MIE JUE VIE*/
		horaEntrada time without time zone NOT NULL,
    	horaSalida time without time zone NOT NULL,
		idHorario integer not NULL,
		CONSTRAINT dia_pk PRIMARY KEY (idDia),
		CONSTRAINT diahorario_fk FOREIGN KEY(idHorario)
			REFERENCES public.horarioactual (idHorario)
			ON UPDATE CASCADE
			ON DELETE set null
		
);
/*Esta tabla no será una entidad, porque la relacion con cambiohorario era uno a uno, fue absorbida
CREATE TABLE public.horariocubierto (
    	idHorarioCubierto integer	 NOT NULL,
    	
        CONSTRAINT horariocubierto_pk PRIMARY KEY (idHorarioCubierto)
);*/

CREATE TABLE public.quincena (
    idQuincena serial NOT NULL,
    inicioQuincena date NOT NULL,
    finQuincena date NOT NULL,
    CONSTRAINT quincena_pk PRIMARY KEY (idQuincena)
);

CREATE TABLE public.zona(
    idZona integer NOT NULL,
    nombre varchar(35) NOT NULL,
    CONSTRAINT zona_pk PRIMARY KEY (idZona)
);

CREATE TABLE public.unidadmedica(
    idUnidad varchar(10) NOT NULL,
    nombre varchar(100) NOT NULL,
    idZona integer NOT NULL,
    CONSTRAINT unidadmedica_pk PRIMARY KEY (idUnidad),
    CONSTRAINT idzona_fk FOREIGN KEY (idZona)
        REFERENCES public.zona (idZona)
        ON UPDATE CASCADE
        ON DELETE CASCADE
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
	idusuario integer NOT NULL,
    idDepartamento smallint NOT NULL,
	idHorario integer NOT NULL,
	
    CONSTRAINT personal_pk PRIMARY KEY (noEmpleado),
    CONSTRAINT departamento_fk FOREIGN KEY (idDepartamento)
        REFERENCES public.departamento (idDepartamento)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

	CONSTRAINT personalhorario_fk FOREIGN KEY(idHorario)
		REFERENCES public.horarioactual (idHorario)
		ON UPDATE CASCADE
		ON DELETE set null
		
);

CREATE TABLE public.justificante (
    idJustificante serial NOT NULL,
	estado varchar (20) NOT NULL,
	fecha date NOT NULL,
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

CREATE TABLE public.tipoa(
    idJustificante integer NOT NULL,
    folio varchar(18) NOT NULL,
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    licenciamedica varchar(255) NOT NULL,
    tipo varchar(2) NOT NULL,
    idUnidad varchar(10) NOT NULL,
    CONSTRAINT tipoa_pk PRIMARY KEY (idJustificante),
	CONSTRAINT tipoa_idjustificante_fk FOREIGN KEY (idJustificante)
        REFERENCES public.justificante (idJustificante)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT unidadmedica_fk FOREIGN KEY (idUnidad)
        REFERENCES public.unidadmedica (idUnidad)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
/*1/5/18 Jonth*/
CREATE TABLE public.cambiohorario (
	idJustificante integer NOT NULL,
	justificacion text NOT NULL,
	
	fecha date NOT NULL,
    horaEntrada time without time zone NOT NULL,
    horaSalida time without time zone NOT NULL,
		
	CONSTRAINT cambiohorario_pk PRIMARY KEY (idJustificante),
	CONSTRAINT cambiohorario_idjustificante_fk FOREIGN KEY (idJustificante)
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


CREATE TABLE public.login (
	correo varchar(70) NOT NULL,
	passwordsalt varchar(255) NOT NULL,
	passwordhash varchar(255) NOT NULL,
	noEmpleado integer NOT NULL,
	CONSTRAINT login_pk PRIMARY KEY (correo),
	CONSTRAINT personal_fk FOREIGN KEY(noEmpleado)
		REFERENCES public.personal (noEmpleado)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);