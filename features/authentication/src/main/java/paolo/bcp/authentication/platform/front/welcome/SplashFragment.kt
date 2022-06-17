package paolo.bcp.authentication.platform.front.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import paolo.bcp.authentication.databinding.FragmentSplashBinding
import paolo.bcp.authentication.utils.constants.AuthenticationConstants


class SplashFragment: Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater)
        initUi()
        return binding.root
    }

    private fun initUi() {
        showCurrencyExchangeDelayed()
    }

    private fun showCurrencyExchangeDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            Intent().apply {
                activity?.let {
                    this.setClassName(
                        it.packageName,
                        AuthenticationConstants.PACKAGE_INTENT_CURRENCY_EXCHANGE
                    )
                    startActivity(this)
                }
            }
        }, SPLASH_TO_CURRENCY_EXCHANGE_DELAY_IN_MILLISECONDS)
    }

    companion object {
        private const val SPLASH_TO_CURRENCY_EXCHANGE_DELAY_IN_MILLISECONDS = 3000L
    }

}