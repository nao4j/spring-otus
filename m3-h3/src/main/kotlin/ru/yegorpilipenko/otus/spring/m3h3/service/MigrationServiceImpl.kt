package ru.yegorpilipenko.otus.spring.m3h3.service

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.stereotype.Service

@Service
class MigrationServiceImpl(val jobLauncher: JobLauncher, val migrateToDbJob: Job): MigrationService {

    override fun migrateToDb() {
        jobLauncher.run(migrateToDbJob, JobParameters())
    }

}
