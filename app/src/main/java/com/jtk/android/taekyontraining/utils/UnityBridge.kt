package com.jtk.android.taekyontraining.utils

import com.unity3d.player.UnityPlayer
import com.jtk.android.taekyontraining.protocol.AndroidToUnityMessages
import com.unity3d.player.UnityPlayerGameActivity

object UnityBridge {
    fun sendMessage(
        gameObject: String?,
        method: String?,
        message: String?
    ) {
        UnityPlayer.UnitySendMessage(gameObject, method, message)
    }
}