package it.ciaosonokekko.formbuilder.form.holder

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.View.FOCUS_FORWARD
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.R
import it.ciaosonokekko.formbuilder.databinding.ViewFormTextBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormTextType

class FormTextHolder(_context: Context, _view: ViewFormTextBinding) : RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormTextBinding = _view
    private var context: Context = _context

    fun bind(data: Form.Text, onValueUpdate: (String) -> Unit) {
        setup(data, onValueUpdate)
    }

    fun checkMandatory(data: Form.Text) {
        if (!data.value.isNullOrEmpty() || data.mandatory == false) {
            view.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText))
        } else {
            view.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
        }
    }

    @Suppress("UNUSED_EXPRESSION")
    private fun setup(data: Form.Text, onValueUpdate: (String) -> Unit) {
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

        checkMandatory(data)

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

            FormTextType.TextArea -> {
                view.txtText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                view.txtText.setText(data.value)
                view.txtText.setLines(3)
            }

            FormTextType.Numeric -> {
                view.txtText.inputType = InputType.TYPE_CLASS_NUMBER
                if (data.numberDecimal == true) {
                    view.txtText.inputType =
                        view.txtText.inputType or InputType.TYPE_NUMBER_FLAG_DECIMAL
                }
                data.value?.let {
                    if (data.numberDecimal == true) {
                        view.txtText.setText(it.toFloat().toString())
                    } else {
                        view.txtText.setText(it.toInt().toString())
                    }
                }
                if(data.emptyWithZero == true && data.value?.toInt() == 0) {
                    view.txtText.setText("")
                }
            }
        }

        view.txtText.isEnabled = data.editable == true
        if (data.editable == true) {
            view.txtText.addTextChangedListener(textWatcher)
            view.txtText.setOnEditorActionListener { textView, i, _ ->
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    val view = textView.focusSearch(FOCUS_FORWARD)
                    if (view != null) {
                        if (!view.requestFocus(FOCUS_FORWARD)) {
                            true
                        }
                    }
                    false
                }
                false
            }
        } else {
            view.txtText.removeTextChangedListener(textWatcher)
        }
    }
}