package com.example.opensource_blog.domain.hashtag;

import java.util.Arrays;

public enum HashTagType {
    AGE("이용자층"),CATEGORY("카테고리"),VISIT_PURPOSE("방문목적")
    ,UTILITY("편의기능"),REGION("지역");

    private final String hashTageTypeName;

    HashTagType(String hashTageTypeName) {
        this.hashTageTypeName = hashTageTypeName;
    }

    public static HashTagType fromHashTageTypeName(String hashTageTypeName) {
        return Arrays.stream(HashTagType.values()).filter(hashTagType ->
            hashTagType.getHashTageTypeName().equals(hashTageTypeName)
        ).findAny().orElseThrow(()-> new IllegalArgumentException("Unknown value: "+hashTageTypeName));
    }

    public String getHashTageTypeName() {
        return hashTageTypeName;
    }
}
