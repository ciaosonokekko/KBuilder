package it.ciaosonokekko.formbuilder.extensions

import android.content.Context
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
        .setColorTitleCancel(getColor(R.color.colorRed))
        .setColorTitle(getColor(R.color.colorBlue))
        .setColorData(getColor(R.color.colorText))
        .create(object : ActionSheetCallBack {
            override fun data(data: String, position: Int) {
                onClick(position)
            }
        })
}
