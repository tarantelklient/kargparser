package argparser.exception

/**
 * The user passed an argument, which was not
 * declared.
 *
 * @param message A message for the exception
 */
class NotDeclaredException(message: String) : Exception(message)
