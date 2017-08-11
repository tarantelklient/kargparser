package argparser.exception

/**
 * The exception will be thrown, if a required argument
 * was not passed by the user
 *
 * @param message A message for the exception
 */
class RequiredNotPassedException(message: String) : Exception(message)
