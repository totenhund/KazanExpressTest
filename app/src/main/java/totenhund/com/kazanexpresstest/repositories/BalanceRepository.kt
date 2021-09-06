package totenhund.com.kazanexpresstest.repositories

import retrofit2.Call
import totenhund.com.kazanexpresstest.data.network.results.HistoryResult
import totenhund.com.kazanexpresstest.data.network.results.WalletResult

interface BalanceRepository {

    fun getAllWallets(): Call<WalletResult>?

    fun getHistory(): Call<HistoryResult>?

}