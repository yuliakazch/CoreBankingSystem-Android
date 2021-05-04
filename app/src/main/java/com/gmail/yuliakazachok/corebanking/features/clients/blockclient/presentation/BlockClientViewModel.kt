package com.gmail.yuliakazachok.corebanking.features.clients.blockclient.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.usecases.BlockClientUseCase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BlockClientViewModel @Inject constructor(
    private val blockClientUseCase: BlockClientUseCase
) : ViewModel(), EventsDispatcherOwner<BlockClientViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    fun blockClient(number: Long, days: Int) = runBlocking {
        try {
            blockClientUseCase(number, days)
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }
}