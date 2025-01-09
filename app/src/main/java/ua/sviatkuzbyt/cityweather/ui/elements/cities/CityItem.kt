package ua.sviatkuzbyt.cityweather.ui.elements.cities

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.theme.Black
import ua.sviatkuzbyt.cityweather.ui.theme.BlackTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.iconLargeSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

enum class CityBackground{
    BLue, BlueDark, White, WhiteDark, Gray, GrayDark
}

data class CityData(
    val name: String,
    val temperature: String,
    val windSpeed: String,
    @DrawableRes val image: Int,
    val weatherDescription: Int,
    val background: CityBackground
)

fun getContrastingColor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() > 0.5f) {
        Black
    } else {
        Color.White
    }
}
fun getContrastingTransparentColor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() > 0.5f) {
        BlackTransparent
    } else {
        WhiteTransparent
    }
}

@Preview(showBackground = true, device = "spec:width=2080px,height=2340px,dpi=440")
@Composable
fun CityItem(
    data: CityData = CityData("Sambir", "+5 C", "4 m/s", R.drawable.weather_02n, R.string.weather_clear, CityBackground.BLue)
){
    val background = getCityBackground(data.background)
     ConstraintLayout(
         modifier = Modifier
             .fillMaxWidth()
             .padding(bottom = spaceMedium)
             .background(
                 brush = Brush.linearGradient(
                    colors = background,
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                 shape = RoundedCornerShape(spaceMedium)
             )
             .padding(vertical = spaceMedium, horizontal = spaceLarge)

     ) {
         val (cityText, tempText, windText, weatherIcon) = createRefs()
         val infoBarrier = createStartBarrier(tempText, windText)

        Text(
            text = data.name,
            style = MaterialTheme.typography.headlineMedium,
            color = getContrastingColor(background[0]),
            modifier = Modifier.constrainAs(cityText){
                centerVerticallyTo(parent)
                end.linkTo(
                    anchor = infoBarrier,
                    margin = spaceMedium)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            }
        )

         Image(
             imageVector = ImageVector.vectorResource(data.image),
             contentDescription = stringResource(data.weatherDescription),
             modifier = Modifier
                 .size(iconLargeSize)
                 .constrainAs(weatherIcon){
                     end.linkTo(parent.end)
                     top.linkTo(tempText.top)
                     bottom.linkTo(windText.bottom)
                 }
         )

         Text(
             text = data.temperature,
             style = MaterialTheme.typography.displayLarge,
             color = getContrastingColor(background[0]),
             modifier = Modifier.constrainAs(tempText){
                 end.linkTo(
                     anchor = weatherIcon.start,
                     margin = spaceLarge
                 )
             }
        )

         Text(
             text = "${stringResource(R.string.wind_speed)} ${data.windSpeed}",
             style = MaterialTheme.typography.displaySmall,
             color = getContrastingTransparentColor(background[0]),
             modifier = Modifier.constrainAs(windText){
                 end.linkTo(
                     anchor = weatherIcon.start,
                     margin = spaceLarge
                 )
                 top.linkTo(tempText.bottom)
             }
         )
     }
}

fun getCityBackground(background: CityBackground) =
    when(background){
        CityBackground.BLue -> {
            listOf(Color(0xFF37AFE1),  Color(0xFF008DDA))
        }
        CityBackground.BlueDark -> {
            listOf(Color(0xFF003285),  Color(0xFF071952))
        }
        CityBackground.White -> {
            listOf(Color(0xFFD2E4F1),  Color(0xFF8FACC0))
        }
        CityBackground.WhiteDark -> {
            listOf(Color(0xFF6C7EA1),  Color(0xFF4B6389))
        }
        CityBackground.Gray -> {
            listOf(Color(0xFF6C7782),  Color(0xFF8C98A5))
        }
        CityBackground.GrayDark -> {
            listOf(Color(0xFF263045),  Color(0xFF465169))
        }
    }