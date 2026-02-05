package org.blindkey.app.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


val RoboFlex = FontFamily(
    Font(
        resource = "roboto_flex",
        weight = FontWeight.Normal,
    ),
    Font(
        resource = "roboto_flex",
        weight = FontWeight.Normal,
    ),
    Font(
        resource = "roboto_flex",
        weight = FontWeight.Medium,
    ),
    Font(
        resource = "roboto_flex",
        weight = FontWeight.Bold,
    ),
)

val baseline = Typography()

val typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = RoboFlex),
    displayMedium = baseline.displayMedium.copy(fontFamily = RoboFlex),
    displaySmall = baseline.displaySmall.copy(fontFamily = RoboFlex),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = RoboFlex),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = RoboFlex),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = RoboFlex),
    titleLarge = baseline.titleLarge.copy(fontFamily = RoboFlex),
    titleMedium = baseline.titleMedium.copy(fontFamily = RoboFlex),
    titleSmall = baseline.titleSmall.copy(fontFamily = RoboFlex),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = RoboFlex),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = RoboFlex),
    bodySmall = baseline.bodySmall.copy(fontFamily = RoboFlex),
    labelLarge = baseline.labelLarge.copy(fontFamily = RoboFlex),
    labelMedium = baseline.labelMedium.copy(fontFamily = RoboFlex),
    labelSmall = baseline.labelSmall.copy(fontFamily = RoboFlex),
)