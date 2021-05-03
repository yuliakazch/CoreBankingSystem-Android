package com.gmail.yuliakazachok.corebanking.shared.clients.domain.repository

import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client

interface ClientsRepository {

    suspend fun getByPassport(numberPassport: Long): Client
}