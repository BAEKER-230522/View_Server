package com.baeker.baeker.myStudy.form;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyStudyJoinForm {

    @Size(max = 40)
    private String msg;
}
