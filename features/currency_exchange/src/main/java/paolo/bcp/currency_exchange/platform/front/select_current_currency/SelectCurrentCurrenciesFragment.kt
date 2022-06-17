package paolo.bcp.currency_exchange.platform.front.select_current_currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import paolo.bcp.currency_exchange.databinding.FragmentSelectCurrentCurrencyByStatusBinding
import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateWithCurrenciesModel
import paolo.bcp.currency_exchange.platform.front.common.views.CurrencyExchangeViewModel
import paolo.bcp.currency_exchange.platform.front.select_current_currency.adapter.ExchangeRatesByCurrencyAdapter
import paolo.bcp.currency_exchange.platform.front.select_current_currency.adapter.OnExchangeRateClicked


class SelectCurrentCurrenciesFragment: Fragment(), OnExchangeRateClicked {

    private val viewModel by activityViewModels<CurrencyExchangeViewModel>()
    private lateinit var binding: FragmentSelectCurrentCurrencyByStatusBinding
    private lateinit var adapter: ExchangeRatesByCurrencyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSelectCurrentCurrencyByStatusBinding.inflate(inflater)
        initUi()
        initObservers()
        return binding.root
    }

    private fun initUi() {
        adapter = ExchangeRatesByCurrencyAdapter(this)
        binding.rvExchangeRates.adapter = adapter
    }

    private fun initObservers() {
        observeLoadedExchangeRates()
        observeNavigationEvents()
    }

    private fun observeLoadedExchangeRates() {
        viewModel.exchangeRatesByStatus.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeNavigationEvents() {
        viewModel.navigateToExchangeCurrentCurrency.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                view?.findNavController()?.popBackStack()
            }
        }
    }

    override fun onExchangeRateSelected(exchangeRateWithCurrencies: ExchangeRateWithCurrenciesModel) {
        viewModel.changeCurrentCurrencyByStatus(exchangeRateWithCurrencies.exchangeCurrency.id)
    }

}