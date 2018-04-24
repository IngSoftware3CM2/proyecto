CREATE TABLE public.zona(
    idZona integer NOT NULL,
    nombre varchar(20) NOT NULL,
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

CREATE TABLE public.tipoa(
    idJustificante integer NOT NULL,
    folio varchar(18) NOT NULL
    fechainicio date NOT NULL,
    fechafin date NOT NULL,
    licenciamedica varchar(255) NOT NULL,
    tipo varchar(2) NOT NULL,
    idUnidad varchar(10) NOT NULL,
    CONSTRAINT tipoa_pk PRIMARY KEY (idJustificante),
    CONSTRAINT unidadmedica_fk FOREIGN KEY (idUnidad)
        REFERENCES public.unidadmedica (idUnidad)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
