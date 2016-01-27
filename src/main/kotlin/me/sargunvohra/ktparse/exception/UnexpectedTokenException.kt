package me.sargunvohra.ktparse.exception

import me.sargunvohra.ktparse.lexer.ITokenType
import me.sargunvohra.ktparse.lexer.Token

class UnexpectedTokenException(
        val expected: ITokenType?,
        val got: Token
): ParseException(
        "Expected ${expected?.let {"'${expected.name}' token"} ?: "end of file"},"
                + " but got '${got.type.name}' at position ${got.position}"
)