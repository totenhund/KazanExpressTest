package totenhund.com.kazanexpresstest.ui.balance.history.adapter

import androidx.recyclerview.widget.DiffUtil
import totenhund.com.kazanexpresstest.data.network.entities.HistoryOperation


class HistoryDiffUtils(
    private val old: List<HistoryOperation>,
    private val new: List<HistoryOperation>
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