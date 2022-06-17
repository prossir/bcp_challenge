package paolo.bcp.foundation.database.converters

import androidx.room.TypeConverter
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


class CurrencyStatusConverter {

    @TypeConverter
    fun fromPointOfInterestStatus(currencyStatus: CurrencyStatusEnum): String {
        return currencyStatus.name
    }

    @TypeConverter
    fun toPointOfInterestStatus(currencyStatus: String): CurrencyStatusEnum {
        return CurrencyStatusEnum.valueOf(currencyStatus)
    }

}