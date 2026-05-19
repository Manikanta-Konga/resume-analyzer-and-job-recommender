package com.resume.Backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AdzunaResponseDto {

    private List<AdzunaJobDto> results;

    public List<AdzunaJobDto> getResults() {
        return results;
    }

    public void setResults(List<AdzunaJobDto> results) {
        this.results = results;
    }
}
