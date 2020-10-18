package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormSelectBinding
import it.ciaosonokekko.formbuilder.extension.createActionSheet
import it.ciaosonokekko.formbuilder.extension.requestFocusAndHideKeyboard
import it.ciaosonokekko.formbuilder.form.Form

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

    private fun setup(data: Form.Select) {
        id = data.id
        view.txtTitle.text = data.title

        data.value?.let {
            view.txtSelect.text = it
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
