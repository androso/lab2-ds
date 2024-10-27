package org.pupuseria.validators

import org.pupuseria.models.Orden
import org.pupuseria.models.Pupusa

class OrdenValidator : Validator<Orden> {
    override fun validar(item: Orden): Boolean {
        return validarNombreCliente(item.nombreCliente) &&
            validarListaPupusas(item.pupusas)

    }

    private fun validarNombreCliente(nombre: String): Boolean {
        return nombre.isNotBlank()
    }

    private fun validarListaPupusas(pupusas: List<Pupusa>): Boolean {
        return pupusas.isNotEmpty() && pupusas.all { pupusa ->
            validarPupusa(pupusa)
        }
    }

    private fun validarPupusa(pupusa: Pupusa): Boolean {
        return pupusa.tipo.isNotBlank() &&
                !pupusa.tipo.any { it.isDigit() } &&
                pupusa.cantidad > 0
    }
}