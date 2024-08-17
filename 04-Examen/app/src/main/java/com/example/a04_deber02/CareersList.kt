package com.example.a04_deber02

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CareersList : AppCompatActivity() {
    private var allStudents: ArrayList<CareerEntity> = arrayListOf()
    private var adapter: ArrayAdapter<CareerEntity>? = null
    private var selectedRegisterPosition = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list) // Asegúrate de que el layout siga siendo el correcto
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.view_students)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val selectedClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("selectedClass", CareerEntity::class.java)
        } else {
            intent.getParcelableExtra<CareerEntity>("selectedClass")
        }

        val studentsClass = findViewById<TextView>(R.id.txt_view_class_name)
        studentsClass.text = selectedClass!!.name

        DataBase.tables = SqliteHelper(this)
        val studentsList = findViewById<ListView>(R.id.list_students)
        allStudents = DataBase.tables.getStudentsByClass(selectedClass.id)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            allStudents
        )

        studentsList.adapter = adapter
        adapter!!.notifyDataSetChanged() // To update the UI

        val btnRedirectCreateStudent = findViewById<Button>(R.id.btn_redirect_create_student)
        btnRedirectCreateStudent.setOnClickListener {
            goToActivity(CreateUpdateStudent::class.java, null, selectedClass)
        }
        val btnBackToMain = findViewById<Button>(R.id.btn_back_to_main)
        btnBackToMain.setOnClickListener {
            goToActivity(MainActivity::class.java)
        }

        registerForContextMenu(studentsList)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // Set options for the context menu
        val inflater = menuInflater
        inflater.inflate(R.menu.student_menu, menu)

        // Get ID of the selected item of the list
        val register = menuInfo as AdapterView.AdapterContextMenuInfo
        selectedRegisterPosition = register.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("selectedClass", UniversityEntity::class.java)
        } else {
            intent.getParcelableExtra<UniversityEntity>("selectedClass")
        }

        return when (item.itemId) {
            R.id.mi_edit_student -> {
                goToActivity(
                    CreateUpdateStudent::class.java,
                    allStudents[selectedRegisterPosition],
                )
                return true
            }

            R.id.mi_delete_student -> {
                openDialog(allStudents[selectedRegisterPosition].id)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    class CreateUpdateStudent {

    }

    private fun goToActivity(
        activityClass: Class<*>,
        dataToPass: CareerEntity? = null,
        dataToPass2: CareerEntity? = null,
        create: Boolean = true
    ) {
        val intent = Intent(this, activityClass)
        if (dataToPass != null) {
            intent.apply {
                putExtra("selectedStudent", dataToPass)
            }
        }
        if (dataToPass2 != null) {
            intent.apply {
                putExtra("selectedClass", dataToPass2)
            }
        }
        intent.apply {
            putExtra("create", create)
        }
        startActivity(intent)
        finish()
    }

    private fun openDialog(registerIndex: Int) {
        val selectedClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("selectedClass", CareerEntity::class.java)
        } else {
            intent.getParcelableExtra<CareerEntity>("selectedClass")
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Está seguro de eliminar el estudiante?")
        builder.setPositiveButton(
            "Eliminar"
        ) { _, _ ->
            DataBase.tables.deleteStudent(registerIndex)
            allStudents.clear()
            allStudents.addAll(DataBase.tables.getStudentsByClass(selectedClass!!.id))
            adapter!!.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar", null)

        builder.create().show()
    }
}
