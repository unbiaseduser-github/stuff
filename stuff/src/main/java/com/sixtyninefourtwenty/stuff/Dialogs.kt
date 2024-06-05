@file:JvmName("Dialogs")

package com.sixtyninefourtwenty.stuff

import android.app.Dialog
import android.view.WindowManager

/**
 * Make the [Dialog] wider.
 */
fun Dialog.setToFullWidth() {
    /*
     * Use WRAP_CONTENT for height since setting that to MATCH_PARENT does nothing useful
     * AND breaks non-fullscreen content
     */
    window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
}
