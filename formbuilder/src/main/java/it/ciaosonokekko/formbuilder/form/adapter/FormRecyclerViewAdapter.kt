package it.ciaosonokekko.formbuilder.form.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormLinearSelectBinding
import it.ciaosonokekko.formbuilder.databinding.ViewFormSectionBinding
import it.ciaosonokekko.formbuilder.databinding.ViewFormSelectBinding
import it.ciaosonokekko.formbuilder.databinding.ViewFormTextBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.holder.FormLinearSelectHolder
import it.ciaosonokekko.formbuilder.form.holder.FormTextHolder
import it.ciaosonokekko.formbuilder.form.view.FormSectionHolder
import it.ciaosonokekko.formbuilder.form.holder.FormSelectHolder

const val ITEM_VIEW_TYPE_SECTION = 1
const val ITEM_VIEW_TYPE_TEXT = 2
const val ITEM_VIEW_TYPE_SWITCH = 3
const val ITEM_VIEW_TYPE_SELECT = 4
const val ITEM_VIEW_TYPE_LINEAR_SELECT = 5

class FormRecyclerViewAdapter(_elements: MutableList<Form>) :
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
                return FormTextHolder(inflatedView)
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

            else -> {

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
