package com.jetgame.huarongdao

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import kotlin.math.max
import kotlin.math.min

const val boardGridPx = 200
const val boardWidth = boardGridPx * 4
const val boardHeight = boardGridPx * 5

data class Chess(
    val name: String,
    val assets: @Composable () -> Int,
    val w: Int,
    val h: Int,
    val offset: IntOffset = IntOffset(0, 0)
)

fun Chess.moveBy(offset: IntOffset) = copy(offset = this.offset + offset)
fun Chess.moveByX(x: Int) = moveBy(IntOffset(x, 0))
fun Chess.moveByY(y: Int) = moveBy(IntOffset(0, y))

val Chess.width get() = w * boardGridPx
val Chess.height get() = h * boardGridPx
val Chess.left get() = offset.x
val Chess.top get() = offset.y
val Chess.right get() = left + width
val Chess.bottom get() = top + height


infix fun Chess.isToRightOf(other: Chess) =
    (left >= other.right) && ((top until bottom) intersect (other.top until other.bottom)).isNotEmpty()

infix fun Chess.isToLeftOf(other: Chess) =
    (right <= other.left) && ((top until bottom) intersect (other.top until other.bottom)).isNotEmpty()

infix fun Chess.isAboveOf(other: Chess) =
    (bottom <= other.top) && ((left until right) intersect (other.left until other.right)).isNotEmpty()

infix fun Chess.isBelowOf(other: Chess) =
    (top >= other.bottom) && ((left until right) intersect (other.left until other.right)).isNotEmpty()



fun Chess.checkAndMoveX(x: Int, others: List<Chess>): Chess {
    others.filter { it.name != name }.forEach { other ->
        if (x > 0 && this isToLeftOf other && right + x >= other.left)
            return moveByX(other.left - right)
        else if (x < 0 && this isToRightOf other && left + x <= other.right)
            return moveByX(other.right - left)
    }
    return if (x > 0) moveByX(min(x, boardWidth - right)) else moveByX(max(x, 0 - left))

}

fun Chess.checkAndMoveY(y: Int, others: List<Chess>): Chess {
    others.filter { it.name != name }.forEach { other ->
        if (y > 0 && this isAboveOf other && bottom + y >= other.top)
            return moveByY(other.top - bottom)
        else if (y < 0 && this isBelowOf other && top + y <= other.bottom)
            if (top + y <= other.bottom) return moveByY(other.bottom - top)
    }
    return if (y > 0) moveByY(min(y, boardHeight - bottom)) else moveByY(max(y, 0 - top))
}
