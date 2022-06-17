package paolo.bcp.currency_exchange.platform.front.common.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import paolo.bcp.currency_exchange.R
import paolo.bcp.currency_exchange.databinding.ActivityCurrencyExchangeBinding


@AndroidEntryPoint
class CurrencyExchangeActivity: AppCompatActivity() {

    private val viewModel by viewModels<CurrencyExchangeViewModel>()
    private lateinit var binding: ActivityCurrencyExchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_exchange)
        viewModel.loadCurrentExchangeRate()
    }

}