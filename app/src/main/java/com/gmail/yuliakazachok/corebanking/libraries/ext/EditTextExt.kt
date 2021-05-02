package com.gmail.yuliakazachok.corebanking.libraries.ext

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun EditText.asFlow(): Flow<String?> = callbackFlow {
    val watcher = doAfterTextChanged { offer(it?.toString()) }
    awaitClose { removeTextChangedListener(watcher) }
}

inline fun EditText.bindTextTwoWayN(
    owner: LifecycleOwner,
    flow: MutableStateFlow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) {
    flow.filter {
        text?.toString() != it
    }.onEach {
        setText(it)
    }.launchIn(owner.lifecycle.coroutineScope)

    asFlow().filter {
        it.takeIf { it != "" } != flow.value
    }.onEach {
        flow.value = it
        take(it)
    }.launchIn(owner.lifecycle.coroutineScope)
}