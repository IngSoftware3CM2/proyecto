-- =============================================================================
-- Diagram Name: Diagrama R General (as is mapped)
-- Created on: 23/04/2018 10:49:57
-- Diagram Version: 
-- =============================================================================
CREATE SEQUENCE "hibernate_sequence"
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1;

ALTER TABLE "hibernate_sequence" OWNER TO "postgres";




CREATE TABLE "asistencia" (
	"id" SERIAL NOT NULL,
	"fecha_registro" date NOT NULL,
	"hora_entrada" time NOT NULL,
	"hora_salida" time NOT NULL,
	"no_empleado" int4,
	CONSTRAINT "asistencia_pkey" PRIMARY KEY("id")
)
WITH (
	OIDS = False
);

ALTER TABLE "asistencia" OWNER TO "postgres";

CREATE TABLE "departamento" (
	"id_departamento" int4 NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"id_superior" int4,
	CONSTRAINT "departamento_pkey" PRIMARY KEY("id_departamento")
)
WITH (
	OIDS = False
);

ALTER TABLE "departamento" OWNER TO "postgres";

CREATE TABLE "incidencia" (
	"id_incidencia" int4 NOT NULL,
	"fecha_registro" date NOT NULL,
	"tipo" varchar(2) NOT NULL,
	"id_justificante" int4,
	"no_empleado" int4,
	"id_quincena" int4,
	CONSTRAINT "incidencia_pkey" PRIMARY KEY("id_incidencia")
)
WITH (
	OIDS = False
);

ALTER TABLE "incidencia" OWNER TO "postgres";

CREATE TABLE "jefe_superior" (
	"id_superior" int4 NOT NULL,
	"apellido_materno" varchar(30) NOT NULL,
	"apellido_paterno" varchar(30) NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"jefe" int4,
	CONSTRAINT "jefe_superior_pkey" PRIMARY KEY("id_superior")
)
WITH (
	OIDS = False
);

ALTER TABLE "jefe_superior" OWNER TO "postgres";

CREATE TABLE "justificante" (
	"id_justificante" int4 NOT NULL,
	"estado" varchar(20) NOT NULL,
	"fecha" time NOT NULL,
	"no_empleado" int4,
	CONSTRAINT "justificante_pkey" PRIMARY KEY("id_justificante")
)
WITH (
	OIDS = False
);

ALTER TABLE "justificante" OWNER TO "postgres";

CREATE TABLE "lic_paternidad" (
	"id_justificante" int4 NOT NULL,
	"actamatrimonio" varchar(255) NOT NULL,
	"actanacimiento" varchar(255) NOT NULL,
	"comprobanteingresos" varchar(255) NOT NULL,
	"constanciacurso" varchar(255) NOT NULL,
	"copiaidentificacion" varchar(255) NOT NULL,
	"justificacion" varchar(255) NOT NULL,
	"registrolicencia" varchar(255) NOT NULL,
	CONSTRAINT "lic_paternidad_pkey" PRIMARY KEY("id_justificante")
)
WITH (
	OIDS = False
);

ALTER TABLE "lic_paternidad" OWNER TO "postgres";

CREATE TABLE "permiso_economico" (
	"id_justificante" int4 NOT NULL,
	CONSTRAINT "permiso_economico_pkey" PRIMARY KEY("id_justificante")
)
WITH (
	OIDS = False
);

ALTER TABLE "permiso_economico" OWNER TO "postgres";

CREATE TABLE "personal" (
	"no_empleado" int4 NOT NULL,
	"apellido_materno" varchar(30) NOT NULL,
	"apellido_paterno" varchar(30) NOT NULL,
	"no_tarjeta" int4 NOT NULL,
	"nombre" varchar(60) NOT NULL,
	"tipo" varchar(4) NOT NULL,
	"id_departamento" int4,
	CONSTRAINT "personal_pkey" PRIMARY KEY("no_empleado"),
	CONSTRAINT "uk_kr1fl9hvnaqf7kbxhi3n4yenb" UNIQUE("no_tarjeta")
)
WITH (
	OIDS = False
);

ALTER TABLE "personal" OWNER TO "postgres";

CREATE TABLE "quincena" (
	"id_quincena" int4 NOT NULL,
	"fin" date NOT NULL,
	"inicio" date NOT NULL,
	CONSTRAINT "quincena_pkey" PRIMARY KEY("id_quincena")
)
WITH (
	OIDS = False
);

ALTER TABLE "quincena" OWNER TO "postgres";


ALTER TABLE "asistencia" ADD CONSTRAINT "personal_pk" FOREIGN KEY ("no_empleado")
	REFERENCES "personal"("no_empleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "departamento" ADD CONSTRAINT "jefesuperior_pk" FOREIGN KEY ("id_superior")
	REFERENCES "jefe_superior"("id_superior")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "justificante_pk" FOREIGN KEY ("id_justificante")
	REFERENCES "justificante"("id_justificante")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "empleado_pk" FOREIGN KEY ("no_empleado")
	REFERENCES "personal"("no_empleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "incidencia" ADD CONSTRAINT "quincena_pk" FOREIGN KEY ("id_quincena")
	REFERENCES "quincena"("id_quincena")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "jefe_superior" ADD CONSTRAINT "jefe_superior_pk" FOREIGN KEY ("id_superior")
	REFERENCES "jefe_superior"("id_superior")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "justificante" ADD CONSTRAINT "justificante_pk" FOREIGN KEY ("no_empleado")
	REFERENCES "personal"("no_empleado")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;

ALTER TABLE "lic_paternidad" ADD CONSTRAINT "fk9vj0xyafl1pm4ilfwdstjvqjp" FOREIGN KEY ("id_justificante")
	REFERENCES "justificante"("id_justificante")
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

ALTER TABLE "permiso_economico" ADD CONSTRAINT "fkboy7hbjmf5ffh9uylvlhqnlks" FOREIGN KEY ("id_justificante")
	REFERENCES "justificante"("id_justificante")
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

ALTER TABLE "personal" ADD CONSTRAINT "departamento_pk" FOREIGN KEY ("id_departamento")
	REFERENCES "departamento"("id_departamento")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;


