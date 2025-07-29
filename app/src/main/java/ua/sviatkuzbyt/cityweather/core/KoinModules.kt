package ua.sviatkuzbyt.cityweather.core

import androidx.room.Room
import com.google.gson.Gson
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import ua.sviatkuzbyt.cityweather.data.database.DatabaseRoom
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastRepository
import ua.sviatkuzbyt.cityweather.data.repositories.SettingsRepository
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.ui.screens.cities.CitiesViewModel
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.ForecastViewModel
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastScreenType
import ua.sviatkuzbyt.cityweather.ui.screens.settings.SettingsViewModel

val viewModelsModule = module {
    viewModelOf(::CitiesViewModel)
    viewModelOf(::SettingsViewModel)

    viewModel { (type: ForecastScreenType, city: String) ->
        ForecastViewModel(
            repository = get(),
            type = type,
            city = city
        )
    }
}

val repositoriesModule = module {
    factoryOf(::CitiesRepository)
    factoryOf(::ForecastRepository)
    factoryOf(::SettingsRepository)
}

val apiModule = module {
    single<Gson> { Gson() }
    factoryOf(::CurrentWeatherManager)
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

    singleOf(::SettingsStoreManager)
}