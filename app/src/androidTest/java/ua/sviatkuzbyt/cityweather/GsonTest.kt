package ua.sviatkuzbyt.cityweather

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData


@RunWith(AndroidJUnit4::class)
class GsonTest {

    private val currentWeatherResponse = """{"coord":{"lon":23.197,"lat":49.5182},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"base":"stations","main":{"temp":4.32,"feels_like":4.32,"temp_min":4.32,"temp_max":4.32,"pressure":1019,"humidity":73,"sea_level":1019,"grnd_level":979},"visibility":10000,"wind":{"speed":1.28,"deg":325,"gust":1.57},"clouds":{"all":100},"dt":1737465596,"sys":{"country":"UA","sunrise":1737439956,"sunset":1737471845},"timezone":7200,"id":694864,"name":"Sambir","cod":200}"""
    private val currentWeatherWithRainResponse = """{"coord":{"lon":23.197,"lat":49.5182},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"base":"stations","main":{"temp":4.32,"feels_like":4.32,"temp_min":4.32,"temp_max":4.32,"pressure":1019,"humidity":73,"sea_level":1019,"grnd_level":979},"visibility":10000,"wind":{"speed":1.28,"deg":325,"gust":1.57},"rain": {"1h": 2.73},"clouds":{"all":100},"dt":1737465596,"sys":{"country":"UA","sunrise":1737439956,"sunset":1737471845},"timezone":7200,"id":694864,"name":"Sambir","cod":200}"""
    private val cityEntity = CityEntity(1, "TestCity", 0)

    private val cityItemDataExpect = CityItemData(
        cityId = cityEntity.id,
        name = cityEntity.name,
        temperature = "4℃",
        windSpeed = "1.28 m/s",
        image = R.drawable.weather_04,
        weatherDescription = "overcast clouds",
        background = CityBackground.Gray,
        humidity = 73,
        pressure = 1019,
        feelsLike = "4℃",
        rain = 0
    )
    private val cityItemDataRainExpect = cityItemDataExpect.copy(rain = 2)

    private val manager = CurrentWeatherManager()

    @Test
    fun convertToCityItem() {
        val result = manager.convertToCityItem(currentWeatherResponse, cityEntity)
        Assert.assertEquals(cityItemDataExpect, result)

    }
    @Test
    fun convertToCityItemRain() {
        val result = manager.convertToCityItem(currentWeatherWithRainResponse, cityEntity)
        Assert.assertEquals(cityItemDataRainExpect, result)
    }
}