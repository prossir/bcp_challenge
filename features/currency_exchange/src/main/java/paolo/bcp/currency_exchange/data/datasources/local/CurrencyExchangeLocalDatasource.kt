package paolo.bcp.currency_exchange.data.datasources.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import org.threeten.bp.OffsetDateTime
import paolo.bcp.foundation.database.dtos.CurrencyEntity
import paolo.bcp.foundation.database.dtos.embedded.ExchangeRateWithCurrenciesEntity
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum
import paolo.bcp.foundation.database.providers.DaoProvider
import paolo.bcp.foundation.dates.DateTimeProvider


class CurrencyExchangeLocalDatasource(
    daoProvider: DaoProvider,
    private val dateTimeProvider: DateTimeProvider<OffsetDateTime>
) {

    private val currencyDao = daoProvider.getCurrencyDao()
    private val exchangeRateDao = daoProvider.getExchangeRateDao()

    fun retrieveCurrencyLiveByStatus(status: CurrencyStatusEnum): LiveData<CurrencyEntity?> {
        return currencyDao.findLiveByStatus(status)
    }

    fun retrieveExchangeRatesOfCurrencyByStatus(status: CurrencyStatusEnum): List<ExchangeRateWithCurrenciesEntity> {
        currencyDao.findByStatus(status)!!.apply {
            return exchangeRateDao.findAllOfSenderCurrency(this.id)
        }
    }

    fun updateCurrencyById(currencyId: Long, status: CurrencyStatusEnum) {
        currencyDao.updateCurrentUniqueByCurrencyStatus(dateTimeProvider, currencyId, status)
    }

    fun retrieveLiveCurrentExchangeRate(): LiveData<ExchangeRateWithCurrenciesEntity?> {
        return Transformations.switchMap(currencyDao.findLiveByStatus(CurrencyStatusEnum.SENT_CURRENCY)) { senderCurrency ->
            exchangeRateDao.findLiveCurrent(senderCurrency!!.id)
        }
    }

    fun invertCurrentCurrencies() {
        val senderCurrency = currencyDao.findByStatus(CurrencyStatusEnum.SENT_CURRENCY)!!
        val receivedCurrency = currencyDao.findByStatus(CurrencyStatusEnum.RECEIVED_CURRENCY)!!
        currencyDao.invertCurrenciesStatus(dateTimeProvider, senderCurrency.id, receivedCurrency.id)
    }

}