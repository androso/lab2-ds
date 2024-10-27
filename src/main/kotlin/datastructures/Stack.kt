package org.pupuseria.datastructures

class CustomStack<T> {
    private var top: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean = size == 0

    fun size(): Int = size

    // Agregar elemento al tope de la pila
    fun push(value: T) {
        val newNode = Node(value)
        newNode.next = top
        top = newNode
        size++
    }

    // Remover y retornar el elemento del tope
    fun pop(): T? {
        if (isEmpty()) return null

        val value = top?.value
        top = top?.next
        size--
        return value
    }

    // Ver el elemento del tope sin removerlo
    fun peek(): T? = top?.value

    // Convertir la pila a una lista (útil para mostrar órdenes despachadas)
    fun toList(): List<T> {
        val list = mutableListOf<T>()
        var current = top
        while (current != null) {
            list.add(current.value)
            current = current.next
        }
        return list
    }

    override fun toString(): String {
        if (isEmpty()) return "[]"

        val result = StringBuilder("[")
        var current = top
        while (current != null) {
            result.append(current.value)
            if (current.next != null) {
                result.append(", ")
            }
            current = current.next
        }
        result.append("]")
        return result.toString()
    }
}