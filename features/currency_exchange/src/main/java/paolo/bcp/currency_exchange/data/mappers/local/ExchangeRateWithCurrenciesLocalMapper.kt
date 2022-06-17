package paolo.bcp.currency_exchange.data.mappers.local

import paolo.bcp.currency_exchange.domain.entities.ExchangeRateWithCurrencies
import paolo.bcp.foundation.database.dtos.embedded.ExchangeRateWithCurrenciesEntity
import paolo.bcp.foundation.mappers.Mapper


class ExchangeRateWithCurrenciesLocalMapper(
    private val currencyLocalMapper: CurrencyLocalMapper,
    private val exchangeRateLocalMapper: ExchangeRateLocalMapper
): Mapper<ExchangeRateWithCurrenciesEntity, ExchangeRateWithCurrencies>() {

    override fun reverseMap(value: ExchangeRateWithCurrencies) = ExchangeRateWithCurrenciesEntity(
        exchangeRate = exchangeRateLocalMapper.reverseMap(value.exchangeRate),
        baseCurrency = currencyLocalMapper.reverseMap(value.baseCurrency),
        exchangeCurrency = currencyLocalMapper.reverseMap(value.exchangeCurrency)
    )

    override fun map(value: ExchangeRateWithCurrenciesEntity) = ExchangeRateWithCurrencies(
        exchangeRate = exchangeRateLocalMapper.map(value.exchangeRate),
        baseCurrency = currencyLocalMapper.map(value.baseCurrency),
        exchangeCurrency = currencyLocalMapper.map(value.exchangeCurrency)
    )

}