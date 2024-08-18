package com.example.a04_deber02

import android.os.Parcel
import android.os.Parcelable

class CareerEntity(
    var id: Int,
    var nameCareer: String,
    var description: String,
    var university_id: Int
) : Parcelable {
    val name: CharSequence?
        get() {
            TODO()
        }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nameCareer)
        parcel.writeString(description)
        parcel.writeInt(university_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CareerEntity> {
        override fun createFromParcel(parcel: Parcel): CareerEntity {
            return CareerEntity(parcel)
        }

        override fun newArray(size: Int): Array<CareerEntity?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "$id - $nameCareer"
    }
}
