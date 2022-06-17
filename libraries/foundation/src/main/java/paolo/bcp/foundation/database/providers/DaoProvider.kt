package paolo.bcp.foundation.database.providers

import paolo.bcp.foundation.database.daos.CurrencyDao
import paolo.bcp.foundation.database.daos.ExchangeRateDao


class DaoProvider(private val database: DatabaseProvider) {

    fun getCurrencyDao(): CurrencyDao = database.getInstance().currencyDao()
    fun getExchangeRateDao(): ExchangeRateDao = database.getInstance().exchangeRateDao()

}