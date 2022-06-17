package paolo.bcp.foundation.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.threeten.bp.OffsetDateTime
import paolo.bcp.foundation.database.dtos.CurrencyEntity
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum
import paolo.bcp.foundation.dates.DateTimeProvider


@Dao
abstract class CurrencyDao: BaseDao<CurrencyEntity> {

    @Transaction
    open fun updateCurrentUniqueByCurrencyStatus(dateTimeProvider: DateTimeProvider<OffsetDateTime>,
                                                 currencyId: Long,
                                                 currencyStatus: CurrencyStatusEnum
    ) {
        val updatedAt = dateTimeProvider.now()
        findByStatus(currencyStatus)?.apply {
            updateStatus(this.id, CurrencyStatusEnum.ACTIVE, updatedAt)
        }
        updateStatus(currencyId, currencyStatus, updatedAt)
    }

    @Transaction
    open fun invertCurrenciesStatus(dateTimeProvider: DateTimeProvider<OffsetDateTime>,
                                                 senderCurrencyId: Long,
                                                 receivedCurrencyId: Long
    ) {
        val updatedAt = dateTimeProvider.now()
        updateStatus(senderCurrencyId, CurrencyStatusEnum.RECEIVED_CURRENCY, updatedAt)
        updateStatus(receivedCurrencyId, CurrencyStatusEnum.SENT_CURRENCY, updatedAt)
    }

    @Query("SELECT * FROM currency WHERE status = :currencyStatus")
    abstract fun findLiveByStatus(currencyStatus: CurrencyStatusEnum): LiveData<CurrencyEntity?>

    @Query("SELECT * FROM currency WHERE status = :currencyStatus")
    abstract fun findByStatus(currencyStatus: CurrencyStatusEnum): CurrencyEntity?

    @Query("SELECT * FROM currency WHERE status in (:sentStatus, :receivedStatus)")
    abstract fun findLiveAllCurrent(sentStatus: CurrencyStatusEnum, receivedStatus: CurrencyStatusEnum):
            LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM currency WHERE status = :status")
    abstract fun findAllActive(status: CurrencyStatusEnum = CurrencyStatusEnum.ACTIVE): List<CurrencyEntity>

    @Query("UPDATE currency SET status = :status, updated_at = :updatedAt WHERE id = :id")
    abstract fun updateStatus(id: Long, status: CurrencyStatusEnum, updatedAt: OffsetDateTime)

}