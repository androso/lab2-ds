package org.pupuseria.datastructures

class Node<T>(val value: T, var next: Node<T>? = null)

class CustomQueue<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean = size == 0

    fun size(): Int = size

    // Agregar al final de la cola
    fun enqueue(value: T) {
        val newNode = Node(value)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    // Remover del inicio de la cola
    fun dequeue(): T? {
        if (isEmpty()) return null

        val value = head?.value
        head = head?.next
        if (head == null) {
            tail = null
        }
        size--
        return value
    }

    // Ver el primer elemento sin removerlo
    fun peek(): T? = head?.value

    override fun toString(): String {
        if (isEmpty()) return "[]"

        val result = StringBuilder("[")
        var current = head
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