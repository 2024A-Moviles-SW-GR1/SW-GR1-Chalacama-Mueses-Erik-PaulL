package com.example.a06_examen02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.a04_deber02.CareerEntity
import com.example.a04_deber02.UniversityEntity

class SqliteHelper(
    context: Context? /* this */
) : SQLiteOpenHelper(context, "AndroidApp", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createUniversityTable = """
            CREATE TABLE UNIVERSITY(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name VARCHAR(100),
                location VARCHAR(100),
                ranking INTEGER,
                latitude VARCHAR(50),
                longitude VARCHAR(50)
            );
        """.trimIndent()

        val createCareerTable = """
            CREATE TABLE CAREER(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nameCareer VARCHAR(100),
                description VARCHAR(200),
                university_id INTEGER,
                FOREIGN KEY (university_id) REFERENCES UNIVERSITY(id) ON DELETE CASCADE
            );
        """.trimIndent()

        db?.execSQL(createUniversityTable)
        db?.execSQL(createCareerTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE UNIVERSITY ADD COLUMN latitude VARCHAR(50)")
            db?.execSQL("ALTER TABLE UNIVERSITY ADD COLUMN longitude VARCHAR(50)")
        }
    }

    fun getAllUniversities(): ArrayList<UniversityEntity> {
        val lectureDB = readableDatabase
        val queryScript = """
            SELECT * FROM UNIVERSITY
        """.trimIndent()
        val queryResult = lectureDB.rawQuery(queryScript, emptyArray())
        val response = arrayListOf<UniversityEntity>()

        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    UniversityEntity(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getInt(3),
                        queryResult.getString(4),
                        queryResult.getString(5)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()

        return response
    }

    fun getCareersByUniversity(university_id: Int): ArrayList<CareerEntity> {
        val lectureDB = readableDatabase
        val queryScript = """
            SELECT * FROM CAREER WHERE university_id=?
        """.trimIndent()
        val queryResult = lectureDB.rawQuery(queryScript, arrayOf(university_id.toString()))
        val response = arrayListOf<CareerEntity>()

        if (queryResult.moveToFirst()) {
            do {
                response.add(
                    CareerEntity(
                        queryResult.getInt(0),
                        queryResult.getString(1),
                        queryResult.getString(2),
                        queryResult.getInt(3)
                    )
                )
            } while (queryResult.moveToNext())
        }
        queryResult.close()
        lectureDB.close()

        return response
    }

    fun createUniversity(
        name: String,
        location: String,
        ranking: Int,
        latitude: String,
        longitude: String
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToStore = ContentValues().apply {
            put("name", name)
            put("location", location)
            put("ranking", ranking)
            put("latitude", latitude)
            put("longitude", longitude)
        }

        val storeResult = writeDB.insert(
            "UNIVERSITY", // Table name
            null,
            valuesToStore // Values to insert
        )
        writeDB.close()

        return storeResult.toInt() != -1
    }

    fun createCareer(
        nameCareer: String,
        description: String,
        university_id: Int
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToStore = ContentValues().apply {
            put("nameCareer", nameCareer)
            put("description", description)
            put("university_id", university_id)
        }

        val storeResult = writeDB.insert(
            "CAREER", // Table name
            null,
            valuesToStore // Values to insert
        )
        writeDB.close()

        return storeResult.toInt() != -1
    }

    fun updateUniversity(
        id: Int,
        name: String,
        location: String,
        ranking: Int,
        latitude: String,
        longitude: String
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues().apply {
            put("name", name)
            put("location", location)
            put("ranking", ranking)
            put("latitude", latitude)
            put("longitude", longitude)
        }

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "UNIVERSITY", // Table name
            valuesToUpdate,
            "id=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    fun updateCareer(
        id: Int,
        nameCareer: String,
        description: String,
        university_id: Int
    ): Boolean {
        val writeDB = writableDatabase
        val valuesToUpdate = ContentValues().apply {
            put("nameCareer", nameCareer)
            put("description", description)
            put("university_id", university_id)
        }

        val parametersUpdateQuery = arrayOf(id.toString())
        val updateResult = writeDB.update(
            "CAREER", // Table name
            valuesToUpdate,
            "id=?",
            parametersUpdateQuery
        )
        writeDB.close()

        return updateResult != -1
    }

    fun deleteUniversity(id: Int): Boolean {
        val writeDB = writableDatabase
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "UNIVERSITY",
            "id=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }

    fun deleteCareer(id: Int): Boolean {
        val writeDB = writableDatabase
        val parametersDeleteQuery = arrayOf(id.toString())
        val deleteResult = writeDB.delete(
            "CAREER",
            "id=?",
            parametersDeleteQuery
        )
        writeDB.close()

        return deleteResult != -1
    }
}
