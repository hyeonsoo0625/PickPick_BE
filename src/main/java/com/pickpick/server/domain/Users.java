package com.pickpick.server.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickpick.server.domain.enums.PublicStatus;
import com.pickpick.server.domain.enums.ShareStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true, length = 30)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String phoneNum;

	private String imgUrl;

	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private PublicStatus publicStatus;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private ShareStatus shareStatus;

	private LocalDate createdAt;

	//== jwt 토큰 추가 ==//
	@Column(length = 1000)
	private String refreshToken;

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void destroyRefreshToken() {
		this.refreshToken = null;
	}

//	@JsonIgnore
//	@OneToMany(mappedBy = "users")
//	private List<SharedAlbum> sharedAlbums = new ArrayList<>();

	//== 패스워드 암호화 ==//
	public void encodePassword(PasswordEncoder passwordEncoder){
		this.password = passwordEncoder.encode(password);
	}

}
