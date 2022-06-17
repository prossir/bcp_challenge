package paolo.bcp.currency_exchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.bcp.currency_exchange.data.datasources.local.CurrencyExchangeLocalDatasource
import paolo.bcp.currency_exchange.data.mappers.local.ExchangeRateWithCurrenciesLocalMapper
import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


class CurrencyExchangeDataRepository(
    private val currencyExchangeLocalDatasource: CurrencyExchangeLocalDatasource,
    private val exchangeRateWithCurrenciesLocalMapper: ExchangeRateWithCurrenciesLocalMapper
): CurrencyExchangeRepository {

    override suspend fun getCurrentExchangeRate(): LiveData<ExchangeRateWithCurrencies?> {
        return Transformations.switchMap(
            currencyExchangeLocalDatasource.retrieveCurrencyLiveByStatus(CurrencyStatusEnum.SENT_CURRENCY)
        ) { sentCurrency ->
            sentCurrency?.let {
                Transformations.map(currencyExchangeLocalDatasource.retrieveLiveCurrentExchangeRate())
                { exchangeRateWithCurrencies ->
                    exchangeRateWithCurrencies?.let {
                        exchangeRateWithCurrenciesLocalMapper.map(it)
                    }
                }
            }
        }
    }

    override suspend fun invertCurrentCurrencies() {
        currencyExchangeLocalDatasource.invertCurrentCurrencies()
    }

    override suspend fun getExchangeRatesOfCurrencyByStatus(status: CurrencyStatusEnum):
            List<ExchangeRateWithCurrencies> {
        return exchangeRateWithCurrenciesLocalMapper.map(
            currencyExchangeLocalDatasource.retrieveExchangeRatesOfCurrencyByStatus(status)
        )
    }

    override suspend fun setCurrencyByIdAsCurrent(
        currencyId: Long,
        currencyStatus: CurrencyStatusEnum
    ) {
        currencyExchangeLocalDatasource.updateCurrencyById(currencyId, currencyStatus)
    }

}