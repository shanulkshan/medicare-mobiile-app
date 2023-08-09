package com.example.wishlist

import android.icu.text.CaseMap.Title

class DataClass {

    var dataTitle:String? =null
    var dataDosage:String? =null
    var dataDesc:String? =null
    var dataImage:String? =null

    constructor(dataTitle: String?, dataDosage:String?,dataDesc:String?,dataImage:String? ){
        this.dataTitle =dataTitle
        this.dataDosage =dataDosage
        this.dataDesc =dataDesc
        this.dataImage =dataImage
    }
}