package it.ciaosonokekko.formbuilder.template

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.ciaosonokekko.formbuilder.databinding.FragmentFormBuilderBinding
import it.ciaosonokekko.formbuilder.form.Form

open class FormBuilderFragment : Fragment() {

    var _binding: FragmentFormBuilderBinding? = null
    val binding get() = _binding!!

    open var elements: MutableList<Form> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBuilderBinding.inflate(inflater, container, false)
        setupForm()
        return binding.root
    }

    open fun setupForm() {
        binding.mainLayout.setup(elements)
        // nothing
    }

    companion object {
        fun newInstance(elements: MutableList<Form>): FormBuilderFragment {
            return FormBuilderFragment().apply {
                this.elements = elements
            }
        }
    }

    fun addElement(element: Form) {
        binding.mainLayout.addElement(element)
    }

    fun addElements(elements: MutableList<Form>) {
        elements.forEach {
            binding.mainLayout.addElement(it)
        }
    }

    fun addElements(vararg elements: Form) {
        elements.forEach {
            binding.mainLayout.addElement(it)
        }
    }

}