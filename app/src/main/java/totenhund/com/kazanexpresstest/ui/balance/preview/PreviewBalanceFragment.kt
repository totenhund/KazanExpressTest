package totenhund.com.kazanexpresstest.ui.balance.preview

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.connection_layout.view.*
import totenhund.com.kazanexpresstest.R
import totenhund.com.kazanexpresstest.databinding.FragmentPreviewBalanceBinding
import totenhund.com.kazanexpresstest.ui.balance.history.HistoryViewModel
import totenhund.com.kazanexpresstest.ui.balance.history.adapter.HistoryAdapter
import totenhund.com.kazanexpresstest.ui.balance.wallet.WalletViewModel
import totenhund.com.kazanexpresstest.ui.balance.wallet.adapter.WalletsAdapter
import totenhund.com.kazanexpresstest.util.isConnected


class PreviewBalanceFragment : Fragment() {

    private lateinit var binding: FragmentPreviewBalanceBinding

    private val walletViewModel: WalletViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    private lateinit var walletsAdapter: WalletsAdapter
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_preview_balance,
            container,
            false
        )

        // preview wallets and history
        initWalletAdapter()
        initHistoryAdapter()
        setButtonsControls()
        checkForConnection()

        return binding.root
    }


    private fun checkForConnection() {
        if (!requireContext().isConnected()) {
            binding.incldNoConnection.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.incldNoConnection.visibility = View.GONE
            fillWallets()
            fillOperations()
        }
    }


    private fun setButtonsControls() {
        binding.btnHistoryShowMore.setOnClickListener {
            findNavController().navigate(PreviewBalanceFragmentDirections.showMoreHistory())
        }

        binding.btnWalletsShowMore.setOnClickListener {
            findNavController().navigate(PreviewBalanceFragmentDirections.showMoreWallets())
        }

        binding.incldNoConnection.btn_try_again.setOnClickListener {
            checkForConnection()
        }

    }

    private fun initWalletAdapter() {
        binding.rvWallet.layoutManager = LinearLayoutManager(requireContext())
        walletsAdapter = WalletsAdapter(preview = true)
        binding.rvWallet.adapter = walletsAdapter
    }

    private fun initHistoryAdapter() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        historyAdapter = HistoryAdapter(preview = true)
        binding.rvHistory.adapter = historyAdapter
    }

    private fun fillWallets() {
        walletViewModel.allWallets.observe(viewLifecycleOwner, {
            walletsAdapter.setWallets(it)
            binding.pbLoading.visibility = View.GONE
        })
    }

    private fun fillOperations() {
        historyViewModel.allOperations.observe(viewLifecycleOwner, {
            historyAdapter.setOperations(it)
            binding.pbLoading.visibility = View.GONE
        })
    }

}