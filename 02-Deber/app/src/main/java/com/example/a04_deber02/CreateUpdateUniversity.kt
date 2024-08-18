package com.example.a04_deber02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Build
import android.widget.Button
import android.widget.EditText

class CreateUpdateUniversity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_update_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.view_create_update_student)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<EditText>(R.id.input_student_name)
        val location = findViewById<EditText>(R.id.input_student_description)
        val ranking = findViewById<EditText>(R.id.input_student_class_id)

        val selectedUniversity = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                intent.getParcelableExtra("selectedUniversity", UniversityEntity::class.java)
            }
            else -> {
                intent.getParcelableExtra<UniversityEntity>("selectedUniversity")
            }
        }

        val btnCreateUpdateUniversity = findViewById<Button>(R.id.btn_create_update_student)
        selectedUniversity?.let { universityEntity ->
            name.setText(universityEntity.name)
            location.setText(universityEntity.location)
            ranking.setText(universityEntity.ranking.toString())

            btnCreateUpdateUniversity.setOnClickListener {
                DataBase.tables?.updateStudent(
                    universityEntity.id,
                    name.text.toString(),
                    location.text.toString(),
                    ranking.text.toString().toInt()
                )
                goToActivity(MainActivity::class.java)
            }
        } ?: run {
            btnCreateUpdateUniversity.setOnClickListener {
                DataBase.tables?.createStudent(
                    name.text.toString(),
                    location.text.toString(),
                    ranking.text.toString().toInt()
                )
                goToActivity(MainActivity::class.java)
            }
        }
    }

    private fun goToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
