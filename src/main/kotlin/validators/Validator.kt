package org.pupuseria.validators

interface Validator<T> {
    fun validar(item: T): Boolean
}