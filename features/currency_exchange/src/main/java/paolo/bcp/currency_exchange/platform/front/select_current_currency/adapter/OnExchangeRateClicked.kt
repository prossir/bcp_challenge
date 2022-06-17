package paolo.bcp.currency_exchange.platform.front.select_current_currency.adapter

import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateWithCurrenciesModel


interface OnExchangeRateClicked {

    fun onExchangeRateSelected(exchangeRateWithCurrencies: ExchangeRateWithCurrenciesModel)

}