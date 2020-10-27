package it.ciaosonokekko.formbuilder.form.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ItemSheetAdapterBinding

class SheetAdapter(
    private val elements: List<String>,
    private val selectedValues: MutableList<String>,
    private val multiSelect: Boolean? = false,
    private val adapterOnClick: (Int, String) -> Unit
) : RecyclerView.Adapter<SheetAdapter.SheetHolder>() {

    var filteredElements: List<String> = elements

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetAdapter.SheetHolder {
        val inflatedView =
            ItemSheetAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SheetHolder(inflatedView)
    }

    override fun getItemCount() = filteredElements.size

    override fun onBindViewHolder(holder: SheetAdapter.SheetHolder, position: Int) {
        holder.bindContentCell(filteredElements[position])
    }

    fun filter(searchQuery: String?) {

        searchQuery?.let {
            filteredElements = elements.filter { element ->
                element.toLowerCase().contains(searchQuery, false)
            }
        } ?: run {
            filteredElements = elements
        }

        notifyDataSetChanged()
    }

    inner class SheetHolder(_view: ItemSheetAdapterBinding) : RecyclerView.ViewHolder(_view.root) {

        private var view: ItemSheetAdapterBinding = _view

        fun bindContentCell(data: String) {

            view.txtTitle.text = data

            if (multiSelect == true) {
                view.checkbox.visibility = View.VISIBLE
                view.checkbox.isChecked = selectedValues.contains(data)
                view.checkbox.setOnCheckedChangeListener { view, _ ->
                    if (view.hasFocus()) {
                        toggleData(data)
                    }
                }
            } else {
                view.checkbox.visibility = View.GONE
            }

            view.root.setOnClickListener {

                if (multiSelect == true) {
                    toggleData(data)
                    view.checkbox.isChecked = selectedValues.contains(data)
                }
                adapterOnClick(adapterPosition, data)
            }
        }
    }

    private fun toggleData(data: String) {
        if(selectedValues.contains(data)) {
            selectedValues.remove(data)
        } else {
            selectedValues.add(data)
        }
    }
}
