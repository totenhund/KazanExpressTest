package totenhund.com.kazanexpresstest.data.network

import totenhund.com.kazanexpresstest.data.network.api.BalanceApi

object Common {
    private const val BASE_URL = "http://www.amock.io/api/totenhund/"
    val apiModule: BalanceApi?
        get() = ApiModule.getClient(BASE_URL)?.create(BalanceApi::class.java)
}