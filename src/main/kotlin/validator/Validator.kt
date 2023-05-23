package validator

import validator.error.ValidationError
import java.lang.reflect.Field

interface Validator<T> {
    fun validate(field: Field, value: T): ValidationError?
}