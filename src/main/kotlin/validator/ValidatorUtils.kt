package validator

import org.apache.commons.lang3.reflect.FieldUtils
import validator.error.ValidationError
import validator.exception.CouldNotValidateException
import java.lang.reflect.Field

object ValidatorUtils {

    fun validate(value: Any?): List<ValidationError> {
        if (value == null) throw CouldNotValidateException()
        val errors = mutableListOf<ValidationError>()
        getFields(value.javaClass).forEach { field: Field ->
            if (field.isAnnotationPresent(ValidateWith::class.java)) {
                val annotation = field.getAnnotation(ValidateWith::class.java)
                if (Validator::class.java.isAssignableFrom(annotation.validator.java)) {
                    errors += validateFields(field, FieldUtils.readField(field, value, true), annotation)
                }
            }
        }
        return errors
    }

    private fun getFields(classRef: Class<*>): List<Field> {
        return classRef.declaredFields.toList()
    }

    private fun validateFields(field: Field, value: Any, annotation: ValidateWith): List<ValidationError> {
        val validator = annotation.validator.java.getDeclaredConstructor().newInstance() as Validator<Any>
        val result = validator.validate(field, value)
        return if (result != null) {
            listOf(result)
        } else {
            emptyList()
        }
    }
}