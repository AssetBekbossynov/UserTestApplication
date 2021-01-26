package com.testapp.testusernameapplication.ui.registration

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.testapp.testusernameapplication.R
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationBottomSheetDialogFragment: BottomSheetDialogFragment() {

    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateTestData()

        registerBtn.setOnClickListener {
            if (fieldsNotEmpty()) {
                if (checkEmail(email.text.toString())) {
                    if (checkPhone(phone.text.toString())) {
                        if (checkStatus(status.text.toString().toInt())) {
                            viewModel.registerUser(username.text.toString(), firstName.text.toString(),
                                secondName.text.toString(), email.text.toString(), password.text.toString(),
                                phone.text.toString(), status.text.toString().toInt())
                        } else {
                            Toast.makeText(context, "Incorrect status", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Incorrect phone", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Incorrect email", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun fieldsNotEmpty() = username.text.isNotEmpty() && firstName.text.isNotEmpty() &&
            secondName.text.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty() &&
            phone.text.isNotEmpty() && status.text.isNotEmpty()

    private fun checkStatus(status: Int) = status in 1..5

    private fun checkEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun checkPhone(phone: String) = phone.length in 11..16

    private fun generateTestData() {
        username.setText("Username")
        firstName.setText("Firstname")
        secondName.setText("SecondName")
        email.setText("email@test.com")
        password.setText("password")
        phone.setText("87073079068")
        status.setText("2")
    }

    companion object {
        fun newInstance() = RegistrationBottomSheetDialogFragment()
    }
}