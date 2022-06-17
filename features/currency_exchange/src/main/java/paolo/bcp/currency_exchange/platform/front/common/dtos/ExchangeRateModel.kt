package paolo.bcp.currency_exchange.platform.front.common.dtos

data class ExchangeRateModel(
    val baseCurrencyId: Long,
    val exchangeCurrencyId: Long,
    val purchaseRate: Double,
    val saleRate: Double
)