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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vedanta.vidiyalay.fine_service.facade.DepositFineFacade;
import org.vedanta.vidiyalay.fine_service.web.rest.vm.FinePaymentVM;
import org.vedanta.vidiyalay.utils.BasicResponse;
import org.vedanta.vidiyalay.utils.ResponseUtils;

import javax.validation.constraints.NotNull;

//1. update fine status in student master
//2. update fine status in fine record
//3. update fine deposit record
//      - fineId
//      - id
//      - amount

@RestController
@RequestMapping("/api/fine/deposit")
public class DepositFineResource {

    private final DepositFineFacade depositFineFacade;

    public DepositFineResource(DepositFineFacade depositFineFacade) {
        this.depositFineFacade = depositFineFacade;
    }

    @PostMapping
    public ResponseEntity<BasicResponse> depositFine(@RequestBody @NotNull final FinePaymentVM finePaymentVM) {
        return depositFineFacade.depositFine(finePaymentVM)
                .map(ResponseUtils::commonResponse)
                .orElseThrow(()->new IllegalArgumentException("Cannot deposit fine now, try again latter"));
    }
}
