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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.fine_service.domain.FinePaymentEntity;
import org.vedanta.vidiyalay.fine_service.domain.FineRecordEntity;
import org.vedanta.vidiyalay.fine_service.domain.enums.Status;
import org.vedanta.vidiyalay.fine_service.repository.FinePaymentEntityRepository;
import org.vedanta.vidiyalay.fine_service.service.CreateUpdateFineRecordService;
import org.vedanta.vidiyalay.fine_service.service.DepositFineService;
import org.vedanta.vidiyalay.fine_service.service.QueryFineRecordService;
import org.vedanta.vidiyalay.utils.CloneObjects;

@Component
@Slf4j
public class DepositFineServiceImpl implements DepositFineService {
    private final QueryFineRecordService queryFineRecordService;
    private final CreateUpdateFineRecordService createUpdateFineRecordService;
    private final FinePaymentEntityRepository finePaymentEntityRepository;

    DepositFineServiceImpl(QueryFineRecordService queryFineRecordService, CreateUpdateFineRecordService createUpdateFineRecordService, FinePaymentEntityRepository finePaymentEntityRepository) {
        this.queryFineRecordService = queryFineRecordService;
        this.createUpdateFineRecordService = createUpdateFineRecordService;
        this.finePaymentEntityRepository = finePaymentEntityRepository;
    }

    @Override
    public FinePaymentEntity depositFine(FinePaymentEntity finePaymentEntity){
        Assert.notNull(finePaymentEntity.getFineId(), "Fine id is null, cannot deposit fine.");

        FineRecordEntity fineRecordEntity = queryFineRecordService.findById(finePaymentEntity.getFineId())
                .orElseThrow(() -> new IllegalArgumentException("Fine record not found for id: " + finePaymentEntity));

        log.info("Update Fine record: {}", CloneObjects.clone(fineRecordEntity, fineRecordEntity.getClass(),
                new CloneObjects.ParameterMap()
                        .put("status", Status.PAID).build())
                .map(createUpdateFineRecordService::updateFineRecord));

        // save transaction details
        return finePaymentEntityRepository.save(finePaymentEntity);
    }
}

