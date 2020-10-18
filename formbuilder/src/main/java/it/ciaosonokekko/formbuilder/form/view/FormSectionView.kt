package it.ciaosonokekko.formbuilder.form.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormSectionBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormViewBase

fun ViewFormSectionBinding.setup(data: Form.Section? = null) {
    data?.let {
        txtTitle.text = it.title
    }
}

class FormSectionView : ConstraintLayout, FormViewBase {

    var view: ViewFormSectionBinding? = null
    private var data: Form.Section? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, data: Form.Section) : super(context) {
        initView()
        setup(data)
    }

    private fun initView() {
        view = ViewFormSectionBinding.inflate(LayoutInflater.from(context))
        view?.root?.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        addView(view?.root)
    }

    fun setup(data: Form.Section, mainView: ViewFormSectionBinding? = view) {
        this.data = data
        mainView?.setup(data)
    }
}

class FormSectionHolder(v: ViewFormSectionBinding) : RecyclerView.ViewHolder(v.root) {
    private var view: ViewFormSectionBinding = v
    fun bind(data: Form.Section) {
        view.setup(data)
    }
}