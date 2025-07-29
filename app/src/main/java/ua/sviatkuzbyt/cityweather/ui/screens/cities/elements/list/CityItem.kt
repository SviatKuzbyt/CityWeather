package ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicLarge
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicSmall
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextHead
import ua.sviatkuzbyt.cityweather.ui.elements.other.basicClick
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.ControlMenu
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.DetailButton
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.DetailItem
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.getCityBackground
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.getCityItemColors
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements.getLinearBackground
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace36

@Composable
fun CityItem(
    data: CityItemData,
    isOpen: Boolean,
    onClickItem: () -> Unit,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit,
    onTodayClick: (String) -> Unit,
    onFiveDaysClick: (String) -> Unit
){
    // Set colors for content
    val backgrounds = getCityBackground(data.background)
    val colors = getCityItemColors(backgrounds[0])

    //Set width, padding, background and animation
    val modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = sizeSpace16)
        .background(
            brush = getLinearBackground(backgrounds),
            shape = RoundedCornerShape(sizeSpace16)
        )
        .basicClick { onClickItem() }
        .padding(vertical = sizeSpace16, horizontal = sizeSpace20)
        .animateContentSize(
            animationSpec = tween(400, 0)
        )

    //Set UI
    Column(modifier) {
        MainCityItem(data, colors)

        AnimatedVisibility(
            visible = isOpen,
            enter = fadeIn(animationSpec = tween(100)),
            exit = fadeOut(animationSpec = tween(100))
        ) {
            DetailCityItem(data, colors, onDelete, onMoveUp, onTodayClick, onFiveDaysClick)
        }
    }
}

@Composable
fun MainCityItem(data: CityItemData, colors: CityItemColors){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        //City name text
        TextHead(
            text = data.name,
            color = colors.textColor,
            modifier = Modifier
                .weight(1f)
                .padding(end = sizeSpace16)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = sizeSpace20)
        ) {
            //Temperature text
            TextBasicLarge(
                text = data.temperature,
                color = colors.textColor
            )

            //Wind speed text
            TextBasicSmall(
                text = "${stringResource(R.string.wind_speed)} ${data.windSpeed}",
                color = colors.iconColor
            )
        }

        //Weather image
        Image(
            imageVector = ImageVector.vectorResource(data.image),
            contentDescription = stringResource(data.weatherDescription),
            modifier = Modifier.size(sizeSpace36)
        )
    }
}

@Composable
fun DetailCityItem(
    data: CityItemData,
    colors: CityItemColors,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit,
    onTodayClick: (String) -> Unit,
    onFiveDaysClick: (String) -> Unit
){
    //List of weather details
    val infoList = listOf(
        CityItemDetailData(R.drawable.ic_humidity, R.string.humidity, "${data.humidity}%"),
        CityItemDetailData(R.drawable.ic_pressure, R.string.pressure, "${data.pressure} hPa"),
        CityItemDetailData(R.drawable.ic_feels_like, R.string.feels_like, data.feelsLike),
        CityItemDetailData(R.drawable.ic_rain, R.string.precipitation_reliability, "${data.rain} mm/h")
    )

    var showControlMenu by remember { mutableStateOf(false) }

    //Set UI
    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = colors.backColor,
            modifier = Modifier.padding(top = sizeSpace16)
        )

        //Weather details
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = sizeSpace16)
        ) {
            infoList.forEach {
                DetailItem(content = it, colors = colors)
            }
        }

        //Buttons
        DetailButton(label = R.string.forecastToday, colors = colors) {
            onTodayClick(data.name)
        }

        DetailButton(label = R.string.forecastFiveDays, colors = colors) {
            onFiveDaysClick(data.name)
        }

        Box {
            DetailButton(label = R.string.control, colors = colors) {
                showControlMenu = true
            }

            ControlMenu(
                show = showControlMenu,
                onHide = {showControlMenu = false},
                onDelete = onDelete,
                onMoveUp = onMoveUp
            )
        }
    }
}