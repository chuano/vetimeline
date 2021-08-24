package com.vetimeline.api.infrastructure.organization.persistence;

import com.vetimeline.api.domain.organization.Organization;
import com.vetimeline.api.domain.organization.OrganizationRepository;
import com.vetimeline.api.infrastructure.shared.persistence.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class OrganizationRepositoryImpl extends GenericRepositoryImpl<Organization> implements OrganizationRepository {
    public OrganizationRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Organization.class);
    }
}
