package com.jtk.android.taekyontraining.unity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jtk.android.taekyontraining.R
import com.jtk.android.taekyontraining.databinding.ActivityUnityHostButtonsBinding
import com.jtk.android.taekyontraining.protocol.AndroidToUnityMessages
import com.jtk.android.taekyontraining.utils.UnityBridge
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayerGameActivity
import java.io.File

class UnityHostActivity : UnityPlayerGameActivity() {

    private lateinit var binding: ActivityUnityHostButtonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBottomButtons()
    }

    private fun addBottomButtons() {
        val contentRoot = findViewById<FrameLayout>(android.R.id.content)
        val buttonBar = LayoutInflater.from(this).inflate(
            R.layout.activity_unity_host_buttons,
            contentRoot,
            false
        )
        binding = ActivityUnityHostButtonsBinding.bind(buttonBar)

        binding.startButton.setOnClickListener {
            UnityPlayer.UnitySendMessage(
                "AndroidBridge", "ReceiveMessage", AndroidToUnityMessages.START_SESSION)
        }
        binding.nextButton.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                "NEXT_FRAME"
            )
        }
        binding.prevButton.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                "PREVIOUS_FRAME"
            )
        }
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply { gravity = Gravity.BOTTOM }
        contentRoot.addView(buttonBar, params)
    }

    override fun onResume() {
        super.onResume()
        sendStartSessionToUnity()
    }

    private fun sendStartSessionToUnity() {

        val motionPath = copyTestMotionToInternalStorage(this)
        Log.d("MotionPath", "Motion file at: $motionPath")
        UnityBridge.sendMessage(
            "AndroidBridge",
            "ReceiveMessage",
            "LOAD_MOTION:$motionPath"
        )


//        val unityPlayer = AndroidJavaClass("com.unity3d.player.UnityPlayer")
//        unityPlayer.callStatic(
//            "UnitySendMessage",
//            "AndroidBridge",
//            "ReceiveMessage",
//            AndroidToUnityMessages.START_SESSION
//        )
    }


}
fun copyTestMotionToInternalStorage(context: Context): String {
    val fileName = "test_motion.json"
    val outFile = File(context.filesDir, fileName)

    if (!outFile.exists()) {
        context.assets.open(fileName).use { input ->
            outFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    return outFile.absolutePath
}