package paolo.bcp.currency_exchange.domain.entities


data class ExchangeRateWithCurrencies(
    val exchangeRate: ExchangeRate,
    val baseCurrency: Currency,
    val exchangeCurrency: Currency
)