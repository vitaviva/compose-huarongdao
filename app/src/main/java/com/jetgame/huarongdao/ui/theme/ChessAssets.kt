package com.jetgame.huarongdao.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.jetgame.huarongdao.R


sealed interface ChessAssets {

    val huangzhong: Int
    val caocao: Int
    val zhaoyun: Int
    val machao: Int
    val zhangfei: Int
    val guanyu: Int
    val zu: Int

}


object LightChess : ChessAssets {
    override val huangzhong = R.drawable.huagnzhong
    override val caocao = R.drawable.caocao
    override val zhaoyun = R.drawable.zhaoyun
    override val zhangfei = R.drawable.zhangfei
    override val guanyu = R.drawable.guanyu
    override val machao = R.drawable.machao
    override val zu = R.drawable.zu
}

object DarkChess : ChessAssets {
    override val huangzhong = R.mipmap.huangzhong
    override val caocao = R.mipmap.caocao
    override val zhaoyun = R.mipmap.zhaoyun
    override val zhangfei = R.mipmap.zhangfei
    override val guanyu = R.mipmap.guanyu
    override val machao = R.mipmap.machao
    override val zu = R.mipmap.zu
}


internal var LocalChessAssets = compositionLocalOf<ChessAssets> {
    DarkChess
}

val MaterialTheme.chessAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalChessAssets.current