package development.tpaga.co.ui.modules.main.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import development.tpaga.co.R
import development.tpaga.co.ui.modules.main.adapters.ProductAdapter
import development.tpaga.co.ui.modules.main.models.PaymentRequest
import development.tpaga.co.ui.modules.main.utils.ERROR_APP_BUY
import development.tpaga.co.ui.modules.main.utils.ERROR_SERVER_CREATE
import development.tpaga.co.ui.modules.main.utils.LifeDisposable
import development.tpaga.co.ui.modules.main.utils.authorizationUser
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    val adapter = ProductAdapter()
    lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var listItems: ArrayList<PaymentRequest> = ArrayList()
    val viewModel by viewModel<MainActivityViewModel>()
    private val diss: LifeDisposable = LifeDisposable(this)
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = this.getSharedPreferences("PreferencesPayment", Context.MODE_PRIVATE)
        editor = this.getSharedPreferences("PreferencesPayment", Context.MODE_PRIVATE).edit()
    }

    override fun onResume() {
        super.onResume()
        listItems = ArrayList()
        loadDataProducts()
        recyclerProductos.layoutManager = LinearLayoutManager(this)
        recyclerProductos.adapter = adapter
        diss add adapter.clickBuyProduct.subscribeBy(onNext = {
            viewModel.getPaymentRequest(it, authorizationUser).subscribeBy(
                onNext = { paymentResponse ->
                    thread {
                        // se inserta la peticion de compra del nuevo producto en el storage
                        viewModel.insertPaymentResonse(paymentResponse)
                    }
                    // se guarda el token de la ultima compra en sahredPreferences
                    editor.putString("lastProductToken", paymentResponse.token)
                    editor.commit()
                    editor.apply()
                    val url = paymentResponse.tpaga_payment_url
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }, onError = {
                    longToast(ERROR_SERVER_CREATE)
                })
        }, onError = {
            longToast(ERROR_APP_BUY)
        })

    }

    @SuppressLint("SimpleDateFormat")
    private fun loadDataProducts() {
        val product1 = PaymentRequest()
        product1.cost = 12000
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("yyyy-MM-dd'T24:00:00.00+00:00'") // Quoted "Z" to indicate UTC, no timezone offset
        df.timeZone = tz
        val nowAsISO = df.format(Date())
        product1.expires_at = nowAsISO
        val timestamp = System.currentTimeMillis()
        product1.idempotency_token = "Producto1-bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz$timestamp"
        product1.order_id = "1"
        product1.purchase_description = "Compra producto 1"
        product1.purchase_details_url = "https://murbano.com/compra/1"
        product1.voucher_url = "https://murbano.com/comprobante/348820"
        product1.terminal_id = "sede_1"
        product1.user_ip_address = "61.1.224.56"
        listItems.add(product1)

        val product2 = PaymentRequest()
        product2.cost = 10000
        df.timeZone = tz
        product2.expires_at = nowAsISO
        product2.idempotency_token = "Producto2-bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz$timestamp"
        product2.order_id = "2"
        product2.purchase_description = "Compra producto 2"
        product2.purchase_details_url = "https://murbano.com/compra/2"
        product2.voucher_url = "https://murbano.com/comprobante/348820"
        product2.terminal_id = "sede_1"
        product2.user_ip_address = "61.1.224.56"
        listItems.add(product2)


        val product3 = PaymentRequest()
        product3.cost = 15000
        df.timeZone = tz
        product3.expires_at = nowAsISO
        product3.idempotency_token = "Producto3-bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz$timestamp"
        product3.order_id = "3"
        product3.purchase_description = "Compra producto 3"
        product3.purchase_details_url = "https://murbano.com/compra/3"
        product3.voucher_url = "https://murbano.com/comprobante/348820"
        product3.terminal_id = "sede_1"
        product3.user_ip_address = "61.1.224.56"
        listItems.add(product3)

        val product4 = PaymentRequest()
        product4.cost = 16000
        df.timeZone = tz
        product4.expires_at = nowAsISO
        product4.idempotency_token = "Producto4-bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz$timestamp"
        product4.order_id = "4"
        product4.purchase_description = "Compra producto 4"
        product4.purchase_details_url = "https://murbano.com/compra/4"
        product4.voucher_url = "https://murbano.com/comprobante/348820"
        product4.terminal_id = "sede_1"
        product4.user_ip_address = "61.1.224.56"
        listItems.add(product4)

        val product5 = PaymentRequest()
        product5.cost = 9000
        df.timeZone = tz
        product5.expires_at = nowAsISO
        product5.idempotency_token = "Producto5-bWluaWFwcC1nYXRvMTptaW5pYXBwbWEtMTIz$timestamp"
        product5.order_id = "5"
        product5.purchase_description = "Compra producto 5"
        product5.purchase_details_url = "https://murbano.com/compra/5"
        product5.voucher_url = "https://murbano.com/comprobante/348820"
        product5.terminal_id = "sede_1"
        product5.user_ip_address = "61.1.224.56"
        listItems.add(product5)



        adapter.listItems = listItems
    }


}
