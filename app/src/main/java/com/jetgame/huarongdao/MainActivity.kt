package com.jetgame.huarongdao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetgame.huarongdao.ui.theme.ComposehuarongdaoTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposehuarongdaoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    var chessState: List<Chess> by remember {
                        mutableStateOf(opening.toList())
                    }

                    ChessBoard(
                        chessList = chessState,
                        onRest = {
                            chessState = opening.toList()
                        }
                    ) { cur, x, y ->
                        chessState = chessState.map {
                            if (it.name == cur) {
                                if (x != 0) it.checkAndMoveX(x, chessState)
                                else it.checkAndMoveY(y, chessState)
                            } else {
                                it
                            }
                        }
                    }
                }
            }
        }
    }
}


private fun Chess.checkAndMoveX(x: Int, others: List<Chess>): Chess {
    others.filter { it.name != name }.forEach { other ->
        when {
            this isToLeftOf other -> {
                if (this.right + x >= other.left) return moveToX(other.left - width)
            }
            this isToRightOf other -> {
                if (this.left + x <= other.right) return moveToX(other.right)
            }
        }
    }
    return moveByX(x)
}

private fun Chess.checkAndMoveY(y: Int, others: List<Chess>): Chess {
    others.filter { it.name != name }.forEach { other ->
        when {
            this isAboveOf other -> {
                if (this.bottom + y >= other.top) return moveToY(other.top - height)
            }
            this isBelowOf other -> {
                if (this.top + y <= other.bottom) return moveToY(other.bottom)
            }
        }
    }
    return moveByY(y)
}


@Composable
fun ChessBoard(
    chessList: List<Chess>,
    onRest: () -> Unit = {},
    onMove: (cur: String, x: Int, y: Int) -> Unit = { _, _, _ -> }
) {

    val scope = rememberCoroutineScope()
    with(LocalDensity.current) {

        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .width(boardWidth.toDp())
                    .height(boardHeight.toDp())
                    .border(1.dp, Color.Cyan)
                    .align(Alignment.TopCenter)
            ) {
                chessList.forEach { chess ->
                    Box(
                        Modifier
                            .offset { chess.offset }
                            .width(chess.width.toDp())
                            .height(chess.height.toDp())
                            .border(1.dp, Color.Black)
                            .background(chess.color)
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
                        Text(chess.name)
                    }
                }
            }

            Button(onClick = onRest, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "reset")
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposehuarongdaoTheme {
        ChessBoard(
            chessList = opening.toList(),
        )
    }
}