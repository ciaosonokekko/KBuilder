package it.ciaosonokekko.formbuilder.form.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormSectionBinding
import it.ciaosonokekko.formbuilder.form.Form

class FormSectionHolder(_view: ViewFormSectionBinding) : RecyclerView.ViewHolder(_view.root) {
    private var view: ViewFormSectionBinding = _view
    fun bind(data: Form.Section) {
        setup(data)
    }

    fun setup(data: Form.Section) {
        view.txtTitle.text = data.title

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }
    }
}