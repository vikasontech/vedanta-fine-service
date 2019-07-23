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

package org.vedanta.vidiyalay.fine_service.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vedanta.vidiyalay.fine_service.facade.CreateUpdateFineRecordFacade;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineRecordVm;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/update-fine-record")
public class CreateUpdateFineRecordResource {
    private final CreateUpdateFineRecordFacade createUpdateFineRecordFacade;

    public CreateUpdateFineRecordResource(CreateUpdateFineRecordFacade createUpdateFineRecordFacade) {
        this.createUpdateFineRecordFacade = createUpdateFineRecordFacade;
    }

    @PostMapping("/create")
    ResponseEntity<BasicResponse> createFineRecord(final @NotNull @RequestBody FineRecordVm fineRecordVm){
        return createUpdateFineRecordFacade.createFineRecord(fineRecordVm)
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElseThrow(() -> new RuntimeException("Cannot create fine record now"));
    }


    @PutMapping("/update")
    ResponseEntity<BasicResponse> updateFineRecord(final @NotNull @RequestBody FineRecordVm fineRecordVm) {
        return createUpdateFineRecordFacade.updateFineRecord(fineRecordVm)
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElseThrow(() -> new RuntimeException("Cannot update fine record now"));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<BasicResponse> deleteFineRecord(final @NotNull @PathVariable(name = "id") Long id){
        return createUpdateFineRecordFacade.deleteFineRecord(id)
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElseThrow(() -> new RuntimeException("Cannot delete fine record now"));
    }
}
