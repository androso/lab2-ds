package org.pupuseria.services

import org.pupuseria.datastructures.CustomQueue
import org.pupuseria.datastructures.CustomStack
import org.pupuseria.models.Orden
import org.pupuseria.validators.OrdenValidator

class OrdenServiceImpl : OrdenService {
    // Utilizamos Queue para órdenes pendientes porque necesitamos garantizar
    // que se procesen en el orden en que llegaron (FIFO)
    private val ordenesPendientes = CustomQueue<Orden>()
    // Utilizamos Stack para órdenes completadas porque queremos ver
    // las más recientes primero (LIFO)
    private val ordenesCompletadas = CustomStack<Orden>()
    private val validator = OrdenValidator()

    override fun registrarOrden(orden: Orden) {
        if (!validator.validar(orden)){
            throw IllegalArgumentException("Orden inválida: debe tener un cliente y al menos una pupusa, cuyo nombre no puede ser un digito")
        } else {
            ordenesPendientes.enqueue(orden)
        }
    }

    override fun obtenerOrdenesPendientes(): List<Orden> {
        // Creamos una cola temporal para no modificar la original mientras la leemos
        // Esto previene problemas de concurrencia y mantiene la integridad de los datos
        val tempQueue = CustomQueue<Orden>()
        val ordenList = mutableListOf<Orden>()

        // Copiar elementos a una lista temporal y a una nueva cola
        while (!ordenesPendientes.isEmpty()) {
            val orden = ordenesPendientes.dequeue()
            orden?.let {
                ordenList.add(it)
                tempQueue.enqueue(it)
            }
        }

        // Restaurar la cola original
        while (!tempQueue.isEmpty()) {
            tempQueue.dequeue()?.let { ordenesPendientes.enqueue(it) }
        }

        return ordenList
    }

    override fun despacharOrden(): Orden? {
        val orden = ordenesPendientes.dequeue()
        if (orden != null) {
            ordenesCompletadas.push(orden)
        }
        return orden
    }

    override fun obtenerOrdenesCompletadas(): List<Orden> {
        return ordenesCompletadas.toList()
    }
}