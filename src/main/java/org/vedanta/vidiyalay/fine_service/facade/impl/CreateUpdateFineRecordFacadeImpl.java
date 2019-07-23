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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.vedanta.vidiyalay.fine_service.StudentServiceHelper;
import org.vedanta.vidiyalay.fine_service.facade.CreateUpdateFineRecordFacade;
import org.vedanta.vidiyalay.fine_service.mapper.FineRecordVmToEntityMapper;
import org.vedanta.vidiyalay.fine_service.service.CreateUpdateFineRecordService;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineRecordVm;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.StudentNewAdmissionVM;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Component
@Slf4j
public class CreateUpdateFineRecordFacadeImpl implements CreateUpdateFineRecordFacade {
    private final CreateUpdateFineRecordService createUpdateFineRecordService;
    private final FineRecordVmToEntityMapper mapper;
    private final StudentServiceHelper studentServiceHelper;
    private final QueryFineMasterFacadeImpl queryFineMasterFacade;

    public CreateUpdateFineRecordFacadeImpl(CreateUpdateFineRecordService createUpdateFineRecordService, FineRecordVmToEntityMapper mapper, StudentServiceHelper studentServiceHelper, QueryFineMasterFacadeImpl queryFineMasterFacade) {
        this.createUpdateFineRecordService = createUpdateFineRecordService;
        this.mapper = mapper;
        this.studentServiceHelper = studentServiceHelper;
        this.queryFineMasterFacade = queryFineMasterFacade;
    }

    @Override
    public Optional<FineRecordVm> createFineRecord(@NotNull FineRecordVm fineRecordVm) {
        //        check if the student exists or not? if not exists then throw error
        final Optional<StudentNewAdmissionVM> finedStudentName = getFinedStudentName(fineRecordVm.getEnrolmentNo());
        if(!(finedStudentName.isPresent())) {
            return Optional.empty();
        }

        // get amount from fine master
        return createUpdateFineRecordService.createFineRecord(
                mapper.toEntity(getFineAmountFromMaster(fineRecordVm, finedStudentName.get())))
                .map(mapper::toVm);
    }

    @Override
    public Optional<FineRecordVm> updateFineRecord(@NotNull FineRecordVm fineRecordVm) {
        // check if the student exists or not? if not exists then throw error
        log.info("Updating student data: {}", getFinedStudentName(fineRecordVm.getEnrolmentNo()));

        // get amount from fine master
        return createUpdateFineRecordService.updateFineRecord(
                mapper.toEntity(fineRecordVm))
                .map(mapper::toVm);
    }

    @Override
    public Optional<FineRecordVm> deleteFineRecord(@NotNull Long id) {
        //        check if the student exists or not? if not exists then throw error
        log.info("Fining student name {}", getFinedStudentName(id));

        return createUpdateFineRecordService.deleteFineRecord(id)
                .map(mapper::toVm);
    }

    private FineRecordVm getFineAmountFromMaster(@NotNull FineRecordVm fineRecordVm, StudentNewAdmissionVM finedStudent) {
        BigDecimal amount = queryFineMasterFacade.findOneByFineTypeAndStandard(fineRecordVm.getFineType(), finedStudent.getAdmissionClass())
                .orElseThrow(()-> new IllegalArgumentException("No data found!")).getAmount();

        return FineRecordVm.builder()
                .amount(amount)
                .description(fineRecordVm.getDescription())
                .enrolmentNo(fineRecordVm.getEnrolmentNo())
                .fineType(fineRecordVm.getFineType())
                .id(fineRecordVm.getId())
                .status(fineRecordVm.getStatus())
                .build();
    }

    private Optional<StudentNewAdmissionVM> getFinedStudentName(@NotNull Long enrolmentNo) {

        return Optional.ofNullable(studentServiceHelper.findByEnrolmentNoAndValidateAdmissionStatus(enrolmentNo));

    }
}
