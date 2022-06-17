package paolo.bcp.currency_exchange.domain.entities

import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


data class Currency(
    val id: Long,
    val name: String,
    val abbreviation: String,
    val countryName: String,
    val flagUrl: String?,
    val status: CurrencyStatusEnum
)