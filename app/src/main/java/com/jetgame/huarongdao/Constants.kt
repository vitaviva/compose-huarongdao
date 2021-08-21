package com.jetgame.huarongdao

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset


val zhang = Chess("张飞", Color.Gray, 1, 2)
val cao = Chess("曹操", Color.White, 2, 2)
val huang = Chess("黄忠", Color.Yellow, 1, 2)
val zhao = Chess("赵云", Color.Blue, 1, 2)
val ma = Chess("马超", Color.LightGray, 1, 2)
val guan = Chess("关羽", Color.Red, 2, 1)

@OptIn(ExperimentalStdlibApi::class)
val zu = buildList {
    repeat(4) { add(Chess("卒$it", Color.Green, 1, 1)) }
}

typealias ChessOpening = List<Triple<Chess, Int, Int>>

@OptIn(ExperimentalStdlibApi::class)
val opening: ChessOpening = buildList {
    add(Triple(zhang, 0, 0))
    add(Triple(cao, 1, 0))
    add(Triple(huang, 3, 0))
    add(Triple(zhao, 0, 2))
    add(Triple(ma, 3, 2))
    add(Triple(guan, 1, 2))
    add(Triple(zu[0], 0, 4))
    add(Triple(zu[1], 1, 4))
    add(Triple(zu[2], 2, 4))
    add(Triple(zu[3], 3, 4))
}


fun ChessOpening.toList() =
    map { (chess, x, y) ->
        chess.moveBy(IntOffset(x * boardGrid, y * boardGrid))
    }
