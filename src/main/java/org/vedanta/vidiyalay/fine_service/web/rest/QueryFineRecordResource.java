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

import org.springframework.web.bind.annotation.*;
import org.vedanta.vidiyalay.fine_service.facade.QueryFineRecordFacade;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineRecordVm;
import org.vedanta.vidiyalay.utils.Utility;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/query-fine-record")
public class QueryFineRecordResource {
    private final QueryFineRecordFacade queryFineRecordFacade;

    public QueryFineRecordResource(QueryFineRecordFacade queryFineRecordFacade) {
        this.queryFineRecordFacade = queryFineRecordFacade;
    }

    @GetMapping("/enrolmentNo/{enrolmentNo}")
    List<FineRecordVm> findByEnrolmentNo(@PathVariable(value="enrolmentNo") @NotNull final Long enrolmentNo) {
        return Optional.of(queryFineRecordFacade.findByEnrolmentNo(enrolmentNo))
//                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElseThrow(() -> new RuntimeException("Fine record not found"));
    }

    @GetMapping("/fineType/{fineType}")
    List<FineRecordVm> findByType(@PathVariable(value="fineType") @NotNull final String fineType) {
        return Optional.of(queryFineRecordFacade.findByType(fineType))
                .orElseThrow(() -> new RuntimeException("Fine record not found"));
    }

    @GetMapping
    List<FineRecordVm> findAll(
            @RequestParam(name = "enrolmentNo") final Long enrolmentNo,
            @RequestParam(name = "fineType") final String fineType
    ) {
        return Optional.of(queryFineRecordFacade.findAll(enrolmentNo, Utility.emptyValueCheck(fineType)))
                .orElseThrow(()-> new RuntimeException("No data found"));
    }
}

