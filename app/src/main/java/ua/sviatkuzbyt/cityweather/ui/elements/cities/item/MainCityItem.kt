package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityData
import ua.sviatkuzbyt.cityweather.ui.theme.iconLargeSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun MainCityItem(data: CityData, contentColor: Color, contentTransparent: Color){
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = mainCityConstraints()
    ) {

        Text(
            text = data.name,
            style = MaterialTheme.typography.headlineMedium,
            color = contentColor,
            modifier = Modifier.layoutId("cityText")
        )

        Image(
            imageVector = ImageVector.vectorResource(data.image),
            contentDescription = stringResource(data.weatherDescription),
            modifier = Modifier
                .size(iconLargeSize)
                .layoutId("weatherIcon")
        )

        Text(
            text = data.temperature,
            style = MaterialTheme.typography.displayLarge,
            color = contentColor,
            modifier = Modifier.layoutId("tempText")
        )

        Text(
            text = "${stringResource(R.string.wind_speed)} ${data.windSpeed}",
            style = MaterialTheme.typography.displaySmall,
            color = contentTransparent,
            modifier = Modifier.layoutId("windText")
        )
    }
}

private fun mainCityConstraints() = ConstraintSet {
    val (cityText, tempText, windText, weatherIcon) =
        createRefsFor("cityText", "tempText", "windText", "weatherIcon")

    val infoBarrier = createStartBarrier(tempText, windText)

    constrain(cityText){
        centerVerticallyTo(parent)
        end.linkTo(
            anchor = infoBarrier,
            margin = spaceMedium
        )
        start.linkTo(parent.start)
        width = Dimension.fillToConstraints
    }

    constrain(tempText){
        end.linkTo(
            anchor = weatherIcon.start,
            margin = spaceLarge
        )
    }

    constrain(windText){
        end.linkTo(
            anchor = weatherIcon.start,
            margin = spaceLarge
        )
        top.linkTo(tempText.bottom)
    }

    constrain(weatherIcon){
        end.linkTo(parent.end)
        top.linkTo(tempText.top)
        bottom.linkTo(windText.bottom)
    }
}