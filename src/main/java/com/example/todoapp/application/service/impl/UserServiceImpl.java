package com.example.todoapp.application.service.impl;

import com.example.todoapp.application.service.UserService;
import com.example.todoapp.domain.model.user.UserRepository;
import com.example.todoapp.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ユーザ管理サービス(実装)
 * Created by d_akihiro on 2017/02/21.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public PasswordEncoder getPasswordEncoder(){
		return User.getPasswordEncoder();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User getUser(long id) {
		return userRepository.findById(new Long(id));
	}

	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(String username, String password) {
		User user = new User(username, password);
		return saveUser(user);
	}

	@Override
	public User updateUser(long id, String username, String password) {
		User user = getUser(id);
		user.setUsername(username);
		user.updatePassword(password);
		return saveUser(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepository.delete(new Long(id));
	}

	/**
	 * 保存処理
	 * @param user
	 * @return
	 */
	private User saveUser(User user){
		User result;
		try{
			result  = userRepository.save(user);

		}
		catch (DataIntegrityViolationException ex){
			// 制約違反の場合は正常系とする
			result = null;
		}
		catch (Exception ex){
			throw ex;
		}
		return result;
	}
}
