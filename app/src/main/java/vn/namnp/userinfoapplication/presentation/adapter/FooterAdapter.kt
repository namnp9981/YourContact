package vn.namnp.userinfoapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.namnp.userinfoapplication.R
import vn.namnp.userinfoapplication.databinding.ItemFooterBinding
import vn.namnp.userinfoapplication.presentation.ui.FooterUiState
import vn.namnp.userinfoapplication.util.extensions.executeWithAction

class FooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState = loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val itemFooterBinding = inflate<ItemFooterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_footer,
            parent,
            false
        )
        return FooterViewHolder(footerBinding = itemFooterBinding, retry = retry)
    }

    inner class FooterViewHolder(
        private val footerBinding: ItemFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(footerBinding.root) {

        init {
            footerBinding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            footerBinding.executeWithAction {
                footerUiState = FooterUiState(loadState = loadState)
            }
        }
    }
}