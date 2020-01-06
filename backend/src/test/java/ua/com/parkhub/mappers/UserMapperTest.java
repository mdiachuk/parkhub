package ua.com.parkhub.mappers;

//class UserMapperTest {
//
//    private static User userEntity, userEntity1;
//    private static UserDTO userDTO, userDTO1;
//    private static UserRole role, role1;
//
//    @BeforeEach
//    public void init() {
//        role = new UserRole();
//        role1 = new UserRole();
//        userEntity = new User();
//        userEntity1 = new User();
//        userDTO = new UserDTO();
//        userDTO1 = new UserDTO();
//        role.setRoleName("ADMIN");
//        role1.setRoleName("USER");
//        userEntity.setId(1L);
//        userEntity.setEmail("Email");
//        userEntity.setFirstName("Artem");
//        userEntity.setLastName("Pupkin");
//        userEntity.setRole(role);
//        userEntity1.setId(1L);
//        userEntity1.setEmail("Email1");
//        userEntity1.setFirstName("Artem1");
//        userEntity1.setLastName("Pupkin1");
//        userEntity1.setRole(role1);
//        userDTO.setId(1L);
//        userDTO.setFirstName("Artem");
//        userDTO.setLastName("Pupkin");
//        userDTO.setEmail("Email");
//        userDTO.setRole(RoleDTO.ADMIN);
//        userDTO1 = new UserDTO();
//        userDTO1.setId(1L);
//        userDTO1.setEmail("Email1");
//        userDTO1.setFirstName("Artem1");
//        userDTO1.setLastName("Pupkin1");
//        userDTO1.setRole(RoleDTO.USER);
//    }
//
//
//    @Test
//    void persist() {
//        User newUserEntity = UserMapper.persist(userDTO);
//        assertEquals("Artem", newUserEntity.getFirstName());
//        assertEquals("Pupkin", newUserEntity.getLastName());
//        assertEquals("Email", newUserEntity.getEmail());
//        assertEquals("ADMIN", newUserEntity.getRole().getRoleName());
//        Assert.assertNull(newUserEntity.getPassword());
//    }
//
//    @Test
//    void detach() {
//        UserDTO newUserDTO = UserMapper.detach(userEntity);
//        assertEquals(1, newUserDTO.getIdInt());
//        assertEquals("Email", newUserDTO.getEmail());
//        assertEquals("Artem", newUserDTO.getFirstName());
//        assertEquals("Pupkin", newUserDTO.getLastName());
//        assertEquals(RoleDTO.ADMIN, newUserDTO.getRole());
//
//    }
//
//    @Test
//    public void detachWhenEntityNull() {
//        User user = null;
//        Assertions.assertThrows(ParkHubException.class, () -> UserMapper.detach(user));
//    }
//
//    @Test
//    public void persistWhenDtoNull() {
//        UserDTO userDTO = null;
//        Assertions.assertThrows(ParkHubException.class, () -> UserMapper.persist(userDTO));
//    }
//
//
//}
