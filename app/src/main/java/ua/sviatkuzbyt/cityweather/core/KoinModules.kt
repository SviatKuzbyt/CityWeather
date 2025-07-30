package ua.sviatkuzbyt.cityweather.core

import androidx.room.Room
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.sviatkuzbyt.cityweather.data.api.WeatherApi
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import ua.sviatkuzbyt.cityweather.data.database.DatabaseRoom
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastRepository
import ua.sviatkuzbyt.cityweather.data.repositories.SettingsRepository
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType
import ua.sviatkuzbyt.cityweather.ui.screens.cities.CitiesViewModel
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.ForecastViewModel
import ua.sviatkuzbyt.cityweather.ui.screens.settings.SettingsViewModel

val viewModelsModule = module {
    viewModelOf(::CitiesViewModel)
    viewModelOf(::SettingsViewModel)

    viewModel { (type: ForecastType, cityId: Long) ->
        ForecastViewModel(
            repository = get(),
            type = type,
            cityId = cityId
        )
    }
}

val repositoriesModule = module {
    singleOf(::ForecastRepository)
    singleOf(::SettingsRepository)
    singleOf(::CitiesRepository)
}

val storageModule = module {
    single<DatabaseRoom> {
        Room.databaseBuilder(
            get(),
            DatabaseRoom::class.java,
            "city_weather_db"
        ).build()
    }

    single<DataBaseDao> {
        get<DatabaseRoom>().dao()
    }
}

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }
}