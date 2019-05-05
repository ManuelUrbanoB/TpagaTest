package development.tpaga.co.ui.modules.main.data

import android.arch.persistence.room.Room
import android.content.Context

object AppDBPayment {
 lateinit var databasePayment: DatabasePayment
    fun init(context: Context){
        databasePayment = Room.databaseBuilder(context,DatabasePayment::class.java, "database-payment").build()
    }
}