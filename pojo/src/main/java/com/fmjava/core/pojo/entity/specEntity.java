package com.fmjava.core.pojo.entity;

import com.fmjava.core.pojo.specification.Specification;
import com.fmjava.core.pojo.specification.SpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class specEntity implements Serializable {
    //spec选项名称
    private Specification spec;
    //
    private List<SpecificationOption> specOption;

    public specEntity() {
    }

    public specEntity(Specification spec, List<SpecificationOption> specOption) {
        this.spec = spec;
        this.specOption = specOption;
    }
}
