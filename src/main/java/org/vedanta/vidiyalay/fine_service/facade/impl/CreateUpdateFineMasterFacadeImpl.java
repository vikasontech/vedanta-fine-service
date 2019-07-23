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
import org.springframework.util.Assert;
import org.vedanta.vidiyalay.fine_service.facade.CreateUpdateFineMasterFacade;
import org.vedanta.vidiyalay.fine_service.mapper.FineMasterVMToFineMasterEntityMapper;
import org.vedanta.vidiyalay.fine_service.service.CreateUpdateFineMasterService;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineMasterVM;

@Component
public class CreateUpdateFineMasterFacadeImpl implements CreateUpdateFineMasterFacade {

    private final FineMasterVMToFineMasterEntityMapper mapper;
    private final CreateUpdateFineMasterService createUpdateFineMasterService;

    public CreateUpdateFineMasterFacadeImpl(FineMasterVMToFineMasterEntityMapper mapper, CreateUpdateFineMasterService createUpdateFineMasterService) {
        this.mapper = mapper;
        this.createUpdateFineMasterService = createUpdateFineMasterService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FineMasterVM createFineMaster(final FineMasterVM fineMasterVM) {

        return createUpdateFineMasterService.createFineMaster(mapper.toEntity(fineMasterVM))
                .map(mapper::toVm)
                .orElseThrow(() -> new RuntimeException("cannot create fine master"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FineMasterVM updateFineMaster(final FineMasterVM fineMasterVM) {
        Assert.isNull(fineMasterVM.getId(), "Cannot update fine master and id is blank!");
        return createUpdateFineMasterService.updateFineMaster(mapper.toEntity(fineMasterVM))
                .map(mapper::toVm)
                .orElseThrow(() -> new RuntimeException("cannot update fine master"));
    }
}
