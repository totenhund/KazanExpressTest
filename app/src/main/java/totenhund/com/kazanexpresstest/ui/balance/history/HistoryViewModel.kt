package totenhund.com.kazanexpresstest.ui.balance.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import totenhund.com.kazanexpresstest.data.network.Common
import totenhund.com.kazanexpresstest.data.network.entities.HistoryOperation
import totenhund.com.kazanexpresstest.domain.interactors.BalanceInteractor
import totenhund.com.kazanexpresstest.repositories.BalanceRepositoryImpl

class HistoryViewModel: ViewModel() {

    private var _allOperations = MutableLiveData<List<HistoryOperation>>()
    val allOperations: LiveData<List<HistoryOperation>>
        get() = _allOperations

    private val balanceInteractor = BalanceInteractor(BalanceRepositoryImpl(Common.apiModule))

    init {
        balanceInteractor.setHistoryOperations(_allOperations)
    }

}