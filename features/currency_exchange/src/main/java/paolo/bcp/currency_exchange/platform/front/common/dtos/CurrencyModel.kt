package paolo.bcp.currency_exchange.platform.front.common.dtos

import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


class CurrencyModel(
    val id: Long,
    val name: String,
    val abbreviation: String,
    val countryName: String,
    val flagUrl: String?,
    val status: CurrencyStatusEnum
) {

    val flagDescription: String
        get() = "This is the flag of $countryName"

}