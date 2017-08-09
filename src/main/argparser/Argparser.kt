package argparser

import argparser.exception.NotAllowedValueException
import argparser.exception.NotDeclaredException

/**
 * This is a simple argument parser for command line
 * tools.
 */
class Argparser(var description: String = "", var programName: String = "") {

    private var options: HashMap<String, Argument> = HashMap()

    /**
     * Add an option to the parser.
     *
     * @param description Description
     * @param option The name of the option
     * @param hasValue Should there be a value after the option
     */
    fun addArgument(description: String, option: String, defaultValue: String = "",
                    hasValue: Boolean = true) {
        val a = Argument(
                description = description,
                hasValue = hasValue,
                value = defaultValue
        )

        options.put(option.replace("--", ""), a)
    }

    /**
     * Parse the arguments passed to the program.
     *
     * @param args Arguments from the command line
     */
    fun parse(args: Array<String>): Map<String, String> {
        var cmd: String
        var lastArg: Argument? = null
        // Is there a help option?
        if (args.contains("--help")) {
            printHelp()
            return emptyMap()
        }

        // Parse each argument
        args.forEach { arg ->
            // Is it a option?
            if (arg.startsWith("--")) {
                cmd = arg.replace("--", "")

                if (!options.containsKey(cmd))
                    throw NotDeclaredException("The option '$arg' was not declared.")

                lastArg = options[cmd]
                lastArg?.isSet = true
            } else {
                if (lastArg !== null) {
                    if (lastArg!!.hasValue)
                        lastArg!!.value += "$arg "
                    else
                        throw NotAllowedValueException("There mustn't be a value after the option ${lastArg!!.value}")
                }
            }
        }

        return transformOptions()
    }

    /**
     * Print all options and values if the command
     * 'help' was used.
     */
    private fun printHelp() {
        if (programName.isNotEmpty())
            println("usage:\n\t$programName [option_1] [value_1] ...")

        println("")
        println("description:\n\t$description")
        println("")
        println("arguments: ")
        options.forEach { k, v ->
            println("\t--$k\t\t${v.description}")
        }
    }

    /**
     * Transform the options list into a map.
     *
     * @return option list as map
     */
    private fun transformOptions(): Map<String, String> {
        val result: HashMap<String, String> = HashMap()

        options.forEach { k, v ->
            result.put(k, v.value)
        }

        return result
    }
}