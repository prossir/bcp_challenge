package paolo.bcp.currency_exchange.domain.repositories

import androidx.lifecycle.LiveData
import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


interface CurrencyExchangeRepository {

    suspend fun getCurrentExchangeRate(): LiveData<ExchangeRateWithCurrencies?>
    suspend fun invertCurrentCurrencies()
    suspend fun getExchangeRatesOfCurrencyByStatus(status: CurrencyStatusEnum): List<ExchangeRateWithCurrencies>
    suspend fun setCurrencyByIdAsCurrent(currencyId: Long, currencyStatus: CurrencyStatusEnum)

}