package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormTextBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormTextType

class FormTextHolder(_context: Context, _view: ViewFormTextBinding) : RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormTextBinding = _view
    private var context: Context = _context

    fun bind(data: Form.Text, onValueUpdate: (String) -> Unit) {
        setup(data, onValueUpdate)
    }

    fun setup(data: Form.Text, onValueUpdate: (String) -> Unit) {
        view.txtTitle.text = data.title

        data.subTitle?.let {
            view.txtSubtitle.text = data.subTitle
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }

        data.hint?.let {
            view.txtText.hint = data.hint
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(view.txtText.hasFocus()) {
                    val text = if (s.isNullOrEmpty()) "" else s.toString()
                    onValueUpdate(text)
                    data.onValueUpdate(text)
                }
            }
        }

        view.txtText.removeTextChangedListener(textWatcher)

        when (data.type) {
            FormTextType.Text -> {
                view.txtText.inputType = InputType.TYPE_CLASS_TEXT
                view.txtText.setText(data.value)
            }

            FormTextType.Numeric -> {
                view.txtText.inputType = InputType.TYPE_CLASS_NUMBER
                data.value?.let {
                    view.txtText.setText(it.toFloat().toString())
                }
            }

            FormTextType.Range -> {
                view.txtText.inputType = InputType.TYPE_CLASS_NUMBER
                data.value?.let {
                    view.txtText.setText(it.toFloat().toString())
                }
            }
        }

        view.txtText.isEnabled = data.editable == true
        if (data.editable == true) {
            view.txtText.addTextChangedListener(textWatcher)
        } else {
            view.txtText.removeTextChangedListener(textWatcher)
        }
    }
}