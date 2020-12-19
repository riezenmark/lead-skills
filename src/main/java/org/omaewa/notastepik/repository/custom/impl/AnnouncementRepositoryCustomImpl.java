package org.omaewa.notastepik.repository.custom.impl;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;
import org.omaewa.notastepik.repository.custom.AnnouncementRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AnnouncementRepositoryCustomImpl implements AnnouncementRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Announcement> findAnnouncementsByParameters(
            final String q,
            final Long timeFrom, final Long timeTo,
            final AnnouncementType type
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Announcement> query = criteriaBuilder.createQuery(Announcement.class);

        Root<Announcement> announcementRoot = query.from(Announcement.class);
        List<Predicate> predicateList = new ArrayList<>();

        fillPredicateList(q, timeFrom, timeTo, type, criteriaBuilder, announcementRoot, predicateList);
        query.where(predicateList.toArray(new Predicate[0]));

        return entityManager
                .createQuery(query)
                .getResultList();
    }

    private void fillPredicateList(
            final String q,
            final Long timeFrom, final Long timeTo,
            final AnnouncementType type,
            final CriteriaBuilder criteriaBuilder,
            final Root<Announcement> announcementRoot,
            final List<Predicate> predicateList
    ) {
       if (StringUtils.hasLength(q)) {
           predicateList.add(criteriaBuilder.like(announcementRoot.get("name"), q));
       }
       if (Objects.nonNull(timeFrom)) {
           predicateList.add(criteriaBuilder.ge(announcementRoot.get("timeFrom"), timeFrom));
       }
        if (Objects.nonNull(timeTo)) {
            predicateList.add(criteriaBuilder.le(announcementRoot.get("timeTo"), timeTo));
        }
        if (Objects.nonNull(type)) {
            predicateList.add(criteriaBuilder.equal(announcementRoot.get("type"), type));
        }
    }
}
