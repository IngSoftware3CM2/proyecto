package com.is.controlincidencias.converter;

import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.model.PermisoEconomicoModel;
import org.springframework.stereotype.Component;

@Component("permisoEconomicoConverter")
public class PermisoEconomicoConverter {
    public PermisoEconomico converterPermisoEconomicoModelToPermisoEconomico(PermisoEconomicoModel permisoEconomicoModel) {
        PermisoEconomico permisoEconomico = new PermisoEconomico();
        permisoEconomico.setFechaIncidencia(StringToLocalDate.tryParseDate(permisoEconomicoModel.getFechaIncidencia()));
        return permisoEconomico;
    }

    public PermisoEconomicoModel converterPermisoEconomicoToPermisoEconomicoModel(PermisoEconomico permisoEconomico) {
        PermisoEconomicoModel permisoEconomicoModel = new PermisoEconomicoModel();
        permisoEconomicoModel.setFechaIncidencia(permisoEconomico.getFechaIncidencia().toString());
        permisoEconomicoModel.setIdjustificante(permisoEconomico.getJustificante().getIdJustificante());
        return permisoEconomicoModel;
    }
}
