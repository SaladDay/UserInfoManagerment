package cn.saladday.service.impl;

import cn.saladday.dao.UserDao;
import cn.saladday.dao.impl.UserDaoImpl;
import cn.saladday.domain.PageBean;
import cn.saladday.domain.User;
import cn.saladday.service.UserService;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageBean<User> findUserByPage(int currentPage, int rows, Map<String, String[]> condition) {
        //调用dao下的getTotalCount获得记录总数
        int totalCount = dao.getTotalCount(condition);

        //计算得到totalPage
        int totalPage = totalCount % rows == 0?totalCount / rows:(totalCount / rows)+1;
        //计算得到startIndex = (currentPage-1)*rows
        int startIndex = (currentPage-1)*rows;
        //调用dao下的List<User> findByPage(int startIndex,int rows);
        List<User> list = dao.findByPage(startIndex, rows,condition);
        PageBean<User> pageBean = new PageBean<User>(totalCount,totalPage,list,currentPage,rows);
        return pageBean;
    }


}
