package totenhund.com.kazanexpresstest.util

import java.lang.Exception

object Notation {

    fun stringFloatToExponentialNotation(amount: String): String {
        amount.toFloatOrNull()?.let {
            return it.toString()
        }

        throw Exception("Amount is not Number")
    }

}