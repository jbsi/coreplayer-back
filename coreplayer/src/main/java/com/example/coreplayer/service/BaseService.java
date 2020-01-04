package com.example.coreplayer.service;

import com.example.coreplayer.domain.BaseEntity;
import com.example.coreplayer.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity,I extends Serializable,R extends BaseRepository<T,I>> {
    //1.查询所有
    List<T> findAll();

    //2.根据主键查询单个对象。
    T getOne(I id);

    //3.查询分页记录。
    Page<T> findAllByPager(Pageable pageable);

    //4.添加单个对象
    void save(T obj);

    //5.修改单个对象
    void update(T obj);

    //6.根据主键删除单个对象。
    void deleteById(I id);

    //7.批量添加
    List<T> saveList(List<T> list);

    //8.批量删除
    void batchDelete(List<T> list);

}
