package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Timetable;
import org.omaewa.notastepik.repository.TimetableRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.TimetableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TimetableServiceImpl extends AbstractService<Long, Timetable, TimetableRepository> implements TimetableService {
    private final AnnouncementService announcementService;

    public TimetableServiceImpl(final TimetableRepository repository, final AnnouncementService announcementService) {
        super(repository);
        this.announcementService = announcementService;
    }

    @Override
    public boolean isValid(final Timetable timetable) {
        return Objects.nonNull(timetable.getUser());
    }

    @Override
    @Transactional
    public void deleteAllUserTimetables(final String userId) {
        Iterable<Timetable> timetablesOfUser = repository.findByUser_Id(userId);
        repository.deleteInBatch(timetablesOfUser);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        repository.findById(id).ifPresent(timetable ->
                timetable.getAnnouncements().forEach(announcement ->
                        announcementService.delete(announcement.getId())
                )
        );
        repository.deleteById(id);
    }
}
