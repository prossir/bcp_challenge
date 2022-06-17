package paolo.bcp.foundation.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


@Entity(
    tableName = CurrencyEntity.TABLE_NAME,
    indices = [
        Index(value = [CurrencyEntity.FIELD_ID])
    ]
)
data class CurrencyEntity(
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = FIELD_NAME)
    val name: String,
    @ColumnInfo(name = FIELD_COUNTRY_NAME)
    val countryName: String,
    @ColumnInfo(name = FIELD_FLAG_URL)
    val flagUrl: String?,
    @ColumnInfo(name = FIELD_STATUS)
    val status: CurrencyStatusEnum,
    @ColumnInfo(name = FIELD_CREATED_AT)
    var createdAt: OffsetDateTime?,
    @ColumnInfo(name = FIELD_UPDATED_AT)
    var updatedAt: OffsetDateTime? = createdAt,
    @ColumnInfo(name = FIELD_ABBREVIATION)
    val abbreviation: String,
) {

    companion object {
        internal const val TABLE_NAME = "currency"

        internal const val FIELD_ID = "id"
        internal const val FIELD_NAME = "name"
        internal const val FIELD_ABBREVIATION = "abbreviation"
        internal const val FIELD_COUNTRY_NAME = "country_name"
        internal const val FIELD_FLAG_URL = "flag_url"
        internal const val FIELD_STATUS = "status"
        internal const val FIELD_CREATED_AT = "created_at"
        internal const val FIELD_UPDATED_AT = "updated_at"
    }

}