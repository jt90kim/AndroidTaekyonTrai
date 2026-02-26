package com.jtk.android.taekyontraining.protocol

/**
 * String constants for Android → Unity messages. See Docs/MESSAGE_PROTOCOL.md.
 */
object AndroidToUnityMessages {
    /** Prefix for load-motion command; append path. */
    const val LOAD_MOTION = "LOAD_MOTION:"

    const val START_SESSION = "START_SESSION"

    const val PAUSE_SESSION = "PAUSE_SESSION"

    const val RESUME_SESSION = "RESUME_SESSION"

    const val STOP_SESSION = "STOP_SESSION"

    const val SET_HOLD_PREFIX = "SET_HOLD:"
    const val RELEASE_HOLD = "RELEASE_HOLD"
}
