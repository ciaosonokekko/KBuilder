package it.ciaosonokekko.formbuilder.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import it.ciaosonokekko.formbuilder.app.databinding.ActivityMainBinding
import it.ciaosonokekko.formbuilder.form.Form
import it.ciaosonokekko.formbuilder.form.FormTextType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initWidget()
    }

    private fun initWidget() {

        mainLayout.setup()

        mainLayout.addElements(

                // Input
                Form.Text(
                        title = "Text Form",
                        subTitle = "Text Form SubTitle",
                        hint = "Text Form Hint",
                        mandatory = false,
                        editable = true,
                        type = FormTextType.Text,
                        value = "Text Form Value",
                        onValueUpdate = { text ->
                            Log.d(TAG, "Value updated: $text")
                        }
                ),
                Form.Text(
                        title = "Number Form",
                        subTitle = "Number Form SubTitle",
                        hint = "Number Form Hint",
                        mandatory = false,
                        editable = true,
                        type = FormTextType.Numeric,
                        value = "0",
                        onValueUpdate = { text ->
                            Log.d(TAG, "Value updated: $text")
                        }
                ),
                Form.Text(
                        title = "Decimal Form",
                        subTitle = "Decimal Form SubTitle",
                        hint = "Decimal Form Hint",
                        mandatory = false,
                        editable = true,
                        numberDecimal = true,
                        type = FormTextType.Numeric,
                        value = "0",
                        onValueUpdate = { text ->
                            Log.d(TAG, "Value updated: $text")
                        }
                ),

                // Select
                Form.Select(
                        title = "Select",
                        subTitle = "Select",
                        mandatory = false,
                        value = "Select Value",
                        onClickView = { data, _ ->
                            Log.d(TAG, "Clicked: $data")
                        },
                        onValueUpdate = { _, _, _, text ->
                            Log.d(TAG, "Value updated: $text")
                        }
                ),

                // Linear Select
                Form.LinearSelect(
                        title = "Linear Select",
                        subTitle = "Linear Select",
                        mandatory = false,
                        value = "Linear Select Value",
                        onClickView = { data, _ ->
                            Log.d(TAG, "Clicked: $data")
                        },
                        onValueUpdate = { _, _, _, text ->
                            Log.d(TAG, "Value updated: $text")
                        }
                ),
        )

    }
}
