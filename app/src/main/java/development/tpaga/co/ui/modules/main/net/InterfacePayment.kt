package development.tpaga.co.ui.modules.main.net


import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface InterfacePayment {

    @Headers("Authorization: basic bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz")
    @POST("payment_requests/create")
    fun createPaymentRequest(@Body paymentRequest: PaymentRequest):Flowable<PaymentResponse>
}