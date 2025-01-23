package ua.sviatkuzbyt.cityweather

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import org.junit.Test
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager


@RunWith(AndroidJUnit4::class)
class ExistCityTest {

    private val insertData = listOf(
        CityEntity(0, "City One", 0),
        CityEntity(0, "City Two", 1)
    )

    private val firstTestData = "City One"
    private val firstTestExpect = 1

    private val secondTestData = "City Three"
    private val secondTestExpect = 0

    private lateinit var dataBaseDao: DataBaseDao

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataBaseDao = DataBaseManager.getDao(context)

        insertData.forEach {
            dataBaseDao.addCity(it)
        }
    }

    @Test
    fun checkExistCity(){
        val result = dataBaseDao.checkExistCity(firstTestData)
        Assert.assertEquals(firstTestExpect, result)
    }

    @Test
    fun checkNoExistCity(){
        val result = dataBaseDao.checkExistCity(secondTestData)
        Assert.assertEquals(secondTestExpect, result)
    }
}