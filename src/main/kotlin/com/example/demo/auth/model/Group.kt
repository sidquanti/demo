package com.example.demo.auth.model

data class Group(val id: String,
                 val groups: List<Group> = emptyList()
)
