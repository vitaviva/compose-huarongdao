package com.jetgame.huarongdao

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
                .background(MaterialTheme.colors.secondary.copy(alpha = 0.2f))
                .align(Alignment.Center)
        ) {
            chessList.forEach { chess ->
                Box(
                    Modifier
                        .offset { chess.offset }
                        .width(chess.width.toDp())
                        .height(chess.height.toDp())
                        .border(1.dp, Color.Black)
                        .pointerInput(Unit) {
                            scope.launch {
                                detectHorizontalDragGestures { change, dragAmount ->
                                    change.consumeAllChanges()
                                    onMove(chess.name, dragAmount.roundToInt(), 0)
                                }
                            }
                            scope.launch {
                                detectVerticalDragGestures { change, dragAmount ->
                                    change.consumeAllChanges()
                                    onMove(chess.name, 0, dragAmount.roundToInt())
                                }
                            }

                        }) {
                    Image(painter = painterResource(id = chess.assets()), contentDescription = "")
                }
            }
        }

    }
}

