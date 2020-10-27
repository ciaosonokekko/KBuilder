package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormSelectBinding
import it.ciaosonokekko.formbuilder.extension.getActivity
import it.ciaosonokekko.formbuilder.extension.requestFocusAndHideKeyboard
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.view.SheetDialogCallBack
import it.ciaosonokekko.formbuilder.form.view.ViewSheetDialog

class FormSelectHolder(_context: Context, _view: ViewFormSelectBinding) :
    RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormSelectBinding = _view
    private var context: Context = _context
    private var id: String = ""

    fun bind(data: Form.Select) {
        setup(data)
    }

    fun updateValue(value: String) {
        view.txtSelect.text = value
    }

    fun updateValues(values: List<String>) {
        var value = ""
        values.forEach { value += "$it, " }
        view.txtSelect.text = value
    }

    private fun setup(data: Form.Select) {
        id = data.id
        view.txtTitle.text = data.title

        if (data.multiSelect == true) {
            updateValues(data.selectedValues ?: listOf())
        } else {
            updateValue(data.value ?: "")
        }

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }

        val thisView = this
        view.root.setOnClickListener {
            context.requestFocusAndHideKeyboard(view.root)
            data.values?.let { list ->
                context.getActivity()?.supportFragmentManager?.let { supportFragmentManager ->
                    ViewSheetDialog.newInstance(data.title, list, data.selectedValues, data.multiSelect, data.mandatory, object :
                        SheetDialogCallBack {
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