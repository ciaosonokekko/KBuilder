package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.R
import it.ciaosonokekko.formbuilder.databinding.ViewFormLinearSelectBinding
import it.ciaosonokekko.formbuilder.extension.getActivity
import it.ciaosonokekko.formbuilder.extension.requestFocusAndHideKeyboard
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.view.SheetDialogCallBack
import it.ciaosonokekko.formbuilder.form.view.ViewSheetDialog

class FormLinearSelectHolder(_context: Context, _view: ViewFormLinearSelectBinding) :
    RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormLinearSelectBinding = _view
    private var context: Context = _context

    fun bind(data: Form.LinearSelect) {
        setup(data)
    }

    fun updateValue(value: String) {
        view.txtSelect.text = value
    }

    fun checkMandatory(data: Form.LinearSelect) {
        if (!data.value.isNullOrEmpty() || data.mandatory == false) {
            view.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText))
        } else {
            view.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
        }
    }

    fun updateValues(values: List<String>) {
        var value = ""
        values.forEach { value += "$it, " }
        view.txtSelect.text = value
    }

    fun setup(data: Form.LinearSelect) {
        view.txtTitle.text = data.title

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }

        if (data.multiSelect == true) {
            updateValues(data.selectedValues ?: listOf())
        } else {
            updateValue(data.value ?: "")
        }

        checkMandatory(data)

        val thisView = this
        view.root.setOnClickListener {
            context.requestFocusAndHideKeyboard(view.root)
            data.values?.let { list ->
                context.getActivity()?.supportFragmentManager?.let { supportFragmentManager ->
                    ViewSheetDialog.newInstance(data.title, list, data.selectedValues, data.multiSelect, data.mandatory, object : SheetDialogCallBack{
                        override fun itemOnClick(position: Int, value: String) {
                            updateValue(value)
                            data.onValueUpdate(data, thisView, position, value)
                        }

                        override fun itemsOnClick(values: List<String>) {
                            updateValues(values)
                            data.onValuesUpdate(data, thisView, values)
                        }
                    }).apply {
                        show(supportFragmentManager, tag)
                    }
                }
            } ?: run {
                data.onClickView(data, thisView)
            }
        }
    }
}