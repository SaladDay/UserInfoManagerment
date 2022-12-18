package cn.saladday.service.impl;

import cn.saladday.dao.UserDao;
import cn.saladday.dao.impl.UserDaoImpl;
import cn.saladday.domain.User;
import cn.saladday.service.UserService;

import java.util.List;

/**
 * 用户管理的业务逻辑层实现
 */
public class UserServiceImpl implements UserService {
    //User的Dao操作给User的逻辑层使用
    private final UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao来查询数据库
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {

        return dao.findUserByUsernameAndPassWord(loginUser.getUsername(),
                loginUser.getPassword());
    }

    @Override
    public void addSingle(User addUser) {
        dao.add(addUser);
    }

    @Override
    public boolean deleteSingle(int id) {
        return dao.delete(id);
    }

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public boolean update(User updateUser) {
        return dao.update(updateUser);
    }

    @Override
    public boolean deleteMultiById(List<Integer> ids) {

        if(ids!=null && !ids.isEmpty()) {
            try {
                for (Integer id : ids) {
                    dao.delete(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }


}
