package development.tpaga.co.ui.modules.main.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class PaymentResponse {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var miniapp_user_token:String? = null
    var cost:Int = 0
    var purchase_details_url:String = ""
    var voucher_url:String = ""
    var idempotency_token:String = ""
    var order_id:String = ""
    var terminal_id:String = ""
    var purchase_description: String = ""
    var user_ip_address:String = ""
    var merchant_user_id:String = ""
    var token:String = ""
    var tpaga_payment_url:String = ""
    var status:String = ""
    var expires_at:String = ""
    var cancelled_at:String = ""
    var checked_by_merchant_at:String = ""
    var delivery_notification_at:String = ""
}