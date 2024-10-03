package com.example.cp2_mob

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.etName).text.toString()
            val email = view.findViewById<EditText>(R.id.etEmail).text.toString()
            val phone = view.findViewById<EditText>(R.id.etPhone).text.toString()


            val intent = Intent(requireActivity(), ActivyTwo::class.java)
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("phone", phone)
            startActivity(intent)
        }

        return view
    }
}
