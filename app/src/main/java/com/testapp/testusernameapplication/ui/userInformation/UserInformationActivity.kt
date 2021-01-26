package com.testapp.testusernameapplication.ui.userInformation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.testapp.testusernameapplication.R
import kotlinx.android.synthetic.main.activity_user_information.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInformationActivity : AppCompatActivity() {

    private val viewModel: UserInformationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        findUser.setOnClickListener {
            if (username.text.isNotEmpty())
                viewModel.getUserFromRemote(username.text.toString())
            else
                Toast.makeText(this, "Enter username", Toast.LENGTH_LONG).show()
        }

        viewModel.userInfo.observe(this, Observer {
            userInfo.text = it.toString()
        })
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, UserInformationActivity::class.java)
    }
}