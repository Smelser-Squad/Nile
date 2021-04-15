package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repo;


    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public Category getCategoryById(Integer categoryId) throws NullCategoryIdException, InvalidCategoryIdException {
        if(categoryId==null){
            throw new NullCategoryIdException("Cannot get category with null id");
        }
        Category retrieved = null;
        Optional<Category> category=repo.findById(categoryId);
        if(category.isPresent()){
            retrieved=category.get();
            return retrieved;
        }else{
            throw new InvalidCategoryIdException("Category with that id does not exist");
        }
    }

    @Override
    public Category addCategory(Category newCategory) {
        return repo.saveAndFlush(newCategory);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        return repo.saveAndFlush(updatedCategory);
    }

    @Override
    public boolean deleteCategory(Integer categoryId) throws NullCategoryIdException, InvalidCategoryIdException {
        if(categoryId == null){
            throw new NullCategoryIdException("Cannot delete category with null id");
        }
        Category retrieved = repo.findById(categoryId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidCategoryIdException("Category with that id does not exist");
        }
    }
}
