package com.example.todoapp.domain.model.user;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * ユーザ-エンティティ
 *
 * Created by d_akihiro on 2017/02/21.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="user_data")
public class User implements Serializable{

	private static final long serialVersionUID = -8672983332788581601L;

	/**
	 * ユーザID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * ユーザ名
	 */
	@Column(nullable=false, unique=true)
	@NotBlank
	@Size(min=5, max=20)
	private String username;

	/**
	 * パスワード
	 */
	@Column(nullable=false)
	@NotEmpty
	private String password;

	/**
	 * コンストラクタ
	 * @param username ユーザ名
	 * @param password パスワード(エンコード前)
	 */
	public User(String username, String password){
		this.username = username;
		this.updatePassword(password);
	}

	/**
	 * パスワード更新
	 * - パスワードはエンコードしてから永続化する
	 * @param password パスワード(エンコード前)
	 */
	public void updatePassword(
			String password){
		if(StringUtils.isEmpty(password) || password.length() < 5){
			this.password = null;
		}
		else {
			this.password = getPasswordEncoder().encode(password);
		}
	}

	/**
	 * パスワードエンコーダ
	 * @return エンコーダ
	 */
	public static PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * 比較処理
	 * - ユーザIDが同一のユーザ-エンティティは同一のエンティティを示す
	 * @param object
	 * @return
	 */
	@Override
	public boolean equals(Object object){
		if(this == object){
			return true;
		}
		if(object == null || getClass() != object.getClass()){
			return false;
		}
		User user = (User)object;
		return username.equals(username);
	}

	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}
}
