package com.sssakib.smartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sssakib.smartapp.model.InsertModel
import com.sssakib.smartapp.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        btnSend.setOnClickListener {
            doInsert()
        }

        observeViewModel()


    }

    private fun observeViewModel() {
        accountViewModel.accountInsert_response_error.observe(this, androidx.lifecycle.Observer {
            it?.let {


                Log.e("Login-->", "Error Found")

            }
        })

        accountViewModel.accountInsert_response.observe(this, androidx.lifecycle.Observer {
            it?.let {

                if ("0".equals(it.outCode)) {

                    Log.d("Message ", it.outMessage.toString())
                    Toast.makeText(
                        this,
                        "Message : " + it.outMessage.toString(),
                        Toast.LENGTH_SHORT
                    ).show()


                }


                Log.e("Login-->", "Error Found")

            }
        })
    }

    private fun doInsert() {
        var model = InsertModel()
        model.requestCode = ("1")
        model.id = etAccountId.getText().toString().trim()
        model.amount = etSendingAmount.getText().toString().trim()
        model.remarks = etRemarks.getText().toString().trim()
        model.flag = "Pending"

        this.let { it1 -> accountViewModel.doInsert(model) }
    }
}