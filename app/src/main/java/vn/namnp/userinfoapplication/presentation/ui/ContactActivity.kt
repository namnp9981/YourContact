package vn.namnp.userinfoapplication.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import vn.namnp.userinfoapplication.R
import vn.namnp.userinfoapplication.databinding.ActivityContactBinding
import vn.namnp.userinfoapplication.presentation.viewmodel.UserViewModel
import vn.namnp.userinfoapplication.presentation.adapter.FooterAdapter
import vn.namnp.userinfoapplication.presentation.adapter.UserAdapter
import vn.namnp.userinfoapplication.util.extensions.collect
import vn.namnp.userinfoapplication.util.extensions.collectLastest
import vn.namnp.userinfoapplication.util.extensions.executeWithAction
import javax.inject.Inject

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private val userViewModel: UserViewModel by viewModels()

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBindings()
        setupListeners()
        setupAdapters()
        collectLastest(userViewModel.userItemUiSates, ::setUsers)
    }

    private fun setupAdapters() {
        collect(
            flow = userAdapter
                .loadStateFlow
                .distinctUntilChangedBy {
                    it.source.refresh
                }
                .map {
                    it.refresh
                },
            action = ::setUsersUiState
        )
        binding.rvContact.adapter = userAdapter
            .withLoadStateFooter(FooterAdapter(userAdapter::retry))
    }

    private fun setupListeners() {
        binding.btnRetry.setOnClickListener {
            userAdapter.retry()
        }
    }

    private fun setupBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact)
    }

    private fun setUsersUiState(loadState: LoadState) {
        binding.executeWithAction {
            usersUiState = UserUiState(loadState)
        }
    }

    private suspend fun setUsers(userItemsPagingData: PagingData<UserUiItemState>) {
        Log.e("TAG", userItemsPagingData.toString())
        userAdapter.submitData(userItemsPagingData)
    }

}