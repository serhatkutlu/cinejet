package com.msk.design_system.components

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.msk.core.design_system.R

import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun <T> SwipeToDeleteItem(
    item: T,
    itemId: Long,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Red,
    swipedItemId: Long?,
    onItemSwiped: (Long) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX, label = "offset")
    var deleteButtonWidth by remember {
        mutableFloatStateOf(0f)
    }
    val deleteButtonWidthDp = with(LocalDensity.current) {
        deleteButtonWidth.toDp()
    }
    val density = LocalDensity.current


    val threshold = with(density) { 100.dp.toPx() }

    val isSwiped = itemId == swipedItemId

    LaunchedEffect(swipedItemId) {
        if (!isSwiped) offsetX = 0f
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min)
            .onGloballyPositioned {
                deleteButtonWidth = (it.size.width / 4).toFloat()
            }
    ) {

        Box(
            modifier = Modifier
                .width(deleteButtonWidthDp)
                .fillMaxHeight()
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterEnd)
                .clickable {
                    onDelete()
                }
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.delete),
                color = Color.White,
            )
        }
        // Foreground (swipeable item content)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX = (offsetX + dragAmount).coerceIn(-deleteButtonWidth, 0f)
                        },
                        onDragEnd = {
                            if (abs(offsetX) > threshold) {
                                offsetX = -deleteButtonWidth
                                onItemSwiped(itemId)
                            } else {
                                offsetX = 0f
                            }
                        }
                    )
                }
        ) {
            content()
        }

    }
}

