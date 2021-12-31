package ir.rahimmahmoudzadeh.address.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.model.LocationInformationEntity

@Dao
interface AddressDao {
    @Insert
    suspend fun insert(locationInformation: List<LocationInformationEntity>)

    @Query("DELETE FROM LocationInformationEntity")
    suspend fun delete()

    @Query("SELECT * FROM LocationInformationEntity")
    suspend fun getAddress(): List<LocationInformationEntity>
}