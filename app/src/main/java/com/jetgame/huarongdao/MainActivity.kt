package com.jetgame.huarongdao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.jetgame.huarongdao.ui.theme.ComposehuarongdaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDark by remember { mutableStateOf(true) }
            ComposehuarongdaoTheme(darkTheme = isDark) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Column {
                        Spacer(Modifier.height(20.dp))

                        Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "华容道")
                        var chessState: List<Chess> by remember {
                            mutableStateOf(opening.toList())
                        }

                        with(LocalDensity.current) {
                            ChessBoard(
                                Modifier.weight(1f),
                                chessList = chessState,
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

                        Row {
                            Spacer(modifier = Modifier.width(10.dp))

                            Button(modifier = Modifier.weight(1f),
                                onClick = { isDark = !isDark }) {
                                Text("Theme")
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = { chessState = opening.toList() }) {
                                Text("Reset")
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                        }
                        Spacer(Modifier.height(20.dp))

                    }

                }
            }
        }
    }
}

@Composable
fun GameScreen() {

}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    ComposehuarongdaoTheme {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    ComposehuarongdaoTheme(darkTheme = true) {
        Density(2.7f, 1f).ChessBoard(
            chessList = opening.toList(),
        )
    }
}