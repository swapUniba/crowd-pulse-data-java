package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.ProjectRun;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link ProjectRunRepository}s.
 *
 * @author Francesco Pontillo
 */
public class ProjectRunRepository extends Repository<ProjectRun, ObjectId> {
    public ProjectRunRepository(String db) {
        super(db);
    }

    @Override public String getCollectionName() {
        return "ProjectRun";
    }

    @Override public Class<ProjectRun> getMappedClass() {
        return ProjectRun.class;
    }
}
