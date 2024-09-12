package com.soarespt.memberofparliament

data class ParliamentMember(
       val hetekaId: Int,
       val seatNumber: Int,
       val lastname: String,
       val firstname: String,
       val party: String,
       val minister: Boolean,
       val pictureUrl: String
)