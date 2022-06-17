package paolo.bcp.currency_exchange.platform.front.common.dtos

import android.graphics.Color
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum


data class ExchangeRateWithCurrenciesModel(
    val exchangeRate: ExchangeRateModel,
    val baseCurrency: CurrencyModel,
    val exchangeCurrency: CurrencyModel
) {

    fun applyRate(amountToExchange: Double): String {
        return "${exchangeRate.purchaseRate * amountToExchange}"
    }

    val description: String
        get() = "Buy: ${exchangeRate.purchaseRate} | Sale: ${exchangeRate.saleRate}"

    val exchangeEquivalenceDescription: String
        get() = "1 ${baseCurrency.abbreviation} = ${exchangeRate.purchaseRate} ${exchangeCurrency.abbreviation}"

    val isCurrent: Boolean
        get() = (baseCurrency.status == CurrencyStatusEnum.SENT_CURRENCY ||
                baseCurrency.status == CurrencyStatusEnum.RECEIVED_CURRENCY) &&
                (exchangeCurrency.status == CurrencyStatusEnum.SENT_CURRENCY ||
                exchangeCurrency.status == CurrencyStatusEnum.RECEIVED_CURRENCY)

    val color: Int
        get() {
            return if(isCurrent) {
                Color.parseColor("#004192")
            } else {
                Color.parseColor("#ffffff")
            }
        }

    val textColor: Int
        get() {
            return if(isCurrent) {
                Color.parseColor("#ffffff")
            } else {
                Color.parseColor("#000000")
            }
        }

}