package paolo.bcp.currency_exchange.platform.front.select_current_currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import paolo.bcp.currency_exchange.databinding.ItemExchangeRateByCurrencyBinding
import paolo.bcp.currency_exchange.platform.front.common.dtos.ExchangeRateWithCurrenciesModel


class ExchangeRatesByCurrencyAdapter(
    private val listener: OnExchangeRateClicked
):
    ListAdapter<ExchangeRateWithCurrenciesModel, ExchangeRatesByCurrencyAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.bind(it, listener) }
    }

    class ViewHolder(private val binding: ItemExchangeRateByCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exchangeRateWithCurrencies: ExchangeRateWithCurrenciesModel, listener: OnExchangeRateClicked) {
            binding.exchangeRateWithCurrencies = exchangeRateWithCurrencies
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExchangeRateByCurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val diffUtil = object :
            DiffUtil.ItemCallback<ExchangeRateWithCurrenciesModel>() {
            override fun areItemsTheSame(old: ExchangeRateWithCurrenciesModel,
                                         new: ExchangeRateWithCurrenciesModel) =
                old.exchangeCurrency == new.exchangeCurrency

            override fun areContentsTheSame(old: ExchangeRateWithCurrenciesModel,
                                            new: ExchangeRateWithCurrenciesModel) =
                old == new
        }
    }

}