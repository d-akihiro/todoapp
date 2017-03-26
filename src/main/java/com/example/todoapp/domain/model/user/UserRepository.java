package com.example.todoapp.domain.model.user;

import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * ユーザ-リポジトリ
 *
 * Created by d_akihiro on 2017/02/21.
 */
public interface UserRepository {
	/**
	 * ユーザ名検索
	 * @param username ユーザ名
	 * @return ユーザ
	 */
	User findByUsername(String username);

	/**
	 * ID検索
	 * @param id ユーザID
	 * @return ユーザ
	 */
	User findById(Long id);

	/**
	 * 全件取得
	 * @return 全ユーザ
	 */
	List<User> findAll();

	/**
	 * 永続化
	 * @param user ユーザ
	 * @return ユーザ
	 */
	User save(@Validated User user);

	/**
	 * 永続化削除
	 * @param id ユーザID
	 */
	void delete(Long id);
}
