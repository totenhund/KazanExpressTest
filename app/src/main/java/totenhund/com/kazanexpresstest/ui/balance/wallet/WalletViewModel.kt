package totenhund.com.kazanexpresstest.ui.balance.wallet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import totenhund.com.kazanexpresstest.data.network.Common
import totenhund.com.kazanexpresstest.data.network.entities.Wallet
import totenhund.com.kazanexpresstest.data.network.results.WalletResult
import totenhund.com.kazanexpresstest.domain.interactors.BalanceInteractor
import totenhund.com.kazanexpresstest.repositories.BalanceRepositoryImpl

class WalletViewModel: ViewModel() {

    private var _allWallets = MutableLiveData<List<Wallet>>()
    val allWallets: LiveData<List<Wallet>>
        get() = _allWallets

    private val balanceInteractor = BalanceInteractor(BalanceRepositoryImpl(Common.apiModule))

    init {
        balanceInteractor.setWallets(_allWallets)
    }

}