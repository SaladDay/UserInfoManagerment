package cn.saladday.dao;

import cn.saladday.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的Dao接口
 */
public interface UserDao {
    public int getTotalCount(Map<String,String[]> condition);
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

    /**
     * 分页查询
     * @param startIndex 从第几条数据开始
     * @param rows  找几条数据
     * @return List<User>
     */
    public List<User> findByPage(int startIndex,int rows,Map<String,String[]> condition);

    public boolean update(User updateUser);


}
