package paolo.bcp.foundation.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = ExchangeRateEntity.TABLE_NAME,
    indices = [
        Index(value = [ExchangeRateEntity.FIELD_BASE_CURRENCY_ID]),
        Index(value = [ExchangeRateEntity.FIELD_EXCHANGE_CURRENCY_ID]),
    ],
    primaryKeys = [
        ExchangeRateEntity.FIELD_BASE_CURRENCY_ID,
        ExchangeRateEntity.FIELD_EXCHANGE_CURRENCY_ID
    ]
)
data class ExchangeRateEntity(
    @ColumnInfo(name = FIELD_BASE_CURRENCY_ID)
    val baseCurrencyId: Long,
    @ColumnInfo(name = FIELD_EXCHANGE_CURRENCY_ID)
    val exchangeCurrencyId: Long,
    @ColumnInfo(name = FIELD_EXCHANGE_RATE_PURCHASE)
    val purchaseRate: Double,
    @ColumnInfo(name = FIELD_EXCHANGE_RATE_SALE)
    val saleRate: Double
) {

    companion object {
        internal const val TABLE_NAME = "exchangeRate"

        internal const val FIELD_BASE_CURRENCY_ID = "base_currency_id"
        internal const val FIELD_EXCHANGE_CURRENCY_ID = "exchange_currency_id"
        internal const val FIELD_EXCHANGE_RATE_PURCHASE = "purchase_rate"
        internal const val FIELD_EXCHANGE_RATE_SALE = "sale_rate"
    }

}