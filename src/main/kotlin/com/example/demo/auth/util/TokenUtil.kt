package com.example.demo.auth.util

import com.auth0.jwt.interfaces.DecodedJWT
import com.example.demo.auth.model.ClaimMapping
import com.example.demo.auth.type.MappingType

class TokenUtil {

    companion object {

        const val bearer = "Bearer"
        const val empty = ""

        fun parseToken(header: String): String {
            val start = header.indexOf(bearer)
            return if (start < 0) header else header.substring(start + bearer.length + 1)
        }

        fun mappingOf(claim: String, attribute: String, type: MappingType = MappingType.SINGLE): ClaimMapping {
            val mapping = ClaimMapping()
            mapping.claim = claim
            mapping.attribute = attribute
            mapping.type = type
            return mapping
        }

        fun parseClaims(jwt: DecodedJWT, mappings: List<ClaimMapping>): Map<String, Any> {
            val claims = mutableMapOf<String, Any>()
            mappings.forEach { mapping ->
                val value = jwt.getClaim(mapping.claim)
                claims[mapping.attribute] = when {
                    mapping.claim.contains(".") -> parseNestedClaim(jwt, mapping.claim)
                    value.isNull -> empty
                    mapping.type == MappingType.LIST -> value.asList(String::class.java)
                    else -> value.asString() ?: value.asDouble() ?: value.asBoolean() ?: value.asDate()
                }
            }

            return claims
        }

        fun parseNestedClaim(jwt: DecodedJWT, key: String): Any {
            val splits = key.split(".")
            var map = jwt.getClaim(splits[0]).asMap()
            for (index in 1 until splits.size) {
                val current = map[splits[index]]
                if (current is Map<*, *>) {
                    map = (current as MutableMap<String, Any>)
                } else {
                    return current!!
                }
            }

            return empty
        }

        fun mergeMappings(configured: List<ClaimMapping>, default: List<ClaimMapping>): List<ClaimMapping> {
            val res = mutableListOf<ClaimMapping>()
            res.addAll(configured)

            val keys = configured.map { it.claim }.toHashSet()
            default.forEach { mapping ->
                if (!keys.contains(mapping.claim)) {
                    res.add(mapping)
                }
            }

            return res
        }
    }
}
