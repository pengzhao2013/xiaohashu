package com.quanxiaoha.xiaohashu.kv.biz.domain.repository;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-06 10:28
 */

import com.quanxiaoha.xiaohashu.kv.biz.domain.dataobject.NoteContentDO;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

/**
 * @author: zpstart
 * @date: 2025/06/06 10:28
 * @version: v1.0.0
 * @description: TODO
 **/
public interface NoteContentRepository extends CassandraRepository<NoteContentDO, UUID> {

}
