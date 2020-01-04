package com.example.coreplayer.domain;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    protected Integer  version; //乐观锁
    @Column(length = 32)
    protected String createTime; //表示该记录的创建时间
    @Column(length = 32)
    protected String modifyTime; //表示该记录的最后一次修改时间

    @Column(insertable = false,columnDefinition = "boolean default false")
    protected boolean flag; //表示该记录是否是已经被删除的记录。
}
