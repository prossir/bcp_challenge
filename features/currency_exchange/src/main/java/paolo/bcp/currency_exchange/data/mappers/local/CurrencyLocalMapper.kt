package paolo.bcp.currency_exchange.data.mappers.local

import paolo.bcp.currency_exchange.domain.entities.Currency
import paolo.bcp.foundation.database.dtos.CurrencyEntity
import paolo.bcp.foundation.mappers.Mapper


class CurrencyLocalMapper: Mapper<CurrencyEntity, Currency>() {
    /**
     * As this implies updating/creating data, we let the [insertOrUpdate()] transaction control the
     * timestamp logic
     * */
    override fun reverseMap(value: Currency) = CurrencyEntity(
        id = value.id,
        name = value.name,
        abbreviation = value.abbreviation,
        countryName = value.countryName,
        flagUrl = value.flagUrl,
        status = value.status,
        createdAt = null,
        updatedAt = null
    )

    override fun map(value: CurrencyEntity) = Currency(
        id = value.id,
        name = value.name,
        abbreviation = value.abbreviation,
        countryName = value.countryName,
        flagUrl = value.flagUrl,
        status = value.status
    )

}