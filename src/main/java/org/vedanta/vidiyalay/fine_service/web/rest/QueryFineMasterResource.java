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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.fine_service.facade.QueryFineMasterFacade;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import java.util.Optional;

@RestController
@RequestMapping("/api/query-fee-master")
public class QueryFineMasterResource {
    private final QueryFineMasterFacade queryFineMasterFacade;

    public QueryFineMasterResource(QueryFineMasterFacade queryFineMasterFacade) {
        this.queryFineMasterFacade = queryFineMasterFacade;
    }

    @GetMapping("/findAll")
    public ResponseEntity<BasicResponse> findAll() {
        return Optional.ofNullable(queryFineMasterFacade.findAll())
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/fineType/{fineType}/standard/{standard}")
    public ResponseEntity<BasicResponse> findOneByFineTypeAndStandard(@PathVariable(name = "fineType") final String fineType,
                                                                      @PathVariable(name = "standard") final int standard) {
        return Optional.ofNullable(queryFineMasterFacade.findOneByFineTypeAndStandard(fineType, standard))
                .map(e -> ResponseEntity.ok(ResponseUtils.getBasicResponse(e, HttpStatus.OK)))
                .orElse(ResponseEntity.notFound().build());
    }
}
