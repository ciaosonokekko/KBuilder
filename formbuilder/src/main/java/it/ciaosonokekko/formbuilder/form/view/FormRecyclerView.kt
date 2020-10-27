package it.ciaosonokekko.formbuilder.form.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewRecyclerFormBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.adapter.FormRecyclerViewAdapter

class FormRecyclerView : ConstraintLayout {

    private var view: ViewRecyclerFormBinding? = null
    private var adapter: FormRecyclerViewAdapter? = null
    private var elements = mutableListOf<Form>()

    private var setupped: Boolean = false

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        view = ViewRecyclerFormBinding.inflate(LayoutInflater.from(context))
        view?.root?.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        addView(view?.root)
    }

    fun setup(_elements: MutableList<Form>? = null, layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)) {
        _elements?.let {
            elements = it
        }
        view?.container?.layoutManager = layoutManager
        adapter = FormRecyclerViewAdapter(elements)
        view?.container?.adapter = adapter
        setupped = true
    }

    private fun checkSetup() {
        if(!setupped) {
            setup()
            setupped = true
        }
    }

    fun addElement(element: Form) {
        checkSetup()
        adapter?.addElement(element)
    }

    fun addElements(elements: MutableList<Form>) {
        checkSetup()
        elements.forEach {
            adapter?.addElement(it)
        }
    }

    fun addElements(vararg elements: Form) {
        checkSetup()
        elements.forEach {
            adapter?.addElement(it)
        }
    }

    fun updateElementAt(element: Form, position: Int) {
        elements[position] = element
        adapter?.updateElementAt(element, position)
    }

    fun updateElement(element: Form) {
        val position = positionFromId(element.id)
        if (position > -1) {
            elements[position] = element
            adapter?.updateElementAt(element, position)
        } else {
            addElement(element)
        }
    }

    fun updateElements(_elements: MutableList<Form>) {
        elements = _elements
        adapter?.updateElements(elements)
    }

    fun updateElementValueFromId(value: String? = null, id: String) {
        val position = positionFromId(id)
        if (position > -1) {
            elements[position].value = value
            adapter?.updateElementAt(elements[position], position)
        }
    }

    private fun positionFromId(id: String) : Int {
        elements.forEachIndexed { position, form ->
            if (form.id.equals(id, true)) {
                return position
            }
        }

        return -1
    }
}