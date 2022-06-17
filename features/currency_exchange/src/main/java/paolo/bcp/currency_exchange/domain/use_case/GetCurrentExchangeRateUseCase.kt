package paolo.bcp.currency_exchange.domain.use_case

import androidx.lifecycle.LiveData
import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository


class GetCurrentExchangeRateUseCase(private val currencyExchangeRepository: CurrencyExchangeRepository) {

    suspend operator fun invoke(): LiveData<ExchangeRateWithCurrencies?> {
        return currencyExchangeRepository.getCurrentExchangeRate()
    }

}