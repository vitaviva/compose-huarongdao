package com.jetgame.huarongdao

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset

const val boardGrid = 150/*px*/
const val boardWidth = boardGrid * 4
const val boardHeight = boardGrid * 5

data class Chess(
    val name: String,
    val color: Color,
    val w: Int,
    val h: Int,
    val offset: IntOffset = IntOffset(0, 0) /*px*/
)

fun Chess.moveBy(offset: IntOffset) = copy(offset = this.offset + offset)
fun Chess.moveByX(x: Int) = moveBy(IntOffset(x, 0))
fun Chess.moveByY(y: Int) = moveBy(IntOffset(0, y))
fun Chess.moveToX(x: Int) = copy(offset = offset.copy(x = x))
fun Chess.moveToY(y: Int) = copy(offset = offset.copy(y = y))

val Chess.width get() = w * boardGrid
val Chess.height get() = h * boardGrid
val Chess.left get() = offset.x
val Chess.top get() = offset.y
val Chess.right get() = left + width
val Chess.bottom get() = top + height

infix fun Chess.isToRightOf(other: Chess) =
    (left >= other.right) && ((top in other.top until other.bottom) || (bottom in other.top + 1 until other.bottom))

infix fun Chess.isToLeftOf(other: Chess) =
    (right <= other.left) && ((top in other.top until other.bottom) || (bottom in other.top + 1 until other.bottom))

infix fun Chess.isAboveOf(other: Chess) =
    (bottom <= other.top) && ((left in other.left until other.right) || (right in other.left + 1 until other.right))

infix fun Chess.isBelowOf(other: Chess) =
    (top >= other.bottom) && ((left in other.left until other.right) || (right in other.left + 1 until other.right))

