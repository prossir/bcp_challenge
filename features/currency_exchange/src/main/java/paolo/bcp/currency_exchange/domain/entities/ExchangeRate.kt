package paolo.bcp.currency_exchange.domain.entities


data class ExchangeRate(
    val baseCurrencyId: Long,
    val exchangeCurrencyId: Long,
    val purchaseRate: Double,
    val saleRate: Double
)