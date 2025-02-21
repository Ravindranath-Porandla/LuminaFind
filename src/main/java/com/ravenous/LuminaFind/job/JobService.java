package com.ravenous.LuminaFind.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();

    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job job);
}
