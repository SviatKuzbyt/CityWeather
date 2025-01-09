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
class DataBaseTest {

    private val isPrintResult = true
    private lateinit var dataBaseDao: DataBaseDao

    private val dataToInsert = listOf(
        CityEntity(0, "City One", 0),
        CityEntity(0, "City Two", 1),
        CityEntity(0, "City Three", 2),
        CityEntity(0, "City Four", 3)
    )

    private val expectedInsertData = listOf(
        CityEntity(1, "City One", 0),
        CityEntity(2, "City Two", 1),
        CityEntity(3, "City Three", 2),
        CityEntity(4, "City Four", 3)
    )

    private val expectedMoveData = listOf(
        CityEntity(3, "City Three", 0),
        CityEntity(1, "City One", 1),
        CityEntity(2, "City Two", 2),
        CityEntity(4, "City Four", 3)
    )

    private val expectedDeleteData = listOf(
        CityEntity(3, "City Three", 0),
        CityEntity(1, "City One", 1),
        CityEntity(4, "City Four", 2)
    )

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataBaseDao = DataBaseManager.getDao(context)
    }

    //Insert all data to DB
    @Test
    fun addCities(){
        dataToInsert.forEach {
            dataBaseDao.addCity(it)
        }
        val resultData = dataBaseDao.getCities()

        if (isPrintResult){
            printResult(expectedInsertData, resultData)
        }

        Assert.assertEquals(expectedInsertData, resultData)
    }

    @Test
    fun moveCity(){
        val positionToMove = 2
        val updateId = 3L

        dataBaseDao.moveCitiesDown(positionToMove)
        dataBaseDao.moveCityUp(updateId)

        val resultData = dataBaseDao.getCities()

        if (isPrintResult){
            printResult(expectedMoveData, resultData)
        }

        Assert.assertEquals(expectedMoveData, resultData)
    }

    @Test
    fun deleteCity(){
        val deleteID = 2L
        val deletePosition = 2

        dataBaseDao.moveCitiesUp(deletePosition)
        dataBaseDao.deleteCity(deleteID)

        val resultData = dataBaseDao.getCities()

        if (isPrintResult){
            printResult(expectedDeleteData, resultData)
        }

        Assert.assertEquals(expectedDeleteData, resultData)
    }


    private fun printResult(
        expectedData: List<CityEntity>,
        realData: List<CityEntity>,
    ){
        var result = "SKLT: RESULT OF TEST\nEXPECTED DATA: \n"
        expectedData.forEach {
            result += "${it.id} - ${it.name} - ${it.position}\n"
        }

        result += "REAL DATA:\n"
        realData.forEach {
            result += "${it.id} - ${it.name} - ${it.position}\n"
        }

        println(result)
    }
}