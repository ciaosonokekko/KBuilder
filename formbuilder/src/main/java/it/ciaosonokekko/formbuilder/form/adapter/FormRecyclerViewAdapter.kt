package it.ciaosonokekko.formbuilder.form.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.*
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.holder.*

const val ITEM_VIEW_TYPE_SECTION = 1
const val ITEM_VIEW_TYPE_TEXT = 2
const val ITEM_VIEW_TYPE_SWITCH = 3
const val ITEM_VIEW_TYPE_SELECT = 4
const val ITEM_VIEW_TYPE_LINEAR_SELECT = 5
const val ITEM_VIEW_TYPE_BUTTON = 6
const val ITEM_VIEW_TYPE_DATE_TIME_BUTTON = 7
const val ITEM_VIEW_TYPE_LABEL = 8

class FormRecyclerViewAdapter(var _elements: MutableList<Form>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var elements: MutableList<Form> = _elements

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            ITEM_VIEW_TYPE_SECTION -> {
                val inflatedView = ViewFormSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FormSectionHolder(inflatedView)
            }

            ITEM_VIEW_TYPE_TEXT -> {
                val inflatedView =
                    ViewFormTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FormTextHolder(parent.context, inflatedView)
            }

            ITEM_VIEW_TYPE_LINEAR_SELECT -> {
                val inflatedView = ViewFormLinearSelectBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FormLinearSelectHolder(parent.context, inflatedView)
            }

            ITEM_VIEW_TYPE_SELECT -> {
                val inflatedView = ViewFormSelectBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FormSelectHolder(parent.context, inflatedView)
            }

            ITEM_VIEW_TYPE_BUTTON -> {
                val inflatedView = ViewFormButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FormButtonHolder(parent.context, inflatedView)
            }

            ITEM_VIEW_TYPE_DATE_TIME_BUTTON -> {
                val inflatedView = ViewFormButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FormDateTimeButtonHolder(parent.context, inflatedView)
            }
        }

        val inflatedView = ViewFormSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FormSectionHolder(inflatedView)
    }

    override fun getItemCount() = elements.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_VIEW_TYPE_SECTION -> {
                (elements[position] as? Form.Section)?.let {
                    (holder as? FormSectionHolder)?.bind(it)
                }
            }

            ITEM_VIEW_TYPE_TEXT -> {
                (elements[position] as? Form.Text)?.let { formText ->
                    (holder as? FormTextHolder)?.bind(formText) { value ->
                        formText.value = value
                    }
                }
            }

            ITEM_VIEW_TYPE_LINEAR_SELECT -> {
                (elements[position] as? Form.LinearSelect)?.let {
                    (holder as? FormLinearSelectHolder)?.bind(it)
                }
            }

            ITEM_VIEW_TYPE_SELECT -> {
                (elements[position] as? Form.Select)?.let {
                    (holder as? FormSelectHolder)?.bind(it)
                }
            }

            ITEM_VIEW_TYPE_BUTTON -> {
                (elements[position] as? Form.Button)?.let {
                    (holder as? FormButtonHolder)?.bind(it)
                }
            }

            ITEM_VIEW_TYPE_DATE_TIME_BUTTON -> {
                (elements[position] as? Form.DateTimeButton)?.let {
                    (holder as? FormDateTimeButtonHolder)?.bind(it)
                }
            }

            ITEM_VIEW_TYPE_LABEL -> {
                (elements[position] as? Form.Label)?.let {
                    (holder as? FormLabelHolder)?.bind(it)
                }
            }

            else -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (elements[position]) {
            is Form.Section -> ITEM_VIEW_TYPE_SECTION
            is Form.Text -> ITEM_VIEW_TYPE_TEXT
            is Form.Switch -> ITEM_VIEW_TYPE_SWITCH
            is Form.Select -> ITEM_VIEW_TYPE_SELECT
            is Form.LinearSelect -> ITEM_VIEW_TYPE_LINEAR_SELECT
            is Form.Button -> ITEM_VIEW_TYPE_BUTTON
            is Form.DateTimeButton -> ITEM_VIEW_TYPE_DATE_TIME_BUTTON
            is Form.Label -> ITEM_VIEW_TYPE_LABEL
        }
    }

    fun updateElements(_elements: MutableList<Form>) {
        this.elements = _elements
        notifyDataSetChanged()
    }

    fun updateElementAt(element: Form, position: Int) {
        elements[position] = element
        notifyItemChanged(position)
    }

    fun addElement(element: Form) {
        elements.add(element)
        notifyItemInserted(elements.size - 1)
    }
}
