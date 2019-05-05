package development.tpaga.co.ui.modules.main.ui.main

import android.arch.lifecycle.ViewModel
import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.net.InterfacePayment
import development.tpaga.co.ui.modules.main.utils.applySchedulers

class MainActivityViewModel(val interfacePayment: InterfacePayment) : ViewModel() {


    fun getPaymentRequest(paymentRequest: PaymentRequest, authorization:String) =
        interfacePayment.createPaymentRequest(authorization,paymentRequest).applySchedulers()
}