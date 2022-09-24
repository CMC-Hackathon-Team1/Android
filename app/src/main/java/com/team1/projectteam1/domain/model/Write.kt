package com.team1.projectteam1.domain.model

data class Write(
    var date : String = "",
    var category : String = "",
    var like : Int = 0,
    var img : Int? = null,
    var text : String = ""
)
