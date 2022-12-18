package cn.saladday.dao.impl;

import cn.saladday.dao.UserDao;
import cn.saladday.domain.User;
import cn.saladday.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        String sql = "select * from user";
        List<User> usersList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return usersList;
    }

    @Override
    public User findUserByUsernameAndPassWord(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            //template如果没有找到就会报错
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void add(User addUser) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,addUser.getName(),addUser.getGender(),addUser.getAge(),addUser.getAddress()
                        ,addUser.getQq(),addUser.getEmail());
        return;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from user where id = ?";
        try{
            template.update(sql,id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public boolean update(User updateUser) {
        String sql = "update user set gender=?,age=?,address=?,qq=?,email=? where id =? ;";
        try {
            template.update(sql, updateUser.getGender(), updateUser.getAge(), updateUser.getAddress(),
                    updateUser.getQq(), updateUser.getEmail(), updateUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
