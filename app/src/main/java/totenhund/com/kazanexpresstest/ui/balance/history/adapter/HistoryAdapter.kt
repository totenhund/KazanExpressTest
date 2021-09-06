package totenhund.com.kazanexpresstest.ui.balance.history.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import totenhund.com.kazanexpresstest.R
import totenhund.com.kazanexpresstest.data.network.entities.HistoryOperation
import totenhund.com.kazanexpresstest.databinding.HistoryItemBinding
import totenhund.com.kazanexpresstest.util.Notation.stringFloatToExponentialNotation


class HistoryAdapter(
    private var operations: List<HistoryOperation> = listOf(),
    private val preview: Boolean = false
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.history_item,
                parent,
                false
            ),
            parent.context
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) = holder.binding.run {
        tvOperationName.text = assignOperationName(holder.context, operations[position])
        tvSpend.text = assignOperationAmount(holder.context, operations[position])

        if (preview) {
            historyDivider.visibility = View.GONE
        }

        executePendingBindings()
    }

    override fun getItemCount(): Int = operations.size

    fun setOperations(operations: List<HistoryOperation>) {

        val newOperations = if(preview){
            operations.subList(0, 3)
        }else{
            operations
        }

        val result = DiffUtil.calculateDiff(HistoryDiffUtils(this.operations, newOperations))
        result.dispatchUpdatesTo(this)
        this.operations = newOperations
    }

    private fun assignOperationName(context: Context, operation: HistoryOperation): String {
        return if (operation.entry == "incoming") {
            context.getString(R.string.incoming_money)
        } else {
            context.getString(R.string.outgoing_money, operation.recipient)
        }
    }

    private fun assignOperationAmount(context: Context, operation: HistoryOperation): String {
        return if (operation.entry == "incoming") {
            context.getString(
                R.string.positive_operation_amount,
                stringFloatToExponentialNotation(operation.amount ?: operation.balance),
                operation.currency
            )
        } else {
            context.getString(
                R.string.negative_operation_amount,
                stringFloatToExponentialNotation(operation.amount ?: operation.balance),
                operation.currency
            )
        }
    }


    inner class HistoryViewHolder(val binding: HistoryItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                Log.e(
                    "Operation",
                    "operation ${operations[adapterPosition].recipient} is clicked"
                )
            }
        }
    }

}
