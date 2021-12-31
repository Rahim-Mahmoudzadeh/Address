package ir.rahimmahmoudzadeh.address.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.rahimmahmoudzadeh.address.data.model.LocationInformationEntity

@Database(entities = [LocationInformationEntity::class] , version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
 abstract fun addressDao():AddressDao
}