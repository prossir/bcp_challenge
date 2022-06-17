package paolo.bcp.currency_exchange.platform.front.common.mappers

import paolo.bcp.currency_exchange.domain.entities.Currency
import paolo.bcp.currency_exchange.platform.front.common.dtos.CurrencyModel
import paolo.bcp.foundation.mappers.Mapper


class CurrencyMapper: Mapper<Currency, CurrencyModel>() {

    override fun reverseMap(value: CurrencyModel) = Currency(
        id = value.id,
        name = value.name,
        abbreviation = value.abbreviation,
        countryName = value.countryName,
        flagUrl = value.flagUrl,
        status = value.status
    )

    override fun map(value: Currency) = CurrencyModel(
        id = value.id,
        name = value.name,
        abbreviation = value.abbreviation,
        countryName = value.countryName,
        flagUrl = value.flagUrl,
        status = value.status
    )

}