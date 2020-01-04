package com.example.coreplayer.repository;


import com.example.coreplayer.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID> extends JpaRepository<T,ID>, JpaSpecificationExecutor {
}
