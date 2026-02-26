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
import com.unity3d.player.UnityPlayerGameActivity
import java.io.File

class UnityHostActivity : UnityPlayerGameActivity() {

    private lateinit var binding: ActivityUnityHostButtonsBinding
    private var bottomButtonsAdded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateSurfaceView() {
        super.onCreateSurfaceView()
        if (!bottomButtonsAdded) {
            addBottomButtons()
            bottomButtonsAdded = true
        }
    }

    private fun addBottomButtons() {
        val contentRoot = findViewById<FrameLayout>(android.R.id.content)
        val buttonBar = LayoutInflater.from(this).inflate(
            R.layout.activity_unity_host_buttons,
            contentRoot,
            false
        )
        binding = ActivityUnityHostButtonsBinding.bind(buttonBar)

        binding.btnLoad.setOnClickListener {
            val motionPath = copyTestMotionToInternalStorage(this)
            Log.d("MotionPath", "Motion file at: $motionPath")
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                "LOAD_MOTION:$motionPath"
            )
        }

        binding.btnStart.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                AndroidToUnityMessages.START_SESSION
            )
        }

        binding.btnPause.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                AndroidToUnityMessages.PAUSE_SESSION
            )
        }

        binding.btnResume.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                AndroidToUnityMessages.RESUME_SESSION
            )
        }

        binding.btnSeek1.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                "SEEK_FRAME:0"
            )
        }

//        binding.btnSetHold.setOnClickListener {
//            UnityBridge.sendMessage(
//                "AndroidBridge",
//                "ReceiveMessage",
//                "SET_HOLD:1"
//            )
//        }
//
        binding.btnReleaseHold.setOnClickListener {
            UnityBridge.sendMessage(
                "AndroidBridge",
                "ReceiveMessage",
                "RELEASE_HOLD"
            )
        }

        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ).apply { gravity = Gravity.BOTTOM }
        contentRoot.addView(buttonBar, params)
    }

    override fun onResume() {
        super.onResume()
        sendStartSessionToUnity()
    }

    private fun sendStartSessionToUnity() {
        val motionPath = copyTestMotionToInternalStorage(this)
//        Log.d("MotionPath", "Motion file at: $motionPath")
//        UnityBridge.sendMessage(
//            "AndroidBridge",
//            "ReceiveMessage",
//            "LOAD_MOTION:$motionPath"
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
