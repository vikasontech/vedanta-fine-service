/*
 *     Copyright (C) 2019  Vikas Kumar Verma
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.vedanta.vidiyalay.fine_service.service.impl;

import org.springframework.stereotype.Component;
import org.vedanta.vidiyalay.fine_service.domain.FineRecordEntity;
import org.vedanta.vidiyalay.fine_service.repository.FineRecordEntityRepository;
import org.vedanta.vidiyalay.fine_service.service.QueryFineRecordService;

import java.util.List;
import java.util.Optional;

@Component
public class QueryFineRecordServiceImpl implements QueryFineRecordService {
    private final FineRecordEntityRepository repository;

    QueryFineRecordServiceImpl(FineRecordEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FineRecordEntity> findByEnrolmentNo(long enrolmentNo) {
        return repository.findByEnrolmentNo(enrolmentNo);
    }

    @Override
    public List<FineRecordEntity> findByType(String fineType) {
        return repository.findByFineType(fineType);
    }

    @Override
    public List<FineRecordEntity> findAll(final Long enrolmentNo, final String fineType) {
        return repository.FindByEnrolmentNoAndId(enrolmentNo, fineType);
    }

    @Override
    public Optional<FineRecordEntity> findById(Long enrolmentNo) {
        return repository.findById(enrolmentNo);
    }

}
