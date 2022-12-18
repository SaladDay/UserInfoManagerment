package cn.saladday.service;

import cn.saladday.domain.PageBean;
import cn.saladday.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务逻辑层接口
 */
public interface UserService {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 传入loginUser，在数据库中查找
     * 若存在则返回user信息
     * 若不存在则返回null
     * @param loginUser
     * @return
     */
    public User login(User loginUser);

    /**
     * 加入一个用户信息
     * @param addUser
     */
    public void addSingle(User addUser);

    public boolean deleteSingle(int id);

    public User findById(int id);


    public boolean update(User updateUser);

    /**
     * 通过id删除多个数据
     * @param ids
     * @return
     */
    public boolean deleteMultiById(List<Integer> ids);

    /**
     * 分页功能
     *
     * @param currentPage
     * @param rows
     * @param condition 查询条件
     * @return
     */
    public PageBean<User> findUserByPage(int currentPage, int rows, Map<String, String[]> condition);
}
