package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();
    Category getCategoryById(Integer categoryId) throws NullProductIdException, InvaildProductIdException, InvalidCategoryIdException, NullCategoryIdException;
    Category addCategory(Category newCategory);
    Category updateCategory(Category updatedCategory);
    boolean deleteCategory(Integer categoryId) throws NullProductIdException, InvaildProductIdException, NullCategoryIdException, InvalidCategoryIdException;



}
