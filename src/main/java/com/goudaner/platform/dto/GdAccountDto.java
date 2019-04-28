package com.goudaner.platform.dto;

import com.goudaner.platform.entity.GdOrder;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdAccountDto {

    private String accountNo;//用户id

    private String systemNo;//系统用户id

    private String systenName;//用户名


    private Date modify;//用户名

}
