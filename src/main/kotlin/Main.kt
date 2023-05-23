import validator.FormatValidator
import validator.RangeValidator
import validator.ValidateWith
import validator.ValidatorUtils
class Android(
    @ValidateWith(FormatValidator::class) val brand: String?,
    @ValidateWith(RangeValidator::class) val version: Int
)

fun main() {
    val android = Android("Pixel", 34)
    println(ValidatorUtils.validate(android))
}