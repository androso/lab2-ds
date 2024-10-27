package org.pupuseria.services

import org.pupuseria.models.Orden

interface OrdenService {
    fun registrarOrden(orden: Orden)
    fun obtenerOrdenesPendientes(): List<Orden>
    fun despacharOrden(): Orden?
    fun obtenerOrdenesCompletadas(): List<Orden>
}