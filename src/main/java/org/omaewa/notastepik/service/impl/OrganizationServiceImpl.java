package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Organization;
import org.omaewa.notastepik.repository.OrganizationRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.OrganizationService;
import org.omaewa.notastepik.service.api.util.PasswordEmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OrganizationServiceImpl extends AbstractService<Long, Organization, OrganizationRepository> implements OrganizationService {
    private final AnnouncementService announcementService;

    public OrganizationServiceImpl(
            final OrganizationRepository repository,
            final AnnouncementService announcementService
    ) {
        super(repository);
        this.announcementService = announcementService;
    }

    @Override
    public boolean isValid(final Organization organization) {
        return organizationHasValidFields(organization)
                && organizationHasUniqueName(organization);
    }

    @Override
    public void delete(final Long id) {
        repository.findById(id).ifPresent(organization ->
                organization.getAnnouncements().forEach(announcement ->
                        announcementService.delete(announcement.getId())
                )
        );
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
