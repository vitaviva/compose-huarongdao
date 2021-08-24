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


object DarkChess : ChessAssets {
    override val huangzhong = R.drawable.huangzhong
    override val caocao = R.drawable.caocao
    override val zhaoyun = R.drawable.zhaoyun
    override val zhangfei = R.drawable.zhangfei
    override val guanyu = R.drawable.guanyu
    override val machao = R.drawable.machao
    override val zu = R.drawable.zu
}

object LightChess : ChessAssets {
    override val huangzhong = R.drawable.huagnzhong_2
    override val caocao = R.drawable.caocao_2
    override val zhaoyun = R.drawable.zhaoyun_2
    override val zhangfei = R.drawable.zhangfei_2
    override val guanyu = R.drawable.guanyu_2
    override val machao = R.drawable.machao_2
    override val zu = R.drawable.zu_2
}

object WoodChess : ChessAssets {
    override val huangzhong = R.drawable.huangzhong_3
    override val caocao = R.drawable.caocao_3
    override val zhaoyun = R.drawable.zhaoyun_3
    override val zhangfei = R.drawable.zhangfei_3
    override val guanyu = R.drawable.guanyu_3
    override val machao = R.drawable.machao_3
    override val zu = R.drawable.zu_3
}



internal var LocalChessAssets = compositionLocalOf<ChessAssets> {
    DarkChess
}

val MaterialTheme.chessAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalChessAssets.current