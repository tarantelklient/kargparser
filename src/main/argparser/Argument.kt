package argparser

data class Argument(
        var description: String = "",
        internal val hasValue: Boolean = false,
        internal var isSet: Boolean = false,
        var value: String = ""
)