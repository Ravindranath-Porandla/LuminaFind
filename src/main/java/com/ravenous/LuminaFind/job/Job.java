package com.ravenous.LuminaFind.job;


import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Job {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
}
