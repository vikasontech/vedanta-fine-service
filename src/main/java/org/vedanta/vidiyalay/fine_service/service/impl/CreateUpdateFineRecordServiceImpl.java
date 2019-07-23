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

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.fine_service.domain.FineRecordEntity;
import org.vedanta.vidiyalay.fine_service.domain.enums.Status;
import org.vedanta.vidiyalay.fine_service.repository.FineRecordEntityRepository;
import org.vedanta.vidiyalay.fine_service.service.CreateUpdateFineRecordService;
import org.vedanta.vidiyalay.utils.CloneObjects;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class CreateUpdateFineRecordServiceImpl implements CreateUpdateFineRecordService {
    private final FineRecordEntityRepository repository;

    public CreateUpdateFineRecordServiceImpl(FineRecordEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<FineRecordEntity> createFineRecord(@NotNull FineRecordEntity fineRecordEntity) {
        return CloneObjects.clone(fineRecordEntity, fineRecordEntity.getClass(),
                new CloneObjects.ParameterMap()
                    .put("status", Status.ACTIVE).build())
        .map(repository::save);
    }

    @Override
    public Optional<FineRecordEntity> updateFineRecord(@NotNull FineRecordEntity fineRecordEntity) {
        Assert.notNull(fineRecordEntity.getId(), "Cannot update as id is zero.");
        return Optional.of(repository.save(fineRecordEntity));
    }

    @Override
    public Optional<FineRecordEntity> deleteFineRecord(@NotNull Long id) {
        FineRecordEntity fineRecordEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("cannot find fine record: " + id));
        repository.delete(fineRecordEntity);
        return Optional.of(fineRecordEntity);
    }
}
