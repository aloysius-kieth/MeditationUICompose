package com.example.meditationcompose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) * 0.5f,
        abs(from.y + to.y) * 0.5f
    )
}