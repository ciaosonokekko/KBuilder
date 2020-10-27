package it.ciaosonokekko.formbuilder.form

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.extension.toHumanFullFormat
import it.ciaosonokekko.formbuilder.form.holder.FormButtonHolder
import it.ciaosonokekko.formbuilder.form.holder.FormDateTimeButtonHolder
import it.ciaosonokekko.formbuilder.form.holder.FormLinearSelectHolder
import it.ciaosonokekko.formbuilder.form.holder.FormSelectHolder
import java.util.*

const val FORM_ID = "FORM_ID"

enum class FormTextType {
    Text,
    Numeric,
    TextArea
}

enum class FormDateTimeButtonType {
    DateTime,
    Date,
    Time
}

sealed class Form(var id: String = UUID.randomUUID().toString(), var value: String? = null) {

    class Section(val title: String) : Form()

    class Text(
        id: String? = null,
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val hint: String? = null,
        val mandatory: Boolean? = false,
        val editable: Boolean? = true,
        val emptyWithZero: Boolean? = false,
        val numberDecimal: Boolean? = false,
        val type: FormTextType? = FormTextType.Text,
        val onValueUpdate: (String) -> Unit = {}
    ) : Form(value = value, id = id ?: UUID.randomUUID().toString())

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
        val multiSelect: Boolean? = false,
        var selectedValues: List<String>? = null,
        val onValueUpdate: (Select, FormSelectHolder, Int, String) -> Unit = { _, _, _, _ -> },
        val onValuesUpdate: (Select, FormSelectHolder, List<String>) -> Unit = {_, _, _ -> },
        val onClickView: (Select, FormSelectHolder) -> Unit = { _, _ -> }
    ) : Form(value = value)

    class LinearSelect(
        val title: String,
        val subTitle: String? = null,
        value: String? = null,
        val values: List<String>? = null,
        val mandatory: Boolean? = false,
        val multiSelect: Boolean? = false,
        val selectedValues: List<String>? = null,
        val onValueUpdate: (LinearSelect, FormLinearSelectHolder, Int, String) -> Unit = {_, _, _, _ -> },
        val onValuesUpdate: (LinearSelect, FormLinearSelectHolder, List<String>) -> Unit = {_, _, _ -> },
        val onClickView: (LinearSelect, FormLinearSelectHolder) -> Unit = { _, _ -> }
    ) : Form(value = value)

    class Button(   // TODO: manage values for actionSheet
        val title: String? = null,  // TODO: manage title
        value: String? = null,
        val hint: String? = null,
        val onClickView: (Button, FormButtonHolder) -> Unit = { _, _ -> }
    ) : Form(value = value)

    class DateTimeButton(
        val title: String? = null,  // TODO: manage title
        var dateValue: Date? = null,
        val hint: String? = null,
        val mandatory: Boolean? = false,
        val type: FormDateTimeButtonType? = FormDateTimeButtonType.DateTime,
        val onClickView: (DateTimeButton, FormDateTimeButtonHolder) -> Unit = { _, _ -> }
    ) : Form(value = dateValue?.toHumanFullFormat())

}

interface FormViewBase

open class FormViewBaseHolder(view: View): RecyclerView.ViewHolder(view) {
    open fun updateValue(value: String) {}
}