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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.fine_service.facade.CreateUpdateFineMasterFacade;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FineMasterVM;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/fine/create-update-fine-master")
public class CreateUpdateFineMasterResource {
    private final CreateUpdateFineMasterFacade createUpdateStudentFacade;

    public CreateUpdateFineMasterResource(CreateUpdateFineMasterFacade createUpdateStudentFacade) {
        this.createUpdateStudentFacade = createUpdateStudentFacade;
    }

    @PostMapping("/new")
    public ResponseEntity<BasicResponse> createFineMater(@RequestBody  final FineMasterVM fineMasterVM) {
        return Optional.ofNullable(createUpdateStudentFacade.createFineMaster(fineMasterVM))
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElse(ResponseEntity.unprocessableEntity().build());
    }

    @PostMapping("/update")
    public ResponseEntity<BasicResponse> updateFineMater(@RequestBody @NotNull final FineMasterVM fineMasterVM) {
        return Optional.ofNullable(createUpdateStudentFacade.createFineMaster(fineMasterVM))
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElse(ResponseEntity.unprocessableEntity().build());
    }

}
