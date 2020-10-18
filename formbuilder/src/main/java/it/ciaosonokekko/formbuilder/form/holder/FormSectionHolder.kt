package it.ciaosonokekko.formbuilder.form.holder

import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormSectionBinding
import it.ciaosonokekko.formbuilder.form.Form

class FormSectionHolder(v: ViewFormSectionBinding) : RecyclerView.ViewHolder(v.root) {
    private var view: ViewFormSectionBinding = v
    fun bind(data: Form.Section) {
        setup(data)
    }

    fun setup(data: Form.Section) {
        view.txtTitle.text = data.title
    }
}