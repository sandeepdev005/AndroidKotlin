package com.singlepoint.androidkotlin.architecure.mvi

import retrofit2.http.GET

/**
 * MVI is a Architecture design pattern
 * It emphasizes unidirectional data flow and state management
 * In this pattern , View observes the state , Model Holds the state and business logic and intent captures the user action.
 *
 *
 * */


/*
interface ApiService {
    @GET("your_endpoint")
    suspend fun fetchData(): List<YourDataModel>
}


@Entity(tableName = "your_data_table")
data class YourDataModel(
    @PrimaryKey val id: Int,
    val name: String,
    // other fields
)

@Dao
interface YourDataDao {
    @Query("SELECT * FROM your_data_table")
    fun getAllData(): Flow<List<YourDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<YourDataModel>)
}

@Database(entities = [YourDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun yourDataDao(): YourDataDao
}


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your_api_base_url")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "your_database_name"
        ).build()
    }

    @Provides
    fun provideYourDataDao(database: AppDatabase): YourDataDao {
        return database.yourDataDao()
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext appContext: Context): NetworkUtils {
        return NetworkUtils(appContext)
    }
}


//MVI Component
sealed class YourState {
    object Loading : YourState()
    data class Success(val data: List<YourDataModel>) : YourState()
    data class Error(val message: String) : YourState()
}

sealed class YourIntent {
    object LoadData : YourIntent()
}


@Singleton
class YourRepository @Inject constructor(
    private val apiService: ApiService,
    private val yourDataDao: YourDataDao
) {
    val data: Flow<List<YourDataModel>> = yourDataDao.getAllData()

    suspend fun refreshData() {
        val newData = apiService.fetchData()
        yourDataDao.insertAll(newData)
    }
}


@HiltViewModel
class YourViewModel @Inject constructor(
    private val repository: YourRepository,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _state = MutableStateFlow<YourState>(YourState.Loading)
    val state: StateFlow<YourState> = _state

    init {
        handleIntent(YourIntent.LoadData)
    }

    fun handleIntent(intent: YourIntent) {
        viewModelScope.launch {
            when (intent) {
                is YourIntent.LoadData -> {
                    _state.value = YourState.Loading
                    try {
                        if (networkUtils.isNetworkAvailable()) {
                            repository.refreshData()
                        }
                        repository.data.collect { data ->
                            _state.value = YourState.Success(data)
                        }
                    } catch (e: Exception) {
                        _state.value = YourState.Error(e.message ?: "Unknown Error")
                    }
                }
            }
        }
    }
}



class NetworkUtils @Inject constructor(@ApplicationContext private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}


@AndroidEntryPoint
class YourFragment : Fragment() {

    private val viewModel: YourViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentYourBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is YourState.Loading -> {
                        // Show loading
                    }
                    is YourState.Success -> {
                        // Show data
                        binding.textView.text = state.data.toString()
                    }
                    is YourState.Error -> {
                        // Show error
                        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.button.setOnClickListener {
            viewModel.handleIntent(YourIntent.LoadData)
        }

        return binding.root
    }
}


@HiltAndroidApp
class YourApplication : Application()
*/