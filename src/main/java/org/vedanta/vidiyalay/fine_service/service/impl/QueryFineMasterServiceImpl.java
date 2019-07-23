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

package org.vedanta.vidiyalay.fine_service.service.impl;

import org.springframework.stereotype.Service;
import org.vedanta.vidiyalay.fine_service.domain.FineMasterEntity;
import org.vedanta.vidiyalay.fine_service.repository.FineMasterEntityRepository;
import org.vedanta.vidiyalay.fine_service.service.QueryFineMasterService;

import java.util.List;
import java.util.Optional;

@Service
public class QueryFineMasterServiceImpl implements QueryFineMasterService {
    private final FineMasterEntityRepository fineMasterEntityRepository;

    public QueryFineMasterServiceImpl(FineMasterEntityRepository fineMasterEntityRepository) {
        this.fineMasterEntityRepository = fineMasterEntityRepository;
    }

    @Override
    public List<FineMasterEntity> findAll() {
        return fineMasterEntityRepository.findAll();
    }

    @Override
    public Optional<FineMasterEntity> findOneByFineTypeAndStandard(final String fineType, final int standard) {
        return fineMasterEntityRepository.findOneByFineTypeAndStandard(fineType, standard);
    }
}
