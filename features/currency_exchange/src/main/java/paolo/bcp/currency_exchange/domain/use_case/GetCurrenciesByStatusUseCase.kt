package paolo.bcp.currency_exchange.domain.use_case

import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


class GetCurrenciesByStatusUseCase(private val currencyExchangeRepository: CurrencyExchangeRepository) {

    suspend operator fun invoke(objectiveCurrencyStatus: CurrencyStatusEnum): List<ExchangeRateWithCurrencies> {
        return currencyExchangeRepository.getExchangeRatesOfCurrencyByStatus(objectiveCurrencyStatus)
    }

}