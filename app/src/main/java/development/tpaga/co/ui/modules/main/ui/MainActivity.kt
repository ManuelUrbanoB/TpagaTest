package development.tpaga.co.ui.modules.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import development.tpaga.co.R
import development.tpaga.co.ui.modules.main.adapters.ProductAdapter
import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.models.PaymentResponse
import development.tpaga.co.ui.modules.main.utils.LifeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import android.net.Uri
import development.tpaga.co.ui.modules.main.utils.ERROR_APP_BUY
import development.tpaga.co.ui.modules.main.utils.ERROR_SERVER_CREATE
import org.jetbrains.anko.longToast


class MainActivity : AppCompatActivity() {
    val adapter = ProductAdapter()
    var listItems : ArrayList<PaymentRequest> = ArrayList()
    val viewModel by viewModel<MainActivityViewModel>()
    var paymentResponse:PaymentResponse = PaymentResponse()
    private val diss: LifeDisposable = LifeDisposable(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        listItems  = ArrayList()
        loadDataProducts()
        recyclerProductos.layoutManager = LinearLayoutManager(this)
        recyclerProductos.adapter = adapter
        diss add adapter.clickBuyProduct.subscribeBy(onNext = {
            viewModel.getPaymentRequest(it, "basic bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz").subscribeBy(onNext = {
                paymentResponse = it
                val url = it.tpaga_payment_url
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }, onError = {
                    longToast(ERROR_SERVER_CREATE)
            })
        },onError = {
            longToast(ERROR_APP_BUY)
        })

    }

    @SuppressLint("SimpleDateFormat")
    private fun loadDataProducts(){
        val product1 = PaymentRequest()
        product1.cost = 12000
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("yyyy-MM-dd'T24:00:00.00+00:00'") // Quoted "Z" to indicate UTC, no timezone offset
        df.timeZone = tz
        val nowAsISO = df.format(Date())
        product1.expires_at = nowAsISO
        val timestamp = System.currentTimeMillis()
        product1.idempotency_token = "bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz"+timestamp
        product1.order_id = "1212"
        product1.purchase_description = "Compra producto 1"
        product1.purchase_details_url = "https://example.com/compra/1212"
        product1.voucher_url = "https://example.com/comprobante/348820"
        product1.terminal_id = "sede_1"
        product1.user_ip_address = "61.1.224.56"
        listItems.add(product1)
        adapter.listItems = listItems
    }


}
