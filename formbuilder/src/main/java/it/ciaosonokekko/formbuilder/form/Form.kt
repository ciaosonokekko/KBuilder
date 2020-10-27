package it.ciaosonokekko.formbuilder.form

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.R
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

sealed class Form(
    var id: String = UUID.randomUUID().toString(),
    val title: String? = null,
    val subTitle: String? = null,
    var value: String? = null,
    val mandatory: Boolean? = false,
    val editable: Boolean? = true
) {

    class Section(
        id: String? = null,
        title: String,
        subTitle: String? = null,
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle
    )

    class Label(
        id: String? = null,
        title: String,
        subTitle: String? = null,
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle
    )

    class Text(
        id: String? = null,
        title: String,
        subTitle: String? = null,
        value: String? = null,
        val hint: String? = null,
        mandatory: Boolean? = false,
        editable: Boolean? = true,
        val emptyWithZero: Boolean? = false,
        val numberDecimal: Boolean? = false,
        val type: FormTextType? = FormTextType.Text,
        val onValueUpdate: (String) -> Unit = {}
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = value,
        mandatory = mandatory,
        editable = editable
    )

    class Switch(
        id: String? = null,
        title: String,
        subTitle: String? = null,
        value: String? = null,
        mandatory: Boolean? = false,
        editable: Boolean? = true,
        val onValueUpdate: (Boolean) -> Unit
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = value,
        mandatory = mandatory,
        editable = editable
    )

    class Select(
        id: String? = null,
        title: String,
        subTitle: String? = null,
        value: String? = null,
        val values: List<String>? = null,
        mandatory: Boolean? = false,
        editable: Boolean? = true,
        val multiSelect: Boolean? = false,
        var selectedValues: List<String>? = null,
        val onValueUpdate: (Select, FormSelectHolder, Int, String) -> Unit = { _, _, _, _ -> },
        val onValuesUpdate: (Select, FormSelectHolder, List<String>) -> Unit = { _, _, _ -> },
        val onClickView: (Select, FormSelectHolder) -> Unit = { _, _ -> }
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = value,
        mandatory = mandatory,
        editable = editable
    )

    class LinearSelect(
        id: String? = null,
        title: String,
        subTitle: String? = null,
        value: String? = null,
        val values: List<String>? = null,
        mandatory: Boolean? = false,
        editable: Boolean? = true,
        val multiSelect: Boolean? = false,
        val selectedValues: List<String>? = null,
        val onValueUpdate: (LinearSelect, FormLinearSelectHolder, Int, String) -> Unit = { _, _, _, _ -> },
        val onValuesUpdate: (LinearSelect, FormLinearSelectHolder, List<String>) -> Unit = { _, _, _ -> },
        val onClickView: (LinearSelect, FormLinearSelectHolder) -> Unit = { _, _ -> }
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = value,
        mandatory = mandatory,
        editable = editable
    )

    class Button(   // TODO: manage values for actionSheet
        id: String? = null,
        title: String? = null,  // TODO: manage title
        subTitle: String? = null,
        value: String? = null,
        editable: Boolean? = true,
        val hint: String? = null,
        val onClickView: (Button, FormButtonHolder) -> Unit = { _, _ -> }
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = value,
        editable = editable
    )

    class DateTimeButton(
        id: String? = null,
        title: String? = null,  // TODO: manage title
        subTitle: String? = null,   // TODO: manage subTitle
        var dateValue: Date? = null,
        val hint: String? = null,
        mandatory: Boolean? = false,
        editable: Boolean? = true,
        val type: FormDateTimeButtonType? = FormDateTimeButtonType.DateTime,
        val onClickView: (DateTimeButton, FormDateTimeButtonHolder) -> Unit = { _, _ -> }
    ) : Form(
        id = id ?: UUID.randomUUID().toString(),
        title = title,
        subTitle = subTitle,
        value = dateValue?.toHumanFullFormat(),
        mandatory = mandatory,
        editable = editable
    )

    fun isValid(): Boolean {
        return (!value.isNullOrEmpty() || mandatory == false)
    }
}

interface FormViewBase

//open class FormViewBaseHolder(view: View) : RecyclerView.ViewHolder(view) {
//    open fun updateValue(value: String) {}
//}