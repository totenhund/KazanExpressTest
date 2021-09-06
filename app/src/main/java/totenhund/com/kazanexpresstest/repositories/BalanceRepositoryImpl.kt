package totenhund.com.kazanexpresstest.repositories

import retrofit2.Call
import totenhund.com.kazanexpresstest.data.network.api.BalanceApi
import totenhund.com.kazanexpresstest.data.network.results.HistoryResult
import totenhund.com.kazanexpresstest.data.network.results.WalletResult

class BalanceRepositoryImpl(private val balanceApi: BalanceApi?) : BalanceRepository {
    override fun getAllWallets(): Call<WalletResult>? = balanceApi?.getWallets()

    override fun getHistory(): Call<HistoryResult>? = balanceApi?.getHistory()

}