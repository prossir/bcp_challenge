package paolo.bcp.foundation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import paolo.bcp.foundation.database.converters.CurrencyStatusConverter
import paolo.bcp.foundation.database.converters.OffsetDateTimeConverter
import paolo.bcp.foundation.database.daos.CurrencyDao
import paolo.bcp.foundation.database.daos.ExchangeRateDao
import paolo.bcp.foundation.database.dtos.CurrencyEntity
import paolo.bcp.foundation.database.dtos.ExchangeRateEntity


@Database(
    entities = [
        CurrencyEntity::class,
        ExchangeRateEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(
    CurrencyStatusConverter::class,
    OffsetDateTimeConverter::class
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        const val VERSION = 2
        const val NAME = "bcp_challenge_db"
    }

}