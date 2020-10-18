package it.ciaosonokekko.formbuilder.form.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import it.ciaosonokekko.formbuilder.databinding.ViewFormSwitchBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormViewBase

class FormSwitchView : ConstraintLayout, FormViewBase {

    private var data: Form.Switch? = null
    var view: ViewFormSwitchBinding? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, data: Form.Switch) : super(context) {
        initView()
        setup(data)
    }

    private fun initView() {
        view = ViewFormSwitchBinding.inflate(LayoutInflater.from(context))
        view?.root?.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//        view?.root?.clipToOutline = true

        addView(view?.root)
    }

    fun setup(data: Form.Switch) {
        this.data = data
        setup()
    }

    private fun setup() {
        data?.let { data ->
            view?.txtTitle?.text = data.title

            data.value?.let {
                view?.swSwitch?.isChecked = it.equals("true", true) // FIX THAT
            }

            data.subTitle?.let {
                view?.txtSubtitle?.text = data.subTitle
            }

            if (view?.txtSubtitle?.text.isNullOrEmpty()) {
                view?.txtSubtitle?.visibility = View.GONE
            }

            view?.swSwitch?.setOnCheckedChangeListener { _, isChecked ->
                data.onValueUpdate(isChecked)
            }
        }
    }
}