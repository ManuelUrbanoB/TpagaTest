package development.tpaga.co.ui.modules.main.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import development.tpaga.co.ui.modules.main.models.PaymentResponse

@Database(entities = [(PaymentResponse::class)], version = 1)
abstract class DatabasePayment  : RoomDatabase(){
    abstract fun paymentResponseDao():PaymentResponseDao
}