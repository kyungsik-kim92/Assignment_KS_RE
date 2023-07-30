package com.example.assignment_ks_re.data.model


import com.google.gson.annotations.SerializedName

data class BookSearchData(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)