package development.tpaga.co.ui.modules.main.ui.listPaymentResponse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import development.tpaga.co.R
import development.tpaga.co.ui.modules.main.adapters.PaymentResponseAdapter
import development.tpaga.co.ui.modules.main.utils.NO_LOAD_PAYMENTS
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_list_payment_response.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ListPaymentResponseActivity : AppCompatActivity() {

    val viewModel by viewModel<ListPaymentResponseViewModel>()
    val adapter = PaymentResponseAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_payment_response)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        text_no_buy.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        recycler_payments.layoutManager = LinearLayoutManager(this)
        recycler_payments.adapter = adapter
        loadPaymentResponses()

    }

    /***
     * @function Función que permite cargar todos los pedidos guardados en persitencia
     */
    private fun loadPaymentResponses(){
        viewModel.getAllPaymentResponse().subscribeBy(onNext = {
            if(it.isEmpty()){
                text_no_buy.visibility = View.VISIBLE
            }
            adapter.paymentResponses = it
        },onError = {
            toast(NO_LOAD_PAYMENTS)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
