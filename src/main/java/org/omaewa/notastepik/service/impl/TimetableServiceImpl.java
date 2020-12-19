package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Timetable;
import org.omaewa.notastepik.repository.TimetableRepository;
import org.omaewa.notastepik.service.api.TimetableService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TimetableServiceImpl extends AbstractService<Long, Timetable, TimetableRepository> implements TimetableService {
    public TimetableServiceImpl(final TimetableRepository repository) {
        super(repository);
    }

    @Override
    public boolean isValid(final Timetable timetable) {
        return Objects.nonNull(timetable.getUser());
    }
}
