package vn.namnp.userinfoapplication.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import vn.namnp.userinfoapplication.R
import vn.namnp.userinfoapplication.databinding.ItemUserBinding
import vn.namnp.userinfoapplication.presentation.ui.UserUiItemState
import vn.namnp.userinfoapplication.util.extensions.executeWithAction
import javax.inject.Inject

class UserAdapter @Inject constructor() :
    PagingDataAdapter<UserUiItemState, UserAdapter.UserViewHolder>(ComparatorObject)
{

    object ComparatorObject : DiffUtil.ItemCallback<UserUiItemState>() {
        override fun areItemsTheSame(oldItem: UserUiItemState, newItem: UserUiItemState): Boolean {
            return oldItem.getPhone() == newItem.getPhone()
        }

        override fun areContentsTheSame(
            oldItem: UserUiItemState,
            newItem: UserUiItemState
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.e("AAAA", "" + position+"");
        getItem(position)?.let {
            userUiItemState -> holder.bind(userUiItemState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )

        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userUiItemState: UserUiItemState) {
            binding.executeWithAction {
                this.userUiItemState = userUiItemState
            }
        }
    }
}