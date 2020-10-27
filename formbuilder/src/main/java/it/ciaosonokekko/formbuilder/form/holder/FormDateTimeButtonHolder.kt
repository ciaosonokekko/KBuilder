package it.ciaosonokekko.formbuilder.form.holder

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.ciaosonokekko.formbuilder.databinding.ViewFormButtonBinding
import it.ciaosonokekko.formbuilder.extension.*
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormDateTimeButtonType
import java.util.*

class FormDateTimeButtonHolder(_context: Context, _view: ViewFormButtonBinding) :
    RecyclerView.ViewHolder(_view.root) {

    private var view: ViewFormButtonBinding = _view
    private var context: Context = _context

    fun bind(data: Form.DateTimeButton) {
        setup(data)
    }

    fun updateValue(value: String) {
        view.btnMain.text = value
    }

    private fun setup(data: Form.DateTimeButton) {

        data.subTitle?.let {
            view.txtSubtitle.text = it
        }

        if (view.txtSubtitle.text.isNullOrEmpty()) {
            view.txtSubtitle.visibility = View.GONE
        }

        view.btnMain.isEnabled = data.editable == true

        view.btnMain.text = data.hint
        data.dateValue?.let {

            when(data.type) {
                FormDateTimeButtonType.DateTime -> {
                    view.btnMain.text = it.toHumanFullFormat()
                }

                FormDateTimeButtonType.Time -> {
                    view.btnMain.text = it.toHumanHourFormat()
                }

                FormDateTimeButtonType.Date -> {
                    view.btnMain.text = it.toHumanDateFormat()
                }
            }
        }

        view.btnMain.setOnClickListener {

            // open date time
            val calendar = GregorianCalendar()
            calendar.time = data.dateValue ?: Date()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
            val minute: Int = calendar.get(Calendar.MINUTE)

            var datePickerDialog: AlertDialog? = null
            when(data.type) {
                FormDateTimeButtonType.DateTime -> {
                    datePickerDialog = DatePickerDialog(context, { _, newYear, monthOfYear, dayOfMonth ->

                        val newCalendar = GregorianCalendar()
                        newCalendar.set(Calendar.YEAR, newYear)
                        newCalendar.set(Calendar.MONTH, monthOfYear)
                        newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        datePickerDialog = TimePickerDialog(context, { _, newHour, newMinute ->

                            newCalendar.set(Calendar.HOUR_OF_DAY, newHour)
                            newCalendar.set(Calendar.MINUTE, newMinute)

                            data.dateValue = newCalendar.time
                            data.value = newCalendar.time.toHumanFullFormat()
                            view.btnMain.text = data.value
                            data.onClickView(data, this)

                        }, hour, minute, true)

                        datePickerDialog?.show()

                    }, year, month, day)
                }

                FormDateTimeButtonType.Date -> {
                    datePickerDialog = DatePickerDialog(context, { _, newYear, monthOfYear, dayOfMonth ->

                        val newCalendar = GregorianCalendar()
                        newCalendar.set(Calendar.YEAR, newYear)
                        newCalendar.set(Calendar.MONTH, monthOfYear)
                        newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        data.dateValue = newCalendar.time
                        data.value = newCalendar.time.toHumanFullFormat()
                        view.btnMain.text = data.dateValue?.toHumanDateFormat()
                        data.onClickView(data, this)

                    }, year, month, day)
                }

                FormDateTimeButtonType.Time -> {
                    datePickerDialog = TimePickerDialog(context, { _, newHour, newMinute ->

                        val newCalendar = GregorianCalendar()
                        newCalendar.set(Calendar.HOUR_OF_DAY, newHour)
                        newCalendar.set(Calendar.MINUTE, newMinute)

                        data.dateValue = newCalendar.time
                        data.value = newCalendar.time.toHumanFullFormat()
                        view.btnMain.text = data.dateValue?.toHumanHourFormat()
                        data.onClickView(data, this)

                    }, hour, minute, true)
                }
            }

            datePickerDialog?.show()
        }
    }
}