DROP TABLE IF EXISTS public.permisoeconomico;
DROP TABLE IF EXISTS public.quincena;

CREATE TABLE public.quincena (
    idQuincena serial NOT NULL,
    inicioQuincena date NOT NULL,
    finQuincena date NOT NULL,
    CONSTRAINT quincena_pk PRIMARY KEY (idQuincena)
);

CREATE TABLE public.permisoeconomico (
    idJustificante integer NOT NULL,
    CONSTRAINT permisoeconomico_pk PRIMARY KEY (idJustificante),
    CONSTRAINT permisoeconomico_idjustificante_fk FOREIGN KEY (idJustificante)
        REFERENCES public.justificante (idJustificante)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

ALTER TABLE public.personal
    ADD COLUMN diaseconomicos smallint NOT NULL;

ALTER TABLE public.incidencia
    ADD COLUMN noEmpleado integer NOT NULL
    CONSTRAINT personal_fk FOREIGN KEY (noEmpleado)
        REFERENCES public.personal (noEmpleado)
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE public.quincena
    ADD COLUMN idQuincena integer NOT NULL
    CONSTRAINT quincena_fk FOREIGN KEY (idQuincena)
        REFERENCES public.quincena (idQuincena)
        ON UPDATE CASCADE
        ON DELETE CASCADE;
