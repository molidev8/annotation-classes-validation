package validator

import validator.error.FormatError
import validator.error.ValidationError
import java.lang.reflect.Field

class FormatValidator : Validator<Any?> {

    override fun validate(field: Field, value: Any?): ValidationError? =
        if ((value as String).contains("s")) null else FormatError("${field.name} field does not contain an s")
}