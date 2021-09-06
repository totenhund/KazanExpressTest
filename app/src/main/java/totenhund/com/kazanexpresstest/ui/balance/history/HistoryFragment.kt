package totenhund.com.kazanexpresstest.ui.balance.history

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
import totenhund.com.kazanexpresstest.databinding.FragmentHistoryBinding
import totenhund.com.kazanexpresstest.ui.balance.history.adapter.HistoryAdapter
import totenhund.com.kazanexpresstest.util.isConnected


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,
            container,
            false
        )

        initHistoryAdapter()

        checkForConnection()

        binding.incldNoConnection.btn_try_again.setOnClickListener {
            checkForConnection()
        }


        return binding.root
    }


    private fun checkForConnection() {
        if (!requireContext().isConnected()) {
            binding.incldNoConnection.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.incldNoConnection.visibility = View.GONE
            fillOperations()
        }
    }


    private fun fillOperations() {
        historyViewModel.allOperations.observe(viewLifecycleOwner, {
            historyAdapter.setOperations(it)
            binding.pbLoading.visibility = View.GONE
        })
    }

    private fun initHistoryAdapter() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        historyAdapter = HistoryAdapter()
        binding.rvHistory.adapter = historyAdapter
    }

}