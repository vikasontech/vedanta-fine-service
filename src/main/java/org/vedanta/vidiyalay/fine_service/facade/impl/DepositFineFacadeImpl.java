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

package org.vedanta.vidiyalay.fine_service.facade.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vedanta.vidiyalay.fine_service.facade.DepositFineFacade;
import org.vedanta.vidiyalay.fine_service.mapper.FinePaymentVmToEntityMapper;
import org.vedanta.vidiyalay.fine_service.service.DepositFineService;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FinePaymentVM;

import java.util.Optional;

@Component
public class DepositFineFacadeImpl implements DepositFineFacade {
    private final DepositFineService depositFineService;
    private final FinePaymentVmToEntityMapper mapper;

    DepositFineFacadeImpl(DepositFineService depositFineService, FinePaymentVmToEntityMapper mapper) {
        this.depositFineService = depositFineService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Optional<FinePaymentVM>  depositFine(FinePaymentVM finePaymentVM) {
        // update fine record status
         return Optional.ofNullable(depositFineService.depositFine(mapper.toEntity(finePaymentVM)))
                .map(mapper::toVm);

    }
}

