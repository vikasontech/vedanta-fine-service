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

package org.vedanta.vidiyalay.fine_service.service;

import org.vedanta.vidiyalay.fine_service.domain.FineMasterEntity;

import java.util.Optional;

public interface CreateUpdateFineMasterService {

    Optional<FineMasterEntity> createFineMaster(FineMasterEntity fineMasterEntity);

    Optional<FineMasterEntity> updateFineMaster(FineMasterEntity fineMasterEntity);
}
