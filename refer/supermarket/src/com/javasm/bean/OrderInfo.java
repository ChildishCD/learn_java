package com.javasm.bean;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderInfo implements Serializable {

  private static final long serialVersionUID = -3117028383788795876L;
  private Integer id;
  private Integer mid;
  private BigDecimal totalMoney;
  private Date payTime;
  private Integer payType;



}
