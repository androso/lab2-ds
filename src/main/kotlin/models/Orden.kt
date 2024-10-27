package org.pupuseria.models

data class Orden (
    val nombreCliente: String,
    val pupusas: List<Pupusa>
) {
    override fun toString(): String {
        return "$nombreCliente - ${pupusas.joinToString(", ") { "${it.cantidad} pupusas de ${it.tipo}"}}"
    }
}

