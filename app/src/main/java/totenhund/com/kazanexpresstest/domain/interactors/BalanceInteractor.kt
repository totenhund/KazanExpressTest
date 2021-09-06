package totenhund.com.kazanexpresstest.domain.interactors

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import totenhund.com.kazanexpresstest.data.network.entities.HistoryOperation
import totenhund.com.kazanexpresstest.data.network.entities.Wallet
import totenhund.com.kazanexpresstest.data.network.results.HistoryResult
import totenhund.com.kazanexpresstest.data.network.results.WalletResult
import totenhund.com.kazanexpresstest.repositories.BalanceRepository

class BalanceInteractor(private val balanceRepository: BalanceRepository) {

    fun setWallets(allWallets: MutableLiveData<List<Wallet>>) {

        balanceRepository.getAllWallets()?.enqueue(object : Callback<WalletResult> {

            override fun onFailure(call: Call<WalletResult>, t: Throwable) {
                Log.e("Connection", t.message.toString())
            }

            override fun onResponse(call: Call<WalletResult>, response: Response<WalletResult>) {
                if (response.body() != null && response.isSuccessful) {
                    response.body()?.let {
                        allWallets.value = it.wallets
                    }
                }
            }
        })

    }

    fun setHistoryOperations(allOperations: MutableLiveData<List<HistoryOperation>>) {

        balanceRepository.getHistory()?.enqueue(object : Callback<HistoryResult> {

            override fun onFailure(call: Call<HistoryResult>, t: Throwable) {
                Log.e("Connection", t.message.toString())
            }

            override fun onResponse(call: Call<HistoryResult>, response: Response<HistoryResult>) {
                if (response.body() != null && response.isSuccessful) {
                    response.body()?.let {
                        allOperations.value = it.histories
                    }
                }
            }
        })

    }
}