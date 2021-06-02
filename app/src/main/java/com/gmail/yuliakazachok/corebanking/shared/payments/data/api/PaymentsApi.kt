package com.gmail.yuliakazachok.corebanking.shared.payments.data.api

import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentCreateDto
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentDto
import com.gmail.yuliakazachok.corebanking.shared.payments.data.dto.PaymentScheduleDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentsApi {

    @GET("/payment/{id}")
    suspend fun getPayments(@Path("id") id: Int): List<PaymentDto>

    @GET("/payment/schedule/{id}")
    suspend fun getPaymentSchedule(@Path("id") id: Int): List<PaymentScheduleDto>

    @POST("payment")
    suspend fun makePayment(@Body paymentCreateDto: PaymentCreateDto)
}