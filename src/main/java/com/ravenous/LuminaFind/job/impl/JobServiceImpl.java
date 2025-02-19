package com.ravenous.LuminaFind.job.impl;

import com.ravenous.LuminaFind.job.Job;
import com.ravenous.LuminaFind.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        for (int i = 0; i < jobs.size(); i++) {
            if (id.equals(jobs.get(i).getId())) {
                jobs.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        for (int i = 0; i < jobs.size(); i++) {
            if (id.equals(jobs.get(i).getId())) {
                jobs.set(i, job);
                return true;
            }
        }
        return false;
    }
}
