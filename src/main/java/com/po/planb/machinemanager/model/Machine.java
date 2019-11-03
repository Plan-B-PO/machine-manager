package com.po.planb.machinemanager.model;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
})

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String uuid;

    @Column(name = "supplierId")
    Long supplierId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "cpus")
    Parameters cpus;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json", name = "gpus")
    Parameters gpus;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "memory")
    Parameters memory;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "localStorage")
    Parameters localStorage;
}
