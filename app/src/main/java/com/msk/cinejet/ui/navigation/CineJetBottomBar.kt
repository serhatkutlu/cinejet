package com.msk.cinejet.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.msk.cinejet.ui.util.Constants
import com.msk.design_system.extension.customBorder


@Composable
fun CineJetBottomBar(
    topLevelDestinations: List<CineJetTopLevelNavigation>,
    currentTopLevelNavigation: CineJetTopLevelNavigation?,
    onNavigate: (CineJetTopLevelNavigation) -> Unit
) {


    val selectedIndex =
        topLevelDestinations.indexOfFirst { it == currentTopLevelNavigation }.coerceAtLeast(0)

    val screenWidthPx = with(LocalDensity.current) {
        (LocalConfiguration.current.screenWidthDp.dp).toPx()
    }

    val indicatorOffset by animateDpAsState(
        targetValue = with(LocalDensity.current) {
            ((selectedIndex + 0.5f) * screenWidthPx / topLevelDestinations.size).toDp()
        },
        label = "indicatorOffset"
    )

    Surface(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
               WindowInsetsSides.Horizontal +
                        WindowInsetsSides.Bottom
            )
        )
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(Constants.BOTTOM_NAV_BAR_HEIGHT.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)

        ) {

            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .offset {
                        IntOffset(
                            x = (indicatorOffset - 40.dp).roundToPx(),
                            y = 0
                        )
                    }
                    .width(80.dp)
                    .fillMaxHeight(0.8f)
                    .customBorder(radius = 16, color = MaterialTheme.colorScheme.onPrimary)
                    .clip(RoundedCornerShape(16.dp))

                    .background(MaterialTheme.colorScheme.primary)
                    .animateContentSize()
            )

            Row(
                Modifier
                    .fillMaxSize() ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                topLevelDestinations.forEach { navItem ->

                    val isSelected = currentTopLevelNavigation?.route?.route?.startsWith(navItem.route.route) ?: false
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onNavigate(navItem)
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = if (isSelected) navItem.selectedIcon else navItem.unselectedIcon,
                            contentDescription = navItem.route.route,
                            tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(24.dp)
                        )
                        AnimatedVisibility(visible = isSelected) {
                            Text(
                                text = stringResource(id =navItem.title),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        }

    }

}
