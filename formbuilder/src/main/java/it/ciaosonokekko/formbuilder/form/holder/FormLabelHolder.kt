package it.ciaosonokekko.formbuilder.form.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormLabelBinding
import it.ciaosonokekko.formbuilder.form.Form

class FormLabelHolder(_view: ViewFormLabelBinding) : RecyclerView.ViewHolder(_view.root) {
    private var view: ViewFormLabelBinding = _view

    fun bind(data: Form.Label) {
        setup(data)
    }

    fun setup(data: Form.Label) {
        view.txtTitle.text = data.title

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }
    }
}