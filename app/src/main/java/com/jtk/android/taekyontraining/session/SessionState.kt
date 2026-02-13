package com.jtk.android.taekyontraining.session

/**
 * Session lifecycle states. See Docs/SESSION_LIFECYCLE.md.
 * Android owns transitions; this type is pure state representation only.
 */
enum class SessionState {
    /** No training session active. */
    IDLE,

    /** Drill selected, motion data resolved; session about to be launched. */
    PREPARE_SESSION,

    /** Runtime initialized, waiting for explicit start command. */
    UNITY_LOADED,

    /** Training session active; motion and timing logic executing. */
    SESSION_RUNNING,

    /** Session completed or aborted; preparing to unload. */
    SESSION_END,

    /** Control returned to Android UI. */
    RETURN_TO_ANDROID
}
