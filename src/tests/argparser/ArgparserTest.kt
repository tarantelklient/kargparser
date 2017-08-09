package argparser

import argparser.exception.NotDeclaredException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ArgparserTest {

    @Test
    @DisplayName("The argparser should be able to parse")
    fun parse() {
        val p = Argparser()
        p.addArgument("command to execute", "--command", "gulp build")
        p.addArgument("path ", "--path")

        val args: Array<String> = "--path asda  --command gulp build".split(" ").toTypedArray()

        val parsed = p.parse(args)
        assertFalse(parsed.isEmpty(), "It should not be empty")
    }

    @Test
    @DisplayName("The help should be printed")
    fun help() {
        val p = Argparser()
        p.description = "This is a simple test..."
        p.programName = "ArgparserTest"
        p.addArgument("command to execute", "--command", "gulp build")
        val args: Array<String> = "--help a --command gulp build".split(" ").toTypedArray()
        val parsed = p.parse(args)

        assertTrue(parsed.isEmpty(), "There mustn't be any elements in the map")
    }

    @Test
    @DisplayName("A option without value should be recognized")
    fun optionWithoutValue() {
        val p = Argparser()
        p.addArgument("Test", "--test123", hasValue = false)
        val args: Array<String> = Array(1, { "--test123" })
        val parsed = p.parse(args)

        assertTrue(parsed.containsKey("test123"), "It should contain the key")
    }

    @Test
    fun optionNotDeclared() {
        val p = Argparser()
        val args: Array<String> = Array(1, { "--test123" })

        Assertions.assertThrows(NotDeclaredException::class.java, {
            val parsed = p.parse(args)
        })
    }


}