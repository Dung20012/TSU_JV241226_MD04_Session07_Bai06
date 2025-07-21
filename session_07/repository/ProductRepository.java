package com.data.session_07.repository;

import com.data.session_07.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // đánh dấu là 1 lớp truy xuất dữ liệu DAO
public class ProductRepository {

    @Autowired
    // đối tượng Hibernate chính để mở các session kết nối tới CSDL
    private SessionFactory sessionFactory;

    // Lấy danh sách tất cả sản phẩm
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("from Product", Product.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Tìm sản phẩm theo ID
    public Product findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.get(Product.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới hoặc cập nhật sản phẩm
    public boolean save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sản phẩm theo ID
    public boolean delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Product product = findById(id);
            if(product != null){
                session.delete(product);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Ktra tên sản phẩm đã tồn tại hay chưa?
    public boolean checkProductNameExists(String productName) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.createQuery("from Product where productName = :productName",Product.class)
                .setParameter("productName", productName).uniqueResult();
        return product != null;
    }

    public List<Product> searchAndFilter(Long categoryId, String searchTerm, String sortOrder) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Product WHERE 1=1";

        if (categoryId != null) {
            hql += " AND categoryId = :categoryId";
        }
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            hql += " AND productName LIKE :searchTerm";
        }

        hql += " ORDER BY price " + ("desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc");

        Query<Product> query = session.createQuery(hql, Product.class);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            query.setParameter("searchTerm", "%" + searchTerm + "%");
        }

        return query.getResultList();
    }

}
