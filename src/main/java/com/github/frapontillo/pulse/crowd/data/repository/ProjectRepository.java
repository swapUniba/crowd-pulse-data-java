package com.github.frapontillo.pulse.crowd.data.repository;

import com.github.frapontillo.pulse.crowd.data.entity.Project;
import org.bson.types.ObjectId;

/**
 * {@link Repository} for {@link Project}s.
 *
 * @author Francesco Pontillo
 */
public class ProjectRepository extends Repository<Project, ObjectId> {
    @Override public String getCollectionName() {
        return "Project";
    }

    @Override public Class<Project> getMappedClass() {
        return Project.class;
    }
}
