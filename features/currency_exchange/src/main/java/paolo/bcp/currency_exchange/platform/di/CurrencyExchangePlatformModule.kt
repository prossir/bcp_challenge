package paolo.bcp.currency_exchange.platform.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository
import paolo.bcp.currency_exchange.domain.use_case.GetCurrenciesByStatusUseCase
import paolo.bcp.currency_exchange.domain.use_case.GetCurrentExchangeRateUseCase
import paolo.bcp.currency_exchange.domain.use_case.InvertCurrentCurrenciesUseCase
import paolo.bcp.currency_exchange.domain.use_case.SelectCurrentCurrencyUseCase
import paolo.bcp.currency_exchange.platform.front.common.mappers.CurrencyMapper
import paolo.bcp.currency_exchange.platform.front.common.mappers.ExchangeRateMapper
import paolo.bcp.currency_exchange.platform.front.common.mappers.ExchangeRateWithCurrenciesMapper


@Module
@InstallIn(ViewModelComponent::class)
object CurrencyExchangePlatformModule {

    // Use Cases
    @ViewModelScoped
    @Provides
    fun providesGetCurrentCurrenciesUseCase(currencyExchangeRepository: CurrencyExchangeRepository) =
        GetCurrentExchangeRateUseCase(currencyExchangeRepository)

    @ViewModelScoped
    @Provides
    fun providesInvertCurrentCurrenciesUseCase(currencyExchangeRepository: CurrencyExchangeRepository) =
        InvertCurrentCurrenciesUseCase(currencyExchangeRepository)

    @ViewModelScoped
    @Provides
    fun providesGetCurrenciesUseCase(currencyExchangeRepository: CurrencyExchangeRepository) =
        GetCurrenciesByStatusUseCase(currencyExchangeRepository)

    @ViewModelScoped
    @Provides
    fun providesSelectCurrentCurrencyUseCase(currencyExchangeRepository: CurrencyExchangeRepository) =
        SelectCurrentCurrencyUseCase(currencyExchangeRepository)

    // Mappers
    @Provides
    fun providesCurrencyMapper() = CurrencyMapper()

    @Provides
    fun providesExchangeRateMapper() = ExchangeRateMapper()

    @Provides
    fun providesExchangeRateWithCurrenciesMapper(
        currencyMapper: CurrencyMapper,
        exchangeRateMapper: ExchangeRateMapper
    ) = ExchangeRateWithCurrenciesMapper(currencyMapper, exchangeRateMapper)

    // Dispatcher
    @Provides
    fun providesDispatcher() = Dispatchers.IO

}