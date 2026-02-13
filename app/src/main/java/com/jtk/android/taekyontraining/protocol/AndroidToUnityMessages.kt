package com.jtk.android.taekyontraining.protocol

/**
 * String constants for Android → Unity messages. See Docs/MESSAGE_PROTOCOL.md.
 */
object AndroidToUnityMessages {
    /** Prefix for load-motion command; append path. */
    const val LOAD_MOTION = "LOAD_MOTION:"

    const val START_SESSION = "START_SESSION"

    const val STOP_SESSION = "STOP_SESSION"
}
