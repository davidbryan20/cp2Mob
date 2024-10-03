package com.example.cp2_mob
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.widget.*


data class Contact(val name: String, val email: String, val phone: String)

class ActivyTwo : AppCompatActivity() {

    private val contacts = mutableListOf<Contact>()
    private lateinit var listViewContacts: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_two)

        listViewContacts = findViewById(R.id.listViewContacts)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewContacts.adapter = adapter

        // Recebendo dados da Intent (opcional)
        val name = intent.getStringExtra("name") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""

        // Adiciona o primeiro contato se não estiver vazio
        if (name.isNotEmpty() || email.isNotEmpty() || phone.isNotEmpty()) {
            addContact(name, email, phone)
        }

        // Configura o ListView para editar ou excluir contatos
        listViewContacts.setOnItemClickListener { _, _, position, _ ->
            val contact = contacts[position]
            showContactDialog(contact, position)
        }

        findViewById<Button>(R.id.btnAddContact).setOnClickListener {
            showContactDialog(null, -1)
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun addContact(name: String, email: String, phone: String) {
        contacts.add(Contact(name, email, phone))
        updateContactList()
    }

    private fun updateContactList() {
        adapter.clear()
        contacts.forEach { contact ->
            adapter.add("${contact.name} - ${contact.email} - ${contact.phone}")
        }
        adapter.notifyDataSetChanged()
    }

    private fun showContactDialog(contact: Contact?, position: Int) {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_contact, null)
        val editName = view.findViewById<EditText>(R.id.editName)
        val editEmail = view.findViewById<EditText>(R.id.editEmail)
        val editPhone = view.findViewById<EditText>(R.id.editPhone)

        if (contact != null) {
            editName.setText(contact.name)
            editEmail.setText(contact.email)
            editPhone.setText(contact.phone)
            builder.setTitle("Editar Contato")
        } else {
            builder.setTitle("Adicionar Contato")
        }

        builder.setView(view)
            .setPositiveButton("Salvar") { _, _ ->
                val name = editName.text.toString()
                val email = editEmail.text.toString()
                val phone = editPhone.text.toString()

                if (contact != null) {
                    // Atualiza o contato existente
                    contacts[position] = Contact(name, email, phone)
                } else {
                    // Adiciona um novo contato
                    addContact(name, email, phone)
                }
                updateContactList()
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}



