package com.ranaus.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        register.setOnClickListener {
            if (password.text.toString().equals(conf_password.text.toString()))
            {
                val signUpUrl = "http://www.ranaus.com/test/new_user.php?email="+email.text.toString() +
                        "&username=" + username.text.toString() + "&password=" + password.text.toString()

                val requestQueue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET,signUpUrl,Response.Listener { response ->

                    if (response.equals("user with this mail address already exist!!!"))
                    {
                        var dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Alert")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }
                    else
                    {
                        Person.email = email.text.toString()
                        Toast.makeText(this,"User created successfully",Toast.LENGTH_SHORT).show()

                        val homeIntent = Intent(this,HomeScreenActivity::class.java)
                        startActivity(homeIntent)
                    }
                },Response.ErrorListener { error ->
                    var dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })

                requestQueue.add(stringRequest)
            }
            else
            {
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }
        }

        register.setOnClickListener {
            finish()
        }

        login.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
