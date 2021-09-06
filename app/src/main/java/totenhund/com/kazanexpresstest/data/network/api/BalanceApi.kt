package totenhund.com.kazanexpresstest.data.network.api

import retrofit2.Call
import retrofit2.http.GET
import totenhund.com.kazanexpresstest.data.network.entities.HistoryOperation
import totenhund.com.kazanexpresstest.data.network.entities.Wallet
import totenhund.com.kazanexpresstest.data.network.results.HistoryResult
import totenhund.com.kazanexpresstest.data.network.results.WalletResult

interface BalanceApi {

    @GET("wallets")
    fun getWallets(): Call<WalletResult>

    @GET("histories")
    fun getHistory(): Call<HistoryResult>

}