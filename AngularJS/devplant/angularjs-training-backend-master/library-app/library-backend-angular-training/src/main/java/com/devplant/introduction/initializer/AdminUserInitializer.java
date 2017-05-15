package com.devplant.introduction.initializer;

import com.devplant.introduction.configuration.Roles;
import com.devplant.introduction.domain.User;
import com.devplant.introduction.domain.UserAuthority;
import com.devplant.introduction.properties.AdminUserProperties;
import com.devplant.introduction.repository.jpa.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminUserInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminUserProperties adminUserProperties;

	@Transactional
	protected void createAdminUser() {

		User adminUser = userRepository.findOneByUsernameIgnoreCase(adminUserProperties.getUsername());
		if (adminUser != null) {
			adminUser.setEnabled(true);
			adminUser.setPassword(passwordEncoder.encode(adminUserProperties.getPassword()));
			adminUser.getAuthorities().clear();
			adminUser.setAuthorities(
					Lists.newArrayList(new UserAuthority(Roles.ROLE_ADMIN), new UserAuthority(Roles.ROLE_USER)));
			userRepository.save(adminUser);
		} else {
			adminUser = new User(adminUserProperties.getUsername(), adminUserProperties.getUsername(),
					adminUserProperties.getUsername(), passwordEncoder.encode(adminUserProperties.getPassword()));
			adminUser.setEnabled(true);
			adminUser.getAuthorities().add(new UserAuthority(Roles.ROLE_ADMIN));
			userRepository.save(adminUser);
		}
	}

	@Override
	public void run(String... strings) throws Exception {
		createAdminUser();
	}
}
