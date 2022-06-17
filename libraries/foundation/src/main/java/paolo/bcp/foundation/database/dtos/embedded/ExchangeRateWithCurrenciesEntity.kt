package paolo.bcp.foundation.database.dtos.embedded

import androidx.room.Embedded
import androidx.room.Relation
import paolo.bcp.foundation.database.dtos.CurrencyEntity
import paolo.bcp.foundation.database.dtos.ExchangeRateEntity


data class ExchangeRateWithCurrenciesEntity(
    @Embedded
    val exchangeRate: ExchangeRateEntity,
    @Relation(
        parentColumn = BASE_CURRENCY_ID_IDENTIFIER,
        entityColumn = CURRENCY_ID
    )
    val baseCurrency: CurrencyEntity,
    @Relation(
        parentColumn = EXCHANGE_CURRENCY_ID_IDENTIFIER,
        entityColumn = CURRENCY_ID
    )
    val exchangeCurrency: CurrencyEntity
) {

    companion object {
        internal const val BASE_CURRENCY_ID_IDENTIFIER = "base_currency_id"
        internal const val EXCHANGE_CURRENCY_ID_IDENTIFIER = "exchange_currency_id"
        internal const val CURRENCY_ID = "id"
    }

}