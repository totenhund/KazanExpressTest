package totenhund.com.kazanexpresstest.ui.balance.wallet.adapter

import androidx.recyclerview.widget.DiffUtil
import totenhund.com.kazanexpresstest.data.network.entities.Wallet

class WalletDiffUtils(
    private val old: List<Wallet>,
    private val new: List<Wallet>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }


}