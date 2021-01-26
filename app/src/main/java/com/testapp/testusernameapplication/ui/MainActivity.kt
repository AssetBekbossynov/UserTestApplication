package com.testapp.testusernameapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testapp.testusernameapplication.R
import com.testapp.testusernameapplication.ui.registration.RegistrationBottomSheetDialogFragment
import com.testapp.testusernameapplication.ui.userInformation.UserInformationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registration.setOnClickListener {
            RegistrationBottomSheetDialogFragment.newInstance().show(supportFragmentManager, "registration")
        }

        getInfo.setOnClickListener {
            startActivity(UserInformationActivity.newInstance(this))
        }
    }
}