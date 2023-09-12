package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke() = repository.loadData()
}