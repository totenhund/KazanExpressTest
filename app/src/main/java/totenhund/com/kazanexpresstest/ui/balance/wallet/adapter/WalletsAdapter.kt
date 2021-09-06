package totenhund.com.kazanexpresstest.ui.balance.wallet.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import totenhund.com.kazanexpresstest.R
import totenhund.com.kazanexpresstest.data.network.entities.Wallet
import totenhund.com.kazanexpresstest.databinding.WalletItemBinding
import totenhund.com.kazanexpresstest.util.Notation.stringFloatToExponentialNotation

class WalletsAdapter(private var wallets: List<Wallet> = listOf(), private val preview: Boolean = false): RecyclerView.Adapter<WalletsAdapter.WalletViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder = WalletViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.wallet_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) = holder.binding.run {
        tvCardName.text = wallets[position].walletName
        tvBalance.text = stringFloatToExponentialNotation(wallets[position].balance)

        if(preview){
            walletDivider.visibility = View.GONE
        }

        executePendingBindings()
    }

    override fun getItemCount(): Int = wallets.size


    fun setWallets(wallets: List<Wallet>){

        val newWallets = if(preview){
            wallets.subList(0, 3)
        }else{
            wallets
        }

        val result = DiffUtil.calculateDiff(WalletDiffUtils(this.wallets, newWallets))
        result.dispatchUpdatesTo(this)
        this.wallets = newWallets
    }

    inner class WalletViewHolder(val binding: WalletItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                Log.e("Wallet", "Wallet ${wallets[adapterPosition].walletName} is clicked")
            }
        }
    }

}
