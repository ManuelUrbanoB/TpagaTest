package development.tpaga.co.ui.modules.main.ui.productLink

import android.arch.lifecycle.ViewModel
import development.tpaga.co.ui.modules.main.data.PaymentResponseDao
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import development.tpaga.co.ui.modules.main.net.InterfacePayment
import development.tpaga.co.ui.modules.main.utils.applySchedulers
import io.reactivex.Flowable

class ProductLinkViewModel(val interfacePayment: InterfacePayment, val paymentResponseDao: PaymentResponseDao) : ViewModel() {

    fun getStatusPaymentRequest(authorization:String, tokenPayment:String) = interfacePayment.statusPaymentRequest(authorization,tokenPayment).applySchedulers()
    fun getPaymentResponsePersistence(token:String) = paymentResponseDao.getPaymentResponseToken(token).applySchedulers()
    fun updatePaymentResponsePersistence(paymentResponse: PaymentResponse) = paymentResponseDao.update(paymentResponse)

}