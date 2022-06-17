package paolo.bcp.foundation.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import paolo.bcp.foundation.database.dtos.ExchangeRateEntity
import paolo.bcp.foundation.database.dtos.embedded.ExchangeRateWithCurrenciesEntity
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


@Dao
abstract class ExchangeRateDao: BaseDao<ExchangeRateEntity> {

    @Query("SELECT * FROM exchangeRate " +
            "WHERE base_currency_id = :sentId " +
            "AND exchange_currency_id = (SELECT id FROM currency WHERE status = :receivedStatus)")
    abstract fun findLiveCurrent(sentId: Long,
                                 receivedStatus: CurrencyStatusEnum = CurrencyStatusEnum.RECEIVED_CURRENCY):
            LiveData<ExchangeRateWithCurrenciesEntity?>

    @Query("SELECT * FROM exchangeRate WHERE base_currency_id = :sentId")
    abstract fun findAllOfSenderCurrency(sentId: Long): List<ExchangeRateWithCurrenciesEntity>

}