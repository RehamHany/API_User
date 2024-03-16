package com.adminPanel.app.dao;

import com.adminPanel.app.model.UserLoginModel;
import com.adminPanel.app.model.UserRegisterModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public UserRegisterModel findByEmail(String email) {

        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from UserRegisterModel where email=:userEmail");
            query.setParameter("userEmail" , email);
            List<UserRegisterModel> userRegisterModel=query.list();
            return userRegisterModel.get(0);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public UserLoginModel findByEmail1(String email) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from UserLoginModel where email=:userEmail");
            query.setParameter("userEmail" , email);
            List<UserLoginModel> userModel=query.list();
            return userModel.get(0);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserLoginModel> findEmail(String email) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserLoginModel where email=:userEmail ");
        query.setParameter("userEmail" , email);

        return query.list();
    }

    @Override
    public UserLoginModel insert(UserRegisterModel userRegisterModel) {
        try {
            Session session = sessionFactory.getCurrentSession();
            UserLoginModel userLoginModel = new UserLoginModel( userRegisterModel.getEmail(), userRegisterModel.getPassword());
            userLoginModel.setUserRegisterModel(userRegisterModel);

            session.persist(userLoginModel);
            return userLoginModel;
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String name,String email,String pass,String phone) {
        try {
            Session session = sessionFactory.getCurrentSession();

            UserRegisterModel userRegisterModel= findByEmail(email);

            UserRegisterModel oldUserRegister = session.get(UserRegisterModel.class,userRegisterModel.getId());

            UserLoginModel oldUserLogin =oldUserRegister.getUserLoginModel();
            //set the values with new values
            oldUserLogin.setPassword(pass);
            oldUserLogin.setEmail(email);
            oldUserRegister.setName(name);
            oldUserRegister.setEmail(email);
            oldUserRegister.setPassword(pass);
            oldUserRegister.setConfirmPass(pass);
            oldUserRegister.setPhone(phone);

            //we will set the olduser after update to the olduser and the olduser will be updated too
            oldUserLogin.setUserRegisterModel(oldUserRegister);
            session.update(oldUserLogin);

        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {

        try {
            Session session = sessionFactory.getCurrentSession();

            UserLoginModel userLoginModel = session.get(UserLoginModel.class,id);

            Query query = session.createQuery("delete from UserLoginModel  where id=:userId");
            query.setParameter("userId" , id);
            query.executeUpdate();

            query = session.createQuery("delete from UserRegisterModel  where id=:userRId");
            query.setParameter("userRId" , userLoginModel.getUserRegisterModel().getId());
            query.executeUpdate();

        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }

    @Override
    public UserRegisterModel findById(int id) {

        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(UserRegisterModel.class , id);
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public UserLoginModel findByUserRegister(UserRegisterModel userRegisterModel) {

        try {
            Session session = sessionFactory.getCurrentSession();
            UserRegisterModel userRegisterModel1  = session.get(UserRegisterModel.class , userRegisterModel.getId());
            return userRegisterModel1.getUserLoginModel();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public List<UserLoginModel> getAllUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM UserLoginModel");
            return (List<UserLoginModel>) query.list();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserLoginModel> checkUserLogin(String email,String pass) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserLoginModel where email=:userEmail and password=:userPassword ");
        query.setParameter("userEmail" , email);
        query.setParameter("userPassword" , pass);
        return query.list();

    }

    @Override
    public List<UserRegisterModel> checkUserRegister(UserRegisterModel userRegisterModel) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserRegisterModel where  email=:userEmail  and phone=:userPhone");
        query.setParameter("userEmail", userRegisterModel.getEmail());
        query.setParameter("userPhone", userRegisterModel.getPhone());
        return query.list();

    }




}
