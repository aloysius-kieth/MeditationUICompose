package com.example.meditationcompose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcompose.BottomMenuContent
import com.example.meditationcompose.Feature
import com.example.meditationcompose.R
import com.example.meditationcompose.standardFromTo
import com.example.meditationcompose.ui.theme.*

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation for noobs",
                        iconId = R.drawable.ic_headphone,
                        lightColor = BlueViolet1,
                        mediumColor = BlueViolet2,
                        darkColor = BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        iconId = R.drawable.ic_videocam,
                        lightColor = LightGreen1,
                        mediumColor = LightGreen2,
                        darkColor = LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        iconId = R.drawable.ic_headphone,
                        lightColor = OrangeYellow1,
                        mediumColor = OrangeYellow2,
                        darkColor = OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        iconId = R.drawable.ic_headphone,
                        lightColor = Beige1,
                        mediumColor = Beige2,
                        darkColor = Beige3
                    ),
                )
            )
        }
        BottomMenu(
            menuContents = listOf(
                BottomMenuContent(
                    title = "Home",
                    iconId = R.drawable.ic_home,
                ),
                BottomMenuContent(
                    title = "Meditate",
                    iconId = R.drawable.ic_bubble,
                ),
                BottomMenuContent(
                    title = "Sleep",
                    iconId = R.drawable.ic_moon,
                ),
                BottomMenuContent(
                    title = "Music",
                    iconId = R.drawable.ic_music,
                ),
                BottomMenuContent(
                    title = "Profile",
                    iconId = R.drawable.ic_profile,
                ),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GreetingSection(name: String = "Aloysius") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good Morning $name",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "We wish you have a good day! $name",
                style = MaterialTheme.typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue)
                    .padding(15.dp)
                    .clickable {
                        selectedChipIndex = it
                    },
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Meditation • 3-10min",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    ButtonBlue
                )
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 7.5.dp,
                end = 7.5.dp,
                bottom = 100.dp
            ),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPt1 = Offset(0f, height * 0.3f)
        val mediumColoredPt2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPt3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPt4 = Offset(width * 75f, height * 0.7f)
        val mediumColoredPt5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPt1.x, mediumColoredPt1.y)
            standardFromTo(mediumColoredPt1, mediumColoredPt2)
            standardFromTo(mediumColoredPt2, mediumColoredPt3)
            standardFromTo(mediumColoredPt4, mediumColoredPt5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        val lightColoredPt1 = Offset(0f, height * 0.35f)
        val lightColoredPt2 = Offset(width * 0.1f, height * 0.4f)
        val lightColoredPt3 = Offset(width * 0.3f, height * 0.35f)
        val lightColoredPt4 = Offset(width * 0.65f, height.toFloat())
        val lightColoredPt5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightColoredPt1.x, lightColoredPt2.y)
            standardFromTo(lightColoredPt1, lightColoredPt2)
            standardFromTo(lightColoredPt2, lightColoredPt3)
            standardFromTo(lightColoredPt4, lightColoredPt5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath, color = feature.mediumColor)
            drawPath(path = lightColoredPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
            //.padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(15.dp)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
            )
//            Text(
//                text = "Start",
//                color = TextWhite,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .clickable {
//
//                    }
//                    .align(Alignment.BottomEnd)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(ButtonBlue)
//                    .padding(vertical = 6.dp, horizontal = 15.dp)
//            )
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonBlue
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    /* NO-OP */
                }) {
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun BottomMenu(
    menuContents: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighLight: Color = ButtonBlue,
    activeText: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedIndex: Int = 0
) {
    var selectedIndex by remember {
        mutableStateOf(initialSelectedIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        menuContents.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedIndex,
                activeHighLight = activeHighLight,
                activeText = activeText,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighLight: Color = ButtonBlue,
    activeText: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClicked()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighLight else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeText else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(text = item.title, color = if (isSelected) activeText else inactiveTextColor)
    }
}







