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
import org.vedanta.vidiyalay.fine_service.facade.QueryFineRecordFacade;
import org.vedanta.vidiyalay.fine_service.mapper.FineRecordVmToEntityMapper;
import org.vedanta.vidiyalay.fine_service.service.QueryFineRecordService;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineRecordVm;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryFineRecordFacadeImpl implements QueryFineRecordFacade {
    private final QueryFineRecordService queryFineRecordService;
    private final FineRecordVmToEntityMapper mapper;

    public QueryFineRecordFacadeImpl(QueryFineRecordService queryFineRecordService, FineRecordVmToEntityMapper mapper) {
        this.queryFineRecordService = queryFineRecordService;
        this.mapper = mapper;
    }

    @Override
    public List<FineRecordVm> findByEnrolmentNo(final long enrolmentNo) {
        return queryFineRecordService.findByEnrolmentNo(enrolmentNo)
                .stream()
                .map(mapper::toVm)
                .collect(Collectors.toList());
    }

    @Override
    public List<FineRecordVm> findByType(final String fineType) {
        return queryFineRecordService.findByType(fineType)
                .stream()
                .map(mapper::toVm)
                .collect(Collectors.toList());
    }

    @Override
    public List<FineRecordVm> findAll(Long enrolmentNo, String fineType) {
        return queryFineRecordService.findAll(enrolmentNo, fineType)
                .stream()
                .map(mapper::toVm)
                .collect(Collectors.toList());
    }
}
