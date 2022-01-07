package com.example.diyquartz.entity;

import javax.persistence.*;

@Table(name = "sys_job")
public class SysJob {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务组名
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 时间表达式
     */
    @Column(name = "job_cron")
    private String jobCron;

    /**
     * 类路径,全类型
     */
    @Column(name = "job_class_path")
    private String jobClassPath;

    /**
     * 传递map参数
     */
    @Column(name = "job_data_map")
    private String jobDataMap;

    /**
     * 状态:1启用 0停用
     */
    @Column(name = "job_status")
    private Integer jobStatus;

    /**
     * 任务功能描述
     */
    @Column(name = "job_describe")
    private String jobDescribe;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取任务名称
     *
     * @return job_name - 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取任务组名
     *
     * @return job_group - 任务组名
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 设置任务组名
     *
     * @param jobGroup 任务组名
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 获取时间表达式
     *
     * @return job_cron - 时间表达式
     */
    public String getJobCron() {
        return jobCron;
    }

    /**
     * 设置时间表达式
     *
     * @param jobCron 时间表达式
     */
    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    /**
     * 获取类路径,全类型
     *
     * @return job_class_path - 类路径,全类型
     */
    public String getJobClassPath() {
        return jobClassPath;
    }

    /**
     * 设置类路径,全类型
     *
     * @param jobClassPath 类路径,全类型
     */
    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }

    /**
     * 获取传递map参数
     *
     * @return job_data_map - 传递map参数
     */
    public String getJobDataMap() {
        return jobDataMap;
    }

    /**
     * 设置传递map参数
     *
     * @param jobDataMap 传递map参数
     */
    public void setJobDataMap(String jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    /**
     * 获取状态:1启用 0停用
     *
     * @return job_status - 状态:1启用 0停用
     */
    public Integer getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置状态:1启用 0停用
     *
     * @param jobStatus 状态:1启用 0停用
     */
    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 获取任务功能描述
     *
     * @return job_describe - 任务功能描述
     */
    public String getJobDescribe() {
        return jobDescribe;
    }

    /**
     * 设置任务功能描述
     *
     * @param jobDescribe 任务功能描述
     */
    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }
}