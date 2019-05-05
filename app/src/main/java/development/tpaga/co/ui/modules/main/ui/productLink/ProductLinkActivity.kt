package development.tpaga.co.ui.modules.main.ui.productLink

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import development.tpaga.co.R
import development.tpaga.co.ui.modules.main.ui.main.MainActivity
import development.tpaga.co.ui.modules.main.utils.*
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_product_link.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProductLinkActivity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences
    val viewModel by viewModel<ProductLinkViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_link)
        preferences = this.getSharedPreferences("PreferencesPayment", Context.MODE_PRIVATE)
        val lastToken = preferences.getString("lastProductToken", "")
        if(lastToken == ""){
            startActivity<MainActivity>()
        }
        viewModel.getStatusPaymentRequest(authorizationUser,lastToken!!).subscribeBy(onNext = {
            purchase_description.text = it.purchase_description
            purchase_cost.text = it.cost.toString()
            if(it.status == "paid"){
                purchase_state.text = STATUS_PAYMENT_OK
            }else if(it.status == "failed"){
                purchase_state.text = STATUS_PAYMENT_FAILED
            }else if(it.status == "delivered"){
                purchase_state.text = STATUS_PAYMENT_DELIVERED
            }else{
                purchase_state.text = it.status
            }
        }, onError = {
            longToast(ERROR_SERVER_CREATE)
            longToast(STATUS_PAYMENT_ERROR)
            startActivity<MainActivity>()
        })
    }

    override fun onResume() {
        super.onResume()
        btnGoTOHome.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}
