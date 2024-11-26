package com.example.opensource_blog.service.hashtag;

import com.example.opensource_blog.domain.hashtag.HashTag;
import com.example.opensource_blog.domain.hashtag.HashTagDto;
import com.example.opensource_blog.domain.hashtag.HashTagRepository;
import com.example.opensource_blog.domain.hashtag.HashTagType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class HashTageService {
    private final HashTagRepository hashTagRepository;

    public List<HashTagDto> getAllHashTag() {
        return hashTagRepository.findAll().stream()
                .map(HashTagDto::fromEntity).collect(Collectors.toList());
    }
    public HashTagDto getHashTagFromName(String hashTagName) {
        if(hashTagName.startsWith("#"))
            hashTagName = hashTagName.substring(1);
        String rawName = ageResolver(hashTagName);
        HashTag hashTag = hashTagRepository.findByName(rawName).orElseThrow(() -> new IllegalArgumentException("hashtag not found"));
        return HashTagDto.fromEntity(hashTag);
    }
    private String ageResolver(String hashTagName) {
        if(hashTagName.contains("10대")||hashTagName.contains("20대"))
            return "10대-20대";
        else if(hashTagName.contains("30대")||hashTagName.contains("40대"))
            return "30대-40대";
        else if(hashTagName.contains("50대")||hashTagName.contains("60대"))
            return "50대-60대";
        return hashTagName;
    }
}
