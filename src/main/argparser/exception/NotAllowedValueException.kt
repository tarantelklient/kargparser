package argparser.exception

/**
 * The exception will be thrown if the user passed a
 * value to a valueless argument
 *
 * @param message A message for the exception
 */
class NotAllowedValueException(message: String) : Exception(message)
