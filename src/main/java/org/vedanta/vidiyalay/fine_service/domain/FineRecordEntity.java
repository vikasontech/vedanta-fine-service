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

package org.vedanta.vidiyalay.fine_service.domain;

import lombok.*;
import org.hibernate.envers.Audited;
import org.vedanta.vidiyalay.BaseEntity;
import org.vedanta.vidiyalay.fine_service.domain.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "fine_record")
@Audited
@EqualsAndHashCode(callSuper = true)
@Getter
public class FineRecordEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long enrolmentNo;
    @NotNull
    private String fineType;
    @NotNull
    private BigDecimal amount;
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
