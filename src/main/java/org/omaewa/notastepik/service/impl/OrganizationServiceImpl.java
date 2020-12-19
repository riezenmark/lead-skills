package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Organization;
import org.omaewa.notastepik.repository.OrganizationRepository;
import org.omaewa.notastepik.service.api.OrganizationService;
import org.omaewa.notastepik.service.api.util.PasswordEmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OrganizationServiceImpl extends AbstractService<Long, Organization, OrganizationRepository> implements OrganizationService {

    public OrganizationServiceImpl(final OrganizationRepository repository) {
        super(repository);
    }

    @Override
    public boolean isValid(final Organization organization) {
        return organizationHasValidFields(organization)
                && organizationHasUniqueName(organization);
    }

    private boolean organizationHasValidFields(final Organization organization) {
        return organizationHasRequiredFields(organization)
                && organization.getName().length() <= 31
                && PasswordEmailValidator.passwordIsValid(organization.getPassword());
    }

    private boolean organizationHasRequiredFields(final Organization organization) {
        return StringUtils.hasLength(organization.getName())
                && StringUtils.hasLength(organization.getPassword());
    }

    private boolean organizationHasUniqueName(final Organization organization) {
        return !repository.existsByName(organization.getName());
    }
}
