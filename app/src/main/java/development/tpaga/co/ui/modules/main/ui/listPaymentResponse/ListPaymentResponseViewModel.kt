package development.tpaga.co.ui.modules.main.ui.listPaymentResponse

import android.arch.lifecycle.ViewModel
import development.tpaga.co.ui.modules.main.data.PaymentResponseDao
import development.tpaga.co.ui.modules.main.utils.applySchedulers

class ListPaymentResponseViewModel (val paymentResponseDao: PaymentResponseDao) : ViewModel() {

    fun getAllPaymentResponse() = paymentResponseDao.getAllPaymentResponse().applySchedulers()
}