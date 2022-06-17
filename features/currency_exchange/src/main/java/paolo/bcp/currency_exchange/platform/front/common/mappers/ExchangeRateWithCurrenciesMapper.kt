package paolo.bcp.currency_exchange.platform.front.common.mappers

import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateWithCurrenciesModel
import paolo.bcp.foundation.mappers.Mapper

class ExchangeRateWithCurrenciesMapper(
    private val currencyMapper: CurrencyMapper,
    private val exchangeRateMapper: ExchangeRateMapper
): Mapper<ExchangeRateWithCurrencies, ExchangeRateWithCurrenciesModel>() {

    override fun reverseMap(value: ExchangeRateWithCurrenciesModel) = ExchangeRateWithCurrencies(
        exchangeRate = exchangeRateMapper.reverseMap(value.exchangeRate),
        baseCurrency = currencyMapper.reverseMap(value.baseCurrency),
        exchangeCurrency = currencyMapper.reverseMap(value.exchangeCurrency)
    )

    override fun map(value: ExchangeRateWithCurrencies) = ExchangeRateWithCurrenciesModel(
        exchangeRate = exchangeRateMapper.map(value.exchangeRate),
        baseCurrency = currencyMapper.map(value.baseCurrency),
        exchangeCurrency = currencyMapper.map(value.exchangeCurrency)
    )


}