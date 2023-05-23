package validator

import validator.error.FormatError
import validator.error.ValidationError
import java.lang.reflect.Field

class RangeValidator : Validator<Any?> {

    override fun validate(field: Field, value: Any?): ValidationError? =
        if ((value as Int) < 1 || value > 33) FormatError("${field.name} field is out of range (1,33)") else null

}