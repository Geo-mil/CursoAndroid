package progressbar

import android.view.View
import android.widget.ProgressBar

fun progressBarStatus(progressBar: ProgressBar, show: Boolean) {
    //Mostrar Barra proceso
    if (show) {
        progressBar.visibility = View.VISIBLE
    } else {
        progressBar.visibility = View.INVISIBLE
    }
}