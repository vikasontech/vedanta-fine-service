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
import org.vedanta.vidiyalay.fine_service.facade.QueryFineMasterFacade;
import org.vedanta.vidiyalay.fine_service.mapper.FineMasterVMToFineMasterEntityMapper;
import org.vedanta.vidiyalay.fine_service.service.QueryFineMasterService;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineMasterVM;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QueryFineMasterFacadeImpl implements QueryFineMasterFacade {

     private final FineMasterVMToFineMasterEntityMapper mapper;
    private final QueryFineMasterService queryFineMasterService;

    public QueryFineMasterFacadeImpl( FineMasterVMToFineMasterEntityMapper mapper, QueryFineMasterService queryFineMasterService) {
        this.mapper = mapper;
        this.queryFineMasterService = queryFineMasterService;
    }

    @Override
    public List<FineMasterVM> findAll() {
        return queryFineMasterService.findAll()
                .stream().map(mapper::toVm)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FineMasterVM> findOneByFineTypeAndStandard(final String fineType, final int standard) {
        return queryFineMasterService.findOneByFineTypeAndStandard(fineType, standard)
                .map(e -> Optional.of(mapper.toVm(e)))
                .orElseThrow(() -> new RuntimeException("Fine master not found"));
    }
}
