-- =============================================================================
-- Diagram Name: Diagrama R
-- Created on: 23/04/2018 16:25:44
-- Diagram Version: 
-- =============================================================================


DROP TABLE IF EXISTS "login" CASCADE;

CREATE TABLE "login" (
	"correo" varchar(70),
	"idusuario" int4 NOT NULL,
	"passwordsalt" varchar(255),
	"passwordhash" varchar(255),
	"PersonalnoEmpleado" int4 NOT NULL,
	PRIMARY KEY("idusuario")
);

DROP TABLE IF EXISTS "personal" CASCADE;

CREATE TABLE "personal" (
	"nombre" varchar(60),
	"apellidopaterno" varchar(30),
	"apellidomaterno" varchar(60),
	"noempleado" int4 NOT NULL,
	"idusuario" int4,
	PRIMARY KEY("noempleado")
);


ALTER TABLE "personal" ADD CONSTRAINT "Ref_Personal_to_Login" FOREIGN KEY ("idusuario")
	REFERENCES "login"("idusuario")
	MATCH SIMPLE
	ON DELETE CASCADE
	ON UPDATE CASCADE
	NOT DEFERRABLE;


