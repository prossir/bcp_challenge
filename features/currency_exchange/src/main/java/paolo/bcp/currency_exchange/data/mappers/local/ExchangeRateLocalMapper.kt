package paolo.bcp.currency_exchange.data.mappers.local

import paolo.bcp.currency_exchange.domain.entities.ExchangeRate
import paolo.bcp.foundation.database.dtos.ExchangeRateEntity
import paolo.bcp.foundation.mappers.Mapper


class ExchangeRateLocalMapper: Mapper<ExchangeRateEntity, ExchangeRate>() {

    override fun reverseMap(value: ExchangeRate) = ExchangeRateEntity(
        baseCurrencyId = value.baseCurrencyId,
        exchangeCurrencyId = value.exchangeCurrencyId,
        purchaseRate = value.purchaseRate,
        saleRate = value.saleRate
    )

    override fun map(value: ExchangeRateEntity) = ExchangeRate(
        baseCurrencyId = value.baseCurrencyId,
        exchangeCurrencyId = value.exchangeCurrencyId,
        purchaseRate = value.purchaseRate,
        saleRate = value.saleRate
    )

}