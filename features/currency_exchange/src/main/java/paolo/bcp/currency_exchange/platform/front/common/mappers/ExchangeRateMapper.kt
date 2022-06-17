package paolo.bcp.currency_exchange.platform.front.common.mappers

import paolo.bcp.currency_exchange.domain.entities.ExchangeRate
import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateModel
import paolo.bcp.foundation.mappers.Mapper

class ExchangeRateMapper: Mapper<ExchangeRate, ExchangeRateModel>() {

    override fun reverseMap(value: ExchangeRateModel) = ExchangeRate(
        baseCurrencyId = value.baseCurrencyId,
        exchangeCurrencyId = value.exchangeCurrencyId,
        purchaseRate = value.purchaseRate,
        saleRate = value.saleRate
    )

    override fun map(value: ExchangeRate) = ExchangeRateModel(
        baseCurrencyId = value.baseCurrencyId,
        exchangeCurrencyId = value.exchangeCurrencyId,
        purchaseRate = value.purchaseRate,
        saleRate = value.saleRate
    )

}