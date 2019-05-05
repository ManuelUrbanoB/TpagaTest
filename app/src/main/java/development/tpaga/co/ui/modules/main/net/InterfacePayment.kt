package development.tpaga.co.ui.modules.main.net


import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import io.reactivex.Flowable
import retrofit2.http.*

interface InterfacePayment {


    @POST("payment_requests/create")
    fun createPaymentRequest(@Header("Authorization") authorization:String, @Body paymentRequest: PaymentRequest):Flowable<PaymentResponse>
    @GET("payment_requests/{token}/info")
    fun statusPaymentRequest(@Header("Authorization") authorization:String, @Path("token") tokenPaymentRequest: String): Flowable<PaymentResponse>
}