package paolo.bcp.currency_exchange.domain.use_case

import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


class SelectCurrentCurrencyUseCase(private val currencyExchangeRepository: CurrencyExchangeRepository) {

    suspend operator fun invoke(currencyId: Long, currencyStatus: CurrencyStatusEnum) {
        return currencyExchangeRepository.setCurrencyByIdAsCurrent(currencyId, currencyStatus)
    }

}