package paolo.bcp.currency_exchange.domain.use_case

import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository


class InvertCurrentCurrenciesUseCase(private val currencyExchangeRepository: CurrencyExchangeRepository) {

    suspend operator fun invoke() {
        return currencyExchangeRepository.invertCurrentCurrencies()
    }

}