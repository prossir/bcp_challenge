package paolo.bcp.currency_exchange.platform.front.exchange_current_currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import paolo.bcp.currency_exchange.databinding.FragmentExchangeCurrentCurrenciesBinding
import paolo.bcp.currency_exchange.platform.front.common.views.CurrencyExchangeViewModel


class ExchangeCurrentCurrenciesFragment: Fragment() {

    private val viewModel by activityViewModels<CurrencyExchangeViewModel>()
    private lateinit var binding: FragmentExchangeCurrentCurrenciesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExchangeCurrentCurrenciesBinding.inflate(inflater)
        initUi()
        initObservers()
        return binding.root
    }

    private fun initUi() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initObservers() {
        observeSentAmountChange()
        observeOnceIfExchangeRateIsLoaded()
        observeNavigationEvents()
    }

    private fun observeSentAmountChange() {
        viewModel.sentAmount.observe(viewLifecycleOwner) {
            it?.apply {
                viewModel.exchangeAmount(it)
            }
        }
    }

    private fun observeOnceIfExchangeRateIsLoaded() {
        viewModel.exchangeRateIsLoaded.observe(viewLifecycleOwner) { isLoaded ->
            if(isLoaded) {
                observeCurrentExchangeRate()
            }
            viewModel.exchangeRateIsLoaded.removeObservers(this)
        }
    }

    private fun observeCurrentExchangeRate() {
        viewModel.currentExchangeRate!!.observe(viewLifecycleOwner) {
            it?.apply {
                viewModel.updateCurrentExchangeRateData(this)
            }
        }
    }

    private fun observeNavigationEvents() {
        viewModel.navigateToChangeCurrentCurrencyByStatus.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                view?.findNavController()?.navigate(
                    ExchangeCurrentCurrenciesFragmentDirections.actionExchangeCurrentCurrenciesFragmentToSelectCurrentCurrenciesFragment()
                )
            }
        }
    }

}