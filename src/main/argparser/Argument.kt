package argparser

/**
 * Data class for each argument
 *
 * @param defaultValue Default value
 * @param description Description/help
 * @param hasValue Define if the argument expects value
 * @param isSet internal flag to check if the argument was passed
 * @param optional Flag for optional arguments
 * @param value The passed value
 */
data class Argument(
        internal val defaultValue: String = "",
        var description: String = "",
        internal val hasValue: Boolean = false,
        internal var isSet: Boolean = false,
        var optional: Boolean = false,
        var value: String = ""
)
