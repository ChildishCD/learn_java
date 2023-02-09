package com.test;


import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("message")
public class MessageModel implements Serializable{

	private static final long serialVersionUID =  1L;

	@Column("id")
	@ID("id")
	private Integer id;

	@Column("ip")
	private String ip;

	@Column("msg")
	private String msg;

	@Column("create_time")
	private LocalDateTime createTime;


}
