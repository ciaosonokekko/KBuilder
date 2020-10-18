package it.ciaosonokekko.formbuilder.form

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.form.holder.FormLinearSelectHolder
import it.ciaosonokekko.formbuilder.form.holder.FormSelectHolder
import java.util.*


enum class FormTextType {
    Text,
    Numeric,
    Range
}

sealed class Form(val id: String = UUID.randomUUID().toString(), var value: String? = null) {

    class Section(val title: String) : Form()

    class Text(
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val hint: String? = null,
        val mandatory: Boolean? = false,
        val editable: Boolean? = true,
        val numberDecimal: Boolean? = false,
        val type: FormTextType? = FormTextType.Text,
        val onValueUpdate: (String) -> Unit = {}
    ) : Form(value = value)

    class Switch(
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val mandatory: Boolean? = false,
        val onValueUpdate: (Boolean) -> Unit
    ) : Form(value = value)

    class Select(
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val values: List<String>? = null,
        val mandatory: Boolean? = false,
        val onValueUpdate: (Select, FormSelectHolder, Int, String) -> Unit = { _, _, _, _ -> },
        val onClickView: (Select, FormSelectHolder) -> Unit = { _, _ -> }
    ) : Form(value = value)

    class LinearSelect(
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val values: List<String>? = null,
        val mandatory: Boolean? = false,
        val onValueUpdate: (LinearSelect, FormLinearSelectHolder, Int, String) -> Unit = {_, _, _, _ -> },
        val onClickView: (LinearSelect, FormLinearSelectHolder) -> Unit = { _, _ -> }
    ) : Form(value = value)
}

interface FormViewBase

open class FormViewBaseHolder(view: View): RecyclerView.ViewHolder(view) {
    open fun updateValue(value: String) {}
}