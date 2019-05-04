package development.tpaga.co.ui.modules.main.net


import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InterfacePayment {


    @POST("payment_requests/create")
    fun createPaymentRequest(@Header("Authorization") authorization:String, @Body paymentRequest: PaymentRequest):Flowable<PaymentResponse>
}