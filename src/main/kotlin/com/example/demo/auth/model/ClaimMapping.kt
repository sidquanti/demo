package com.example.demo.auth.model

import com.example.demo.auth.type.MappingType

class ClaimMapping {
    var claim: String = ""
    var attribute: String = ""
    var type: MappingType = MappingType.SINGLE
}
