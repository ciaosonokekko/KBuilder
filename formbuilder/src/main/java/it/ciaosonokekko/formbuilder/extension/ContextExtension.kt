package it.ciaosonokekko.formbuilder.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.khoiron.actionsheets.ActionSheet
import com.khoiron.actionsheets.callback.ActionSheetCallBack
import it.ciaosonokekko.formbuilder.R

@Synchronized
fun Context.createActionSheet(title: String, data: MutableList<String>, onClick: (Int) -> Unit) {

    ActionSheet(this, data)
        .setTitle(title)
        .setCancelTitle("Annulla")
        .setSizeTextTitle(20F)
        .setSizeTextCancel(20F)
        .setSizeTextData(16F)
        .setColorTitleCancel(ContextCompat.getColor(this, R.color.colorRed))
        .setColorTitle(ContextCompat.getColor(this, R.color.colorBlue))
        .setColorData(ContextCompat.getColor(this, R.color.colorText))
        .create(object : ActionSheetCallBack {
            override fun data(data: String, position: Int) {
                onClick(position)
            }
        })
}

fun Context.getActivity(): Activity? {
    var context: Context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return this as Activity
        }
        context = context.baseContext
    }
    return null
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.requestFocusAndHideKeyboard(view: View) {
    getActivity()?.let {
        it.currentFocus?.clearFocus()
        it.hideKeyboard(view)
    }
}
