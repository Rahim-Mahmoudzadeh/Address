package ir.rahimmahmoudzadeh.address.data.db

import android.content.Context
import androidx.room.Room
import ir.rahimmahmoudzadeh.address.utils.App
import ir.rahimmahmoudzadeh.address.utils.Constants.DATABASE_NAME

object RoomBuilder {
    @Volatile
    private var instance: AppDataBase? = null
    fun getInstance(context: Context): AppDataBase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }

    private fun buildDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()
    }
}