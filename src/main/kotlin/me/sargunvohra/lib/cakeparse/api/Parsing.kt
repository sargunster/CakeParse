package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.Result
import me.sargunvohra.lib.common.cached

/**
 * Apply the provided parser on the input sequence until the parser is satisfied. Remaining input is allowed.
 *
 * @param parser the target rule to satisfy.
 *
 * @return the result of the parse.
 */
fun <A> Sequence<TokenInstance>.parseToGoal(parser: Parser<A>) = parser.eat(this.cached())

/**
 * Apply the provided parser on the input sequence and make sure all input is consumed. Remaining input is not allowed.
 *
 * @param parser the target rule to satisfy.
 *
 * @return the result of the parse.
 *
 * @throws UnexpectedTokenException when the parser does not consume the entire input.
 */
fun <A> Sequence<TokenInstance>.parseToEnd(parser: Parser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.filter { !it.type.ignore }.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}