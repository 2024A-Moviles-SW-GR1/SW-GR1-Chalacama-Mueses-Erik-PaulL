package com.example.a04_deber02

import android.os.Parcel
import android.os.Parcelable

class UniversityEntity(
    var id: Int,
    var name: String,
    var location: String,
    var ranking: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        requireNotNull(parcel.readString()) { "Name is null" },
        requireNotNull(parcel.readString()) { "Location is null" },
        parcel.readInt().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeInt(ranking)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UniversityEntity> {
        override fun createFromParcel(parcel: Parcel): UniversityEntity {
            return UniversityEntity(parcel)
        }

        override fun newArray(size: Int): Array<UniversityEntity?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "$id - $name"
    }
}

private fun Parcel.writeInt(ranking: String) {

}
