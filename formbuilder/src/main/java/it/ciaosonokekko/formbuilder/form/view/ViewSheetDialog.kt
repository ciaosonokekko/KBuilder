package it.ciaosonokekko.formbuilder.form.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import it.ciaosonokekko.formbuilder.R
import it.ciaosonokekko.formbuilder.databinding.ViewSheetDialogBinding
import it.ciaosonokekko.formbuilder.form.adapter.SheetAdapter


interface SheetDialogCallBack {
    fun itemOnClick(position: Int, value: String)
    fun itemsOnClick(values: List<String>)
}

class ViewSheetDialog : BottomSheetDialogFragment() {

    private var _binding: ViewSheetDialogBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var values: List<String> = listOf()
    private var selectedValues: MutableList<String> = mutableListOf()
    private var multiSelect: Boolean = false
    private var mandatory: Boolean = false
    private lateinit var callBack: SheetDialogCallBack
 
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
 
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.txtTitle.text = title
        binding.recycler.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recycler.adapter = SheetAdapter(values, selectedValues, multiSelect) { position, value ->
            if (multiSelect) {
                evaluateEnableConfirm()
            } else {
                callBack.itemOnClick(position, value)
                dismiss()
            }
        }

        binding.btnConfirm.visibility = if (multiSelect) View.VISIBLE else View.GONE
        binding.btnConfirm.setOnClickListener {
            if (multiSelect) {
                callBack.itemsOnClick(selectedValues)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        evaluateEnableConfirm()
    }

    private fun evaluateEnableConfirm() {
        if(multiSelect && mandatory) {
            binding.btnConfirm.isEnabled = selectedValues.isNotEmpty()
        } else {
            binding.btnConfirm.isEnabled = true
        }
    }

    companion object {
        fun newInstance(
            title: String? = null,
            values: List<String>? = null,
            selectedValues: List<String>? = mutableListOf(),
            multiSelect: Boolean? = false,
            mandatory: Boolean? = false,
            callBack: SheetDialogCallBack
        ): ViewSheetDialog {
            return ViewSheetDialog().apply {
                this.title = title
                this.values = values ?: listOf()
                this.selectedValues = selectedValues?.toMutableList() ?: mutableListOf()
                this.multiSelect = multiSelect ?: false
                this.mandatory = mandatory ?: false
                this.callBack = callBack
            }
        }
    }
}