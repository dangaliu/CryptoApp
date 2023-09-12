package com.example.cryptoapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.presentation.CoinApp
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.presentation.viewmodel.CoinViewModel
import com.example.cryptoapp.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CoinApp).component
    }

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }
    private val binding: ActivityCoinPriceListBinding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoDTO: CoinInfo) {
                if (isOnePaneMode()) {
                    launchCoinDetailActivity(coinInfoDTO.fromSymbol)
                } else {
                    launchCoinDetailFragment(coinInfoDTO.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.coinDetailFragmentContainer == null
    }

    private fun launchCoinDetailFragment(fSym: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.coinDetailFragmentContainer!!.id, CoinDetailFragment.newInstance(fSym))
            .addToBackStack(null)
            .commit()
    }

    private fun launchCoinDetailActivity(fSym: String) {
        startActivity(CoinDetailActivity.newIntent(this, fSym))
    }
}
