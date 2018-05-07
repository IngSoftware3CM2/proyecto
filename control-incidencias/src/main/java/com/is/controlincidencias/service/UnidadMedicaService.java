package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.entity.Zona;

import java.util.List;

public interface UnidadMedicaService {
    List<UnidadMedica> getunidadesMedicasByZona(Zona zona);
    UnidadMedica getUnidadMedicaByNombre(String nombre);
}
