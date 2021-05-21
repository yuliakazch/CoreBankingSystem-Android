package com.gmail.yuliakazachok.corebanking.features.users.signin.presentation

import androidx.lifecycle.ViewModel
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcher
import com.gmail.yuliakazachok.corebanking.libraries.core.presentation.EventsDispatcherOwner
import com.gmail.yuliakazachok.corebanking.shared.users.domain.entities.Credentials
import com.gmail.yuliakazachok.corebanking.shared.users.domain.usecases.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    val checkTokenExist: CheckTokenExist
) : ViewModel(), EventsDispatcherOwner<SignInViewModel.EventListener> {

    interface EventListener {
        fun showToastError()
    }

    override val eventsDispatcher = EventsDispatcher<EventListener>()

    fun auth(login: String, password: String) = runBlocking {
        try {
            clearTokenUseCase()
            saveTokenUseCase(authUseCase(Credentials(login, password)))
        } catch (throwable: Throwable) {
            eventsDispatcher.dispatchEvent { showToastError() }
        }
    }
}