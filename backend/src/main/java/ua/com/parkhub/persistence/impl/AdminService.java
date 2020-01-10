package ua.com.parkhub.persistence.impl;
//
//@Service
//public class AdminService {
//    private UserDAO userDAO;
//    private UserRoleDAO userRoleDAO;
//
//
//    public AdminService(UserDAO userDAO, UserRoleDAO userRoleDAO) {
//        this.userDAO = userDAO;
//        this.userRoleDAO = userRoleDAO;
//
//    }
//
//    public User findUserById(long id){
//        return userDAO.findElementByIdSimple(id);
//    }
//    @Transactional
//    public void setRole(Long id, String role){
//        List<UserRole> r = userRoleDAO.findAll().stream().filter(ro -> ro.getRoleName().equals(role)).collect(Collectors.toList());
//        User targetUser = userDAO.findElementByIdSimple(id);
//        targetUser.setRole(r.get(0));
//        userDAO.updateElement(targetUser);
//    }
//    public String getRole(long id){
//        User targetUser = userDAO.findElementByIdSimple(id);
//        return targetUser.getRole().getRoleName();
//    }
//
//    public String getFirstName(long id){
//        User targetUser = userDAO.findElementByIdSimple(id);
//        return targetUser.getFirstName();
//    }
//
//    public long getId(long id){
//        User targetUser = userDAO.findElementByIdSimple(id);
//        return targetUser.getId();
//    }
//}

