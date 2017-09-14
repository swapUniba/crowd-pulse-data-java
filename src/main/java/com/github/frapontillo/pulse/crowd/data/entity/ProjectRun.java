package com.github.frapontillo.pulse.crowd.data.entity;

import java.util.Date;

/**
 * Holds a specific run of a {@link Project}.
 *
 * @author Francesco Pontillo
 */
public class ProjectRun extends Entity {
    private Date dateStart;
    private Date dateEnd;
    private String log;
    private Integer status;
    private Integer pid;

    /**
     * Get the {@link Date} this run was started.
     *
     * @return A {@link Date}.
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Set the {@link Date} this run has started.
     *
     * @param dateStart A {@link Date}.
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Get the {@link Date} this run ended.
     *
     * @return A {@link Date}.
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Set the {@link Date} this run has ended.
     *
     * @param dateEnd A {@link Date}.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Get the log information of the run. According to the specific implementation this may
     * contain
     * the full log as text or the path where the log file can be found.
     *
     * @return A {@link String} representing the log for the run.
     */
    public String getLog() {
        return log;
    }

    /**
     * Set the log for the run. According to the specific implementation this may contain the full
     * log as text or the path where the log file can be found.
     *
     * @param log A {@link String} representing the log for the run.
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * Get the return status of this {@link Project} run.
     * If {@code null}, the run hasn't completed yet.
     *
     * @return The exit code for the specific run.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Set the exit code for this specific {@link Project} run.
     *
     * @param status The exit code for the run.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Get the process ID on the machine where the process was started.
     *
     * @return A {@link Integer} process ID (PID):
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * Set the process ID that a started process has on the machine where it runs.
     *
     * @param pid The {@link Integer} PID.
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
