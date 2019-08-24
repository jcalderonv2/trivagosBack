package com.ucenfotec.backend.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ucenfotec.backend.model.Role;
import com.ucenfotec.backend.model.User;
import com.ucenfotec.backend.service.RoleService;
import com.ucenfotec.backend.service.UserService;
import com.ucenfotec.backend.virtualobjects.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/user")
@Api(tags = "Usuario")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	@ApiOperation(value = " Obtiene todos los usuarios (Administrador) ")
	public Set<User> getAllUsers() {
		return userService.getAllActiveUsers();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = " Obtiene un usuario por su Id")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id).get();
	}

	@PostMapping()
	@ApiOperation(value = " Crea un usuario")
	@ApiResponse(code = 201, message = "Usuario creado correctamente")
	public ResponseEntity<User> createUser(@RequestBody UserVO userVO) {

		Optional<Role> role = roleService.getRoleById(userVO.getRole_id());

		User user = new User();

		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setEmail(userVO.getEmail());
		user.setPassword(userVO.getPassword());
		user.setActive(true);
		user.setRole(role.get());

		user = userService.createUser(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	@ApiOperation(value = " Actualiza un usuario por su Id")
	@ApiResponse(code = 200, message = "Usuario actualizado con exito")
	public ResponseEntity<User> updateUser(@RequestBody UserVO userVO, @RequestParam int id) {

		Optional<User> user = userService.getUserById(id);

		if (user.isPresent()) {

			user.get().setFirstName(userVO.getFirstName());
			user.get().setLastName(userVO.getLastName());
			user.get().setEmail(userVO.getEmail());
			user.get().setPassword(passwordEncoder.encode(userVO.getPassword()));

			userService.updateUser(user.get());

			return new ResponseEntity<User>(user.get(), HttpStatus.OK);

		}

		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = " Elimina un usuario por su Id (Administrador)")
	public void deleteUser(@RequestParam int id) {
		userService.deleteUser(id);

	}


}
