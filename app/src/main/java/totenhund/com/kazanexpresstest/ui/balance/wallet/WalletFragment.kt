package totenhund.com.kazanexpresstest.ui.balance.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.connection_layout.view.*
import totenhund.com.kazanexpresstest.R
import totenhund.com.kazanexpresstest.databinding.FragmentWalletBinding
import totenhund.com.kazanexpresstest.ui.balance.wallet.adapter.WalletsAdapter
import totenhund.com.kazanexpresstest.util.isConnected


class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var walletsAdapter: WalletsAdapter
    private val viewModel: WalletViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_wallet,
            container,
            false
        )

        initializeWalletAdapter()

        checkForConnection()

        binding.incldNoConnection.btn_try_again.setOnClickListener {
            checkForConnection()
        }

        return binding.root
    }


    private fun checkForConnection(){
        if(!requireContext().isConnected()){
            binding.incldNoConnection.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        }else{
            binding.incldNoConnection.visibility = View.GONE
            fillWallets()
        }
    }


    private fun fillWallets(){
        viewModel.allWallets.observe(viewLifecycleOwner, {
            walletsAdapter.setWallets(it)
            binding.pbLoading.visibility = View.GONE
        })
    }

    private fun initializeWalletAdapter() {
        binding.rvWallet.layoutManager = LinearLayoutManager(requireContext())
        walletsAdapter = WalletsAdapter()
        binding.rvWallet.adapter = walletsAdapter
    }

}