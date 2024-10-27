package org.pupuseria
import org.pupuseria.models.Orden
import org.pupuseria.models.Pupusa
import org.pupuseria.services.OrdenService

// Inyectamos OrdenService en lugar de crear una instancia
// para  permitir diferentes implementaciones
class MenuUI(private val ordenService: OrdenService) {
    fun iniciar() {
        while (true) {
            mostrarMenu()
            when (val opcion = leerOpcion()) {
                1 -> registrarNuevaOrden()
                2 -> mostrarOrdenesPendientes()
                3 -> despacharOrden()
                4 -> mostrarOrdenesCompletadas()
                5 -> {
                    println("¡Gracias por usar el sistema!")
                    break
                }
                else -> println("Opción inválida: $opcion. Por favor, intente de nuevo.")
            }
            println("\nPresione Enter para continuar...")
            readlnOrNull()
        }
    }

    private fun mostrarMenu() {
        println("""
            |=== Bienvenido a la Pupusería "El Comalito" ===
            |Seleccione una opción:
            |1. Registrar una nueva orden
            |2. Ver órdenes pendientes
            |3. Despachar orden
            |4. Ver órdenes despachadas
            |5. Salir
            |""".trimMargin())
    }

    private fun leerOpcion(): Int {
        print("Ingrese su opción: ")
        return try {
            readlnOrNull()?.toIntOrNull() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun registrarNuevaOrden() {
        println("\n=== Registrar Nueva Orden ===")
        print("Ingrese el nombre del cliente: ")
        val nombreCliente = readlnOrNull()?.trim() ?: return

        print("¿Cuántos tipos de pupusas desea ordenar?: ")
        val cantidadTipos = readlnOrNull()?.toIntOrNull() ?: 0

        if (cantidadTipos <= 0) {
            println("Cantidad inválida de tipos de pupusas.")
            return
        }

        val pupusas = mutableListOf<Pupusa>()
        for (i in 1..cantidadTipos) {
            println("\nPupusa #$i")
            print("Ingrese el tipo de pupusa: ")
            val tipo = readlnOrNull()?.trim() ?: continue

            print("Ingrese la cantidad de pupusas de $tipo: ")
            val cantidad = readlnOrNull()?.toIntOrNull() ?: continue

            if (cantidad > 0) {
                pupusas.add(Pupusa(tipo, cantidad))
            }
        }
        try {
            val orden = Orden(nombreCliente, pupusas)
            ordenService.registrarOrden(orden)
            println("\nOrden registrada exitosamente: $orden")
        } catch(e: IllegalArgumentException) {
            println("\nError: ${e.message}")
        }
    }

    private fun mostrarOrdenesPendientes() {
        println("\n=== Órdenes Pendientes ===")
        val ordenesPendientes = ordenService.obtenerOrdenesPendientes()
        if (ordenesPendientes.isEmpty()) {
            println("No hay órdenes pendientes.")
            return
        }

        ordenesPendientes.forEachIndexed { index, orden ->
            println("${index + 1}. $orden")
        }
    }

    private fun despacharOrden() {
        println("\n=== Despachar Orden ===")
        val orden = ordenService.despacharOrden()
        if (orden != null) {
            println("Orden despachada exitosamente: $orden")
        } else {
            println("No hay órdenes pendientes para despachar.")
        }
    }

    private fun mostrarOrdenesCompletadas() {
        println("\n=== Órdenes Despachadas ===")
        val ordenesCompletadas = ordenService.obtenerOrdenesCompletadas()
        if (ordenesCompletadas.isEmpty()) {
            println("No hay órdenes despachadas.")
            return
        }

        ordenesCompletadas.forEachIndexed { index, orden ->
            println("${index + 1}. $orden")
        }
    }
}