package org.pupuseria.validators

import org.pupuseria.models.Orden

class OrdenValidator : Validator<Orden> {
    override fun validar(item: Orden): Boolean {
        return item.nombreCliente.isNotBlank() &&
                item.pupusas.isNotEmpty() &&
                item.pupusas.all { it.cantidad > 0 }
    }
}