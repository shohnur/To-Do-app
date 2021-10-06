package uz.task.to_do_app

import android.graphics.Color

val priority= arrayListOf (
    "CRITIC", "IMPORTANT", "NOImportant"
)

fun getPriorityColor(p: String): Int {
    return when (p) {
        priority[0] -> Color.RED
        priority[1] -> Color.GREEN
        priority[2] -> Color.GRAY
        else -> 0
    }
}
