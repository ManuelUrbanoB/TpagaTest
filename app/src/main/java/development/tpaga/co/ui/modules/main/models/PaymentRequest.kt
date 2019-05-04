package development.tpaga.co.ui.modules.main.models

class PaymentRequest {
    var cost:Int = 0
    var purchase_details_url:String = ""
    var voucher_url:String = ""
    var idempotency_token:String = ""
    var order_id:String = ""
    var terminal_id:String = ""
    var purchase_description: String = ""
    var user_ip_address:String = ""
    var expires_at:String = ""
}