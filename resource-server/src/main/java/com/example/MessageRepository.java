package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Messageエンティティのリポジトリクラス
 * PagingAndSortingRepositoryはCrud+Paging/Sorting
 * Created by jun on 2016/06/29.
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
// ↓のCrudRepositoryを使うとJPAのCrudAPIがそのまま使える
// public interface MessageRepository extends CrudRepository<Message, Integer> {
}