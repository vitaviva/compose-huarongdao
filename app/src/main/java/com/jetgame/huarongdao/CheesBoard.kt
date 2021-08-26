package com.jetgame.huarongdao

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun Density.ChessBoard(
    modifier: Modifier = Modifier,
    chessList: List<Chess>,
    onMove: (cur: String, x: Int, y: Int) -> Unit = { _, _, _ -> }
) {

    val scope = rememberCoroutineScope()

    Box(
        modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .background(MaterialTheme.colors.secondary.copy(alpha = 0.2f))
                .padding(10.dp)
                .width(boardWidth.toDp())
                .height(boardHeight.toDp())
                .background(MaterialTheme.colors.secondary)
                .align(Alignment.Center)
        ) {
            chessList.forEach { chess ->
                Image(
                    modifier = Modifier
                        .offset { chess.offset }
                        .width(chess.width.toDp())
                        .height(chess.height.toDp())
                        .border(1.dp, Color.Black)
                        .draggable( //demonstration draggable
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState(onDelta = {
                                onMove(chess.name, it.roundToInt(), 0)
                            })
                        )
                        .pointerInput(Unit) {
                            scope.launch {//demonstrate detectDragGestures
                                detectVerticalDragGestures { change, dragAmount ->
                                    change.consumeAllChanges()
                                    onMove(chess.name, 0, dragAmount.roundToInt())
                                }
                            }

                        },
                    painter = painterResource(id = chess.assets()),
                    contentDescription = chess.name
                )
            }
        }

    }
}

