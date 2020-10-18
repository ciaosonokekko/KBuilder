package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.view.View
import it.ciaosonokekko.formbuilder.R
import it.ciaosonokekko.formbuilder.databinding.ViewFormLinearSelectBinding
import it.ciaosonokekko.formbuilder.extension.createActionSheet
import it.ciaosonokekko.formbuilder.extension.requestFocusAndHideKeyboard
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormViewBaseHolder

class FormLinearSelectHolder(_context: Context, _view: ViewFormLinearSelectBinding) :
    FormViewBaseHolder(_view.root) {

    private var view: ViewFormLinearSelectBinding = _view
    private var context: Context = _context

    fun bind(data: Form.LinearSelect) {
        setup(data)
    }

    override fun updateValue(value: String) {
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

        view.txtSelect.text = data.value

        if (!data.value.isNullOrEmpty() || data.mandatory == false) {
            view.txtTitle.setTextColor(context.getColor(R.color.colorText))
        } else {
            view.txtTitle.setTextColor(context.getColor(R.color.colorRed))
        }

        val thisView = this
        view.root.setOnClickListener {
            context.requestFocusAndHideKeyboard(view.root)
            data.values?.let { list ->
                context.createActionSheet(data.title, list.toMutableList()) { position ->
                    list.getOrNull(position)?.let {
                        data.onValueUpdate(data, thisView, position, it)
                        view.txtSelect.text = it
                    }
                }
            } ?: run {
                data.onClickView(data, thisView)
            }
        }
    }
}