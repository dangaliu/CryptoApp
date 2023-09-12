package com.example.cryptoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoapp.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase.invoke()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase.invoke()
        }
    }

}