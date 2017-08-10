package argparser

import argparser.exception.NotDeclaredException
import argparser.exception.RequiredNotPassedException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
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
    @DisplayName("A. option without value should be recognized")
    fun optionWithoutValue() {
        val p = Argparser()
        p.addArgument("Test", "--test123", hasValue = false)
        val args: Array<String> = Array(1, { "--test123" })
        val parsed = p.parse(args)

        assertTrue(parsed.containsKey("test123"), "It should contain the key")
    }

    @Test
    @DisplayName("The arguments should be declared")
    fun argumentNotDeclared() {
        val p = Argparser()
        val args: Array<String> = Array(1, { "--test123" })

        Assertions.assertThrows(NotDeclaredException::class.java, {
            p.parse(args)
        })
    }

    @Test
    @DisplayName("Test the required arguments")
    fun optionRequired() {
        val p = Argparser()
        p.addArgument("Test 1 optional", "--test1", optional = true)
        p.addArgument("Test 2 required", "--test2")
        val args: Array<String> = "--test1 a --test2 b".split(" ").toTypedArray()

        val parsed = p.parse(args)

        assertEquals("a", parsed["test1"], "The first option should be set")
        assertEquals("b", parsed["test2"], "The second option should be set")

        val argsForFail = "--test1 a".split(" ").toTypedArray()
        Assertions.assertThrows(RequiredNotPassedException::class.java, {
            p.parse(argsForFail)
        })
    }


}