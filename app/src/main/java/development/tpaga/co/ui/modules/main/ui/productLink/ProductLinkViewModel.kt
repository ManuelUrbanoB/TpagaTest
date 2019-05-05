package development.tpaga.co.ui.modules.main.ui.productLink

import android.arch.lifecycle.ViewModel
import development.tpaga.co.ui.modules.main.net.InterfacePayment
import development.tpaga.co.ui.modules.main.utils.applySchedulers

class ProductLinkViewModel(val interfacePayment: InterfacePayment) : ViewModel() {

    fun getStatusPaymentRequest(authorization:String, tokenPayment:String) = interfacePayment.statusPaymentRequest(authorization,tokenPayment).applySchedulers()

}