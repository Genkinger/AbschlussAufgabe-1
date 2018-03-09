package edu.kit.informatik.genkinger.controller;

/**
 * This enum represents the possible errors that can occur while parsing a {@link Command}
 *
 * @author Lukas Genkinger
 * @see CommandParser
 */
public enum ErrorCode {
    /**
     * Invalid parameter type
     */
    INVALID_PARAMETER,

    /**
     * Invalid parameter count
     */
    INVALID_PARAMETER_COUNT,

    /**
     * Malformed input (shouldn't occur)
     */
    MALFORMED_COMMAND,

    /**
     * Extraneous whitespace in or around a command
     */
    EXTRANEOUS_WHITESPACE,

    /**
     * Unknown command
     */
    UNKNOWN_COMMAND,

    /**
     * OK
     */
    OK
}
