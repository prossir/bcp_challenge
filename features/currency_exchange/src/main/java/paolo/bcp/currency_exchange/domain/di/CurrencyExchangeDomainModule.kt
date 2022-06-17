package paolo.bcp.currency_exchange.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.threeten.bp.OffsetDateTime
import paolo.bcp.currency_exchange.data.datasources.local.CurrencyExchangeLocalDatasource
import paolo.bcp.currency_exchange.data.mappers.local.CurrencyLocalMapper
import paolo.bcp.currency_exchange.data.mappers.local.ExchangeRateLocalMapper
import paolo.bcp.currency_exchange.data.mappers.local.ExchangeRateWithCurrenciesLocalMapper
import paolo.bcp.currency_exchange.data.repositories.CurrencyExchangeDataRepository
import paolo.bcp.currency_exchange.domain.repositories.CurrencyExchangeRepository
import paolo.bcp.foundation.database.providers.DaoProvider
import paolo.bcp.foundation.dates.DateTimeProvider


@Module
@InstallIn(ActivityRetainedComponent::class)
object CurrencyExchangeDomainModule {

    // Mappers
    // Local
    @Provides
    fun providesCurrencyLocalMapper() = CurrencyLocalMapper()

    @Provides
    fun providesExchangeRateLocalMapper() = ExchangeRateLocalMapper()

    @Provides
    fun providesExchangeRateWithCurrenciesLocalMapper(
        currencyLocalMapper: CurrencyLocalMapper,
        exchangeRateLocalMapper: ExchangeRateLocalMapper
    ) = ExchangeRateWithCurrenciesLocalMapper(currencyLocalMapper, exchangeRateLocalMapper)

    // Datasource
    // Local
    @Provides
    fun providesCurrencyExchangeLocalDatasource(daoProvider: DaoProvider,
                                                dateTimeProvider: DateTimeProvider<OffsetDateTime>) =
        CurrencyExchangeLocalDatasource(daoProvider, dateTimeProvider)

    // Repository
    @ActivityRetainedScoped
    @Provides
    fun providesCurrencyExchangeRepository(
        currencyExchangeLocalDatasource: CurrencyExchangeLocalDatasource,
        exchangeRateWithCurrenciesLocalMapper: ExchangeRateWithCurrenciesLocalMapper,
    ) : CurrencyExchangeRepository =
        CurrencyExchangeDataRepository(currencyExchangeLocalDatasource, exchangeRateWithCurrenciesLocalMapper)

}