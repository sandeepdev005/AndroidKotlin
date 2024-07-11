package com.singlepoint.androidkotlin.architecure.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.http.GET

//Retrofit Service

interface APIService{

    @GET("endpoint")
    suspend fun getAllItems() : List<MyDataModel>
}

//ROOM
@Entity(tableName = "datamodel")
data class MyDataModel(
   @PrimaryKey val id: Int,
   val name: String
)

@Dao
interface DataDao{
    @Query("SELECT * FROM datamodel")
    fun getAllData() : Flow<List<MyDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data : List<MyDataModel>)
}

@Database(entities = [MyDataModel::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun yourDataDao() : DataDao
}



//Repository

class MyRepository(private val apiService: APIService , private val dataDao: DataDao) {
    val data : Flow<List<MyDataModel>> = dataDao.getAllData()

    suspend fun refreshData(){
        val newData  = apiService.getAllItems()
        dataDao.insertAll(newData)
    }
}

//Utils

class NetworkUtils(val context: Context) {
    fun isNetworkAvailable() = true
}

//ViewModel

class MyViewModelMVVM(private val repository: MyRepository, private val networkUtils: NetworkUtils) : ViewModel(){

    val data : LiveData<List<MyDataModel>> = repository.data.asLiveData()
    
    fun refreshData(){
        viewModelScope.launch {
            if(networkUtils.isNetworkAvailable()){
                repository.refreshData()
            }
        }
    }
}


//View