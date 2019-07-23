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

package org.vedanta.vidiyalay.fine_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vedanta.vidiyalay.fine_service.domain.FineRecordEntity;

import java.util.List;

public interface FineRecordEntityRepository extends CrudRepository<FineRecordEntity, Long> {
    List<FineRecordEntity> findByEnrolmentNo(final long enrolmentNo);
    List<FineRecordEntity> findByFineType(final String fineType);
    List<FineRecordEntity> findAll();
    @Query(value = "select * from fine_record where (:enrolmentNo is null or enrolment_no=:enrolmentNo)" +
            " and (:fineType is null or fine_type=:fineType) order by amount desc", nativeQuery = true)
    List<FineRecordEntity> FindByEnrolmentNoAndId(@Param(value = "enrolmentNo") Long enrolmentNo,
                                                  @Param(value = "fineType") String fineType);
}
