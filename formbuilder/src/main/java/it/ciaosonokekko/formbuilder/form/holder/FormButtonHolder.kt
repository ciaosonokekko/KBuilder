package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormButtonBinding
import it.ciaosonokekko.formbuilder.form.Form

class FormButtonHolder(_context: Context, _view: ViewFormButtonBinding) :
    RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormButtonBinding = _view
    private var context: Context = _context

    fun bind(data: Form.Button) {
        setup(data)
    }

    fun updateValue(value: String) {
        view.btnMain.text = value
    }

    private fun setup(data: Form.Button) {

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }

        view.btnMain.isEnabled = data.editable == true

        view.btnMain.text = data.hint
        data.value?.let {
            view.btnMain.text = it
        }

        view.btnMain.setOnClickListener {
            data.onClickView(data, this)
        }
    }
}