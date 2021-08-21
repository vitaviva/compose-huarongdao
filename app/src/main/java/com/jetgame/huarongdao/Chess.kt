package com.jetgame.huarongdao

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color


sealed class Chess(val name: String, val color: Color, val w: Int, val h: Int) {
    operator fun plus(offset: Offset): ChessOffset {
        return ChessOffset(this, offset)
    }
}

object Zhang : Chess("张飞", Color.Gray, 1, 2)
object Caoco : Chess("曹操", Color.White, 2, 2)
object Huang : Chess("黄忠", Color.Yellow, 1, 2)
object Zhaoy : Chess("赵云", Color.Blue, 1, 2)
object Macho : Chess("马超", Color.LightGray, 1, 2)
object Guany : Chess("关羽", Color.Red, 2, 1)
data class Zuuu(val index: Int) : Chess("卒", Color.Green, 1, 1)

val boardUnit = 50/*dp*/
val boardWidth = boardUnit * 4
val boardHeight = boardUnit * 5

data class ChessOffset(val chess: Chess, val offset: Offset)


@OptIn(ExperimentalStdlibApi::class)
val startBoard: Map<Chess, Pair<Int, Int>> = buildMap {

    put(Zhang, 0 to 0)
    put(Caoco, 1 to 0)
    put(Huang, 3 to 0)
    put(Zhaoy, 0 to 2)
    put(Macho, 3 to 2)
    put(Guany, 1 to 2)
    put(Zuuu(1), 0 to 4)
    put(Zuuu(2), 1 to 4)
    put(Zuuu(3), 2 to 4)
    put(Zuuu(4), 3 to 4)
}


fun Map<Chess, Pair<Int, Int>>.toOffset(density: Float) =
    entries.map {
        ChessOffset(
            it.key,
            Offset(
                (it.value.first * boardUnit * density),
                (it.value.second * boardUnit * density)
            )
        )
    }
