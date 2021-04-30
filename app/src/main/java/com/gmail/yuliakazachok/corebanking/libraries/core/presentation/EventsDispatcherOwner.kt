package com.gmail.yuliakazachok.corebanking.libraries.core.presentation

interface EventsDispatcherOwner<T> {
    val eventsDispatcher: EventsDispatcher<T>
}