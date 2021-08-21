package com.jetgame.huarongdao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jetgame.huarongdao.ui.theme.ComposehuarongdaoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposehuarongdaoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val density = LocalDensity.current
                    var chessState: List<ChessOffset> by remember {
                        mutableStateOf(startBoard.toOffset(density.density))
                    }

                    ChessBoard(
                        chesses = chessState
                    ) {
                        chessState = chessState.moveBy(it)

                    }
                }
            }
        }
    }
}

@Composable
fun ChessBoard(
    chesses: List<ChessOffset>,
    onMove: (offset: ChessOffset) -> Unit = {}
) {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(boardWidth.dp)
                .height(boardHeight.dp)
                .border(1.dp, Color.Cyan)
                .align(Alignment.TopCenter)
        ) {
            chesses.forEach {
                Box(
                    Modifier
                        .offset { IntOffset(it.offset.x.roundToInt(), it.offset.y.roundToInt()) }
                        .width((it.chess.w * boardUnit).dp)
                        .height((it.chess.h * boardUnit).dp)
                        .border(1.dp, Color.Black)
                        .background(it.chess.color)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consumeAllChanges()
                                onMove(it.chess + dragAmount)
                            }
                        }) {
                    Text(it.chess.name)
                }
            }
        }

    }


}

private fun List<ChessOffset>.moveBy(chessOffset: ChessOffset) =
    map {
        if (it.chess == chessOffset.chess) {
            it.chess + (chessOffset.offset + it.offset)
        } else {
            it
        }
    }


private fun List<ChessOffset>.detectBorder(chessOffset: ChessOffset) {

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposehuarongdaoTheme {
        ChessBoard(
            chesses = startBoard.toOffset(2.75f),
        )
    }
}