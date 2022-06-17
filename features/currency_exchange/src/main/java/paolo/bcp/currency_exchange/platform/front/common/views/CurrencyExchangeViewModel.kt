package paolo.bcp.currency_exchange.platform.front.common.views

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import paolo.bcp.currency_exchange.domain.use_case.GetCurrenciesByStatusUseCase
import paolo.bcp.currency_exchange.domain.use_case.GetCurrentExchangeRateUseCase
import paolo.bcp.currency_exchange.domain.use_case.InvertCurrentCurrenciesUseCase
import paolo.bcp.currency_exchange.domain.use_case.SelectCurrentCurrencyUseCase
import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateWithCurrenciesModel
import paolo.bcp.currency_exchange.platform.front.common.mappers.ExchangeRateWithCurrenciesMapper
import paolo.bcp.foundation.database.dtos.enums.CurrencyStatusEnum
import paolo.bcp.foundation.events.Event
import paolo.bcp.foundation.extensions.safeLaunch
import paolo.bcp.foundation.extensions.withDispatcher
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(
    private val getCurrentExchangeRateUseCase: GetCurrentExchangeRateUseCase,
    private val invertCurrentCurrenciesUseCase: InvertCurrentCurrenciesUseCase,
    private val getCurrenciesByStatusUseCase: GetCurrenciesByStatusUseCase,
    private val selectCurrentCurrencyUseCase: SelectCurrentCurrencyUseCase,
    private val exchangeRateWithCurrenciesMapper: ExchangeRateWithCurrenciesMapper,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    val sentAmount: MutableLiveData<Double> = MutableLiveData(0.0)
    var sentAmountAsString = object: ObservableField<String>(sentAmount.value.toString()) {
        override fun set(value: String?) {
            super.set(value)
            sentAmount.postValue(value?.toDoubleOrNull())
        }
    }
    val receivedAmountAsString: MutableLiveData<String> = MutableLiveData("0.0")

    var currentExchangeRate: LiveData<ExchangeRateWithCurrenciesModel?>? = null
    val exchangeRateIsLoaded: MutableLiveData<Boolean> = MutableLiveData(false)
    fun loadCurrentExchangeRate() {
        viewModelScope.safeLaunch(::handleCurrentExchangeRateException) {
            withDispatcher(dispatcher) {
                currentExchangeRate = Transformations.map(getCurrentExchangeRateUseCase()) {
                    it?.let {
                        exchangeRateWithCurrenciesMapper.map(it)
                    }
                }
                exchangeRateIsLoaded.postValue(true)
            }
        }
    }
    private fun handleCurrentExchangeRateException(t: Throwable) {
        Timber.e(t)
    }

    val baseCurrencyName: MutableLiveData<String> = MutableLiveData("")
    val exchangeCurrencyName: MutableLiveData<String> = MutableLiveData("")
    val exchangeRateDescription: MutableLiveData<String> = MutableLiveData("")
    fun updateCurrentExchangeRateData(exchangeRateWithCurrencies: ExchangeRateWithCurrenciesModel) {
        baseCurrencyName.postValue(exchangeRateWithCurrencies.baseCurrency.name)
        exchangeCurrencyName.postValue(exchangeRateWithCurrencies.exchangeCurrency.name)
        exchangeRateDescription.postValue(exchangeRateWithCurrencies.description)
        resetSentAmount()
    }

    private fun resetSentAmount() {
        sentAmountAsString.set("0.0")
    }

    fun exchangeAmount(amountToExchange: Double) {
        currentExchangeRate?.value?.apply {
            receivedAmountAsString.postValue(
                this.applyRate(amountToExchange)
            )
        }
    }

    fun invertCurrentCurrencies() {
        viewModelScope.safeLaunch(::handleInvertCurrentCurrencies) {
            withDispatcher(dispatcher) {
                invertCurrentCurrenciesUseCase()
            }
        }
    }
    private fun handleInvertCurrentCurrencies(t: Throwable) {
        Timber.e(t)
    }

    // Change Current Currency
    private val _navigateToChangeCurrentCurrencyByStatus = MutableLiveData<Event<String>>()
    val navigateToChangeCurrentCurrencyByStatus : LiveData<Event<String>>
        get() = _navigateToChangeCurrentCurrencyByStatus

    private val _navigateToExchangeCurrentCurrency = MutableLiveData<Event<String>>()
    val navigateToExchangeCurrentCurrency : LiveData<Event<String>>
        get() = _navigateToExchangeCurrentCurrency

    val exchangeRatesByStatus: MutableLiveData<List<ExchangeRateWithCurrenciesModel>> =
        MutableLiveData(listOf())
    private var objectiveCurrencyStatus = CurrencyStatusEnum.ACTIVE

    fun changeSentCurrency(): Boolean {
        setObjectiveStatusOfCurrencyToChange(CurrencyStatusEnum.SENT_CURRENCY)
        navigateToSelectCurrentCurrencies()
        loadExchangeRatesByStatus()
        return true
    }

    fun changeReceivedCurrency(): Boolean {
        setObjectiveStatusOfCurrencyToChange(CurrencyStatusEnum.RECEIVED_CURRENCY)
        navigateToSelectCurrentCurrencies()
        loadExchangeRatesByStatus()
        return true
    }

    private fun setObjectiveStatusOfCurrencyToChange(status: CurrencyStatusEnum) {
        objectiveCurrencyStatus = status
    }

    private fun navigateToSelectCurrentCurrencies() {
        _navigateToChangeCurrentCurrencyByStatus.postValue(Event(System.currentTimeMillis().toString()))
    }

    private fun loadExchangeRatesByStatus() {
        viewModelScope.safeLaunch(::handleLoadExchangeRatesByStatusException) {
            withDispatcher(dispatcher) {
                exchangeRatesByStatus.postValue(
                    exchangeRateWithCurrenciesMapper.map(
                        getCurrenciesByStatusUseCase(objectiveCurrencyStatus)
                    )
                )
            }
        }
    }
    private fun handleLoadExchangeRatesByStatusException(t: Throwable) {
        Timber.e(t)
    }

    fun changeCurrentCurrencyByStatus(exchangeCurrencyId: Long) {
        viewModelScope.safeLaunch(::handleChangeCurrentCurrencyByStatus) {
            withDispatcher(dispatcher) {
                selectCurrentCurrencyUseCase(exchangeCurrencyId, objectiveCurrencyStatus)
                _navigateToExchangeCurrentCurrency.postValue(Event(System.currentTimeMillis().toString()))
            }
        }
    }
    private fun handleChangeCurrentCurrencyByStatus(t: Throwable) {
        Timber.e(t)
    }

}