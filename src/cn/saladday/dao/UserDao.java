package cn.saladday.dao;

import cn.saladday.domain.User;

import java.util.List;

/**
 * 用户操作的Dao接口
 */
public interface UserDao {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 通过username和password查找对应用户
     * 只有两个都匹配才返回用户信息
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassWord(String username,String password);

    /**
     * 加入一个用户信息
     * @param addUser
     */
    public void add(User addUser);

    public boolean delete(int id);

    public User findById(int id);

    public boolean update(User updateUser);
}
